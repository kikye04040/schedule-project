package com.sparta.planmanager.repository;

import com.sparta.planmanager.entity.Plan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlanRepository {
    private final JdbcTemplate jdbcTemplate;

    public PlanRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Plan 저장 메서드
    public Plan save(Plan plan) {
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체
        String sql = "INSERT INTO plan (todo, manager_id, password, date, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(con ->  {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, plan.getTodo());
            ps.setLong(2, plan.getManagerId());
            ps.setString(3, plan.getPassword());
            ps.setString(4, plan.getDate());
            ps.setTimestamp(5, Timestamp.valueOf(plan.getCreateAt()));
            ps.setTimestamp(6, Timestamp.valueOf(plan.getUpdateAt()));
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();
        plan.setId(id);

        return plan;
    }

    public List<Plan> findAllByConditions(Long managerId, String updateAt, int pageNumber, int pageSize) {
        StringBuilder sql = new StringBuilder("SELECT p.*, m.name as manager_name FROM plan p JOIN manager m ON p.manager_id = m.id");

        List<Object> params = new ArrayList<>();
        boolean hasConditions = (managerId != null) || (updateAt != null && !updateAt.isEmpty());

        if (hasConditions) {
            sql.append(" WHERE ");
            if (managerId != null) {
                sql.append(" m.id = ?");
                params.add(managerId);
            }
            if (updateAt != null && !updateAt.isEmpty()) {
                if (managerId != null) {
                    sql.append(" AND");
                }
                sql.append(" DATE(p.updated_at) = ?");
                params.add(updateAt);
            }
        }

        sql.append(" ORDER BY p.updated_at DESC");
        sql.append(" LIMIT ? OFFSET ?");

        int offset = (pageNumber - 1) * pageSize;
        params.add(pageSize);
        params.add(offset);

        return jdbcTemplate.query(sql.toString(), new RowMapper<Plan>() {
            @Override
            public Plan mapRow(ResultSet rs, int rowNum) throws SQLException {
                Plan plan = new Plan();
                plan.setId(rs.getLong("id"));
                plan.setTodo(rs.getString("todo"));
                plan.setManagerId(rs.getLong("manager_id"));
                plan.setPassword(rs.getString("password"));
                plan.setDate(rs.getString("date"));
                plan.setCreateAt(rs.getTimestamp("created_at").toLocalDateTime());
                plan.setUpdateAt(rs.getTimestamp("updated_at").toLocalDateTime());
                return plan;
            }
        });
    }

    public void update(Plan plan) {
        String sql = "UPDATE plan SET todo = ?, manager_id = ?, password = ?, date = ?, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                plan.getTodo(),
                plan.getManagerId(),
                plan.getPassword(),
                plan.getDate(),
                Timestamp.valueOf(plan.getUpdateAt()),
                plan.getId());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM plan WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Plan findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM memo WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Plan plan = new Plan();
                plan.setId(resultSet.getLong("id"));
                plan.setTodo(resultSet.getString("todo"));
                plan.setManagerId(resultSet.getLong("manager_id"));
                plan.setPassword(resultSet.getString("password"));
                plan.setDate(resultSet.getString("date"));
                plan.setCreateAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                plan.setUpdateAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
                return plan;
            } else {
                return null;
            }
        }, id);
    }
}
