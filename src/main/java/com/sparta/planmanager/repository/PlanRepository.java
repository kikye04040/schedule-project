package com.sparta.planmanager.repository;

import com.sparta.planmanager.dto.PlanResponseDto;
import com.sparta.planmanager.entity.Plan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class PlanRepository {
    private final JdbcTemplate jdbcTemplate;

    public PlanRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Plan save(Plan plan) {
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체
        String sql = "INSERT INTO plan (todo, managername, password, date) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(con ->  {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, plan.getTodo());
            ps.setString(2, plan.getManagername());
            ps.setString(3, plan.getPassword());
            ps.setString(4, plan.getDate());
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();
        plan.setId(id);

        return plan;
    }

    public Plan findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM memo WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Plan plan = new Plan();
                plan.setId(resultSet.getLong("id"));
                plan.setTodo(resultSet.getString("todo"));
                plan.setManagername(resultSet.getString("managername"));
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
