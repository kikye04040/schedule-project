package com.sparta.planmanager.repository;

import com.sparta.planmanager.entity.Manager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class ManagerRepository {
    private final JdbcTemplate jdbcTemplate;

    public ManagerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Manager save(Manager manager) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO manager(name, email, created_at, updated_at) VALUES (?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, manager.getName());
            ps.setString(2, manager.getEmail());
            ps.setTimestamp(3, java.sql.Timestamp.valueOf(manager.getCreateAt()));
            ps.setTimestamp(4, java.sql.Timestamp.valueOf(manager.getUpdateAt()));
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();
        manager.setId(id);
        return manager;
    }

    public void update(Manager manager) {
        String sql = "UPDATE manager SET name = ?, email = ?, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, manager.getName(), manager.getEmail(), manager.getUpdateAt(), manager.getId());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM manager WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Manager findById(Long id) {
        String sql = "SELECT * FROM manager WHERE id = ?";
        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Manager manager = new Manager();
                manager.setId(resultSet.getLong("id"));
                manager.setName(resultSet.getString("name"));
                manager.setEmail(resultSet.getString("email"));
                manager.setCreateAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                manager.setUpdateAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
                return manager;
            }
            else {
                return null;
            }
        }, id);
    }
}
