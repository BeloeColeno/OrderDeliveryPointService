package com.java.petrovsm.orderdeliverypointservice.repository;

import com.java.petrovsm.orderdeliverypointservice.entity.Pvz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
public class PvzRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PvzRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Pvz save(Pvz pvz) {
        if (pvz.getId() == null) {
            pvz.setId(UUID.randomUUID());
        }

        jdbcTemplate.update(
                "INSERT INTO pvz (id, name, address, status, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?)",
                pvz.getId(), pvz.getName(), pvz.getAddress(), pvz.getStatus(),
                Timestamp.valueOf(pvz.getCreatedAt()), Timestamp.valueOf(pvz.getUpdatedAt())
        );

        return pvz;
    }

    public Page<Pvz> findAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end, Pageable pageable) {
        String countSql = "SELECT count(*) FROM pvz WHERE created_at BETWEEN ? AND ?";
        String sql = "SELECT * FROM pvz WHERE created_at BETWEEN ? AND ? LIMIT ? OFFSET ?";

        Integer total = jdbcTemplate.queryForObject(countSql, Integer.class, start, end);

        List<Pvz> pvzList = jdbcTemplate.query(sql, new PvzRowMapper(),
                start, end, pageable.getPageSize(), pageable.getOffset());

        return new PageImpl<>(pvzList, pageable, total != null ? total : 0);
    }

    public Page<Pvz> findAll(Pageable pageable) {
        String countSql = "SELECT count(*) FROM pvz";
        String sql = "SELECT * FROM pvz LIMIT ? OFFSET ?";

        Integer total = jdbcTemplate.queryForObject(countSql, Integer.class);

        List<Pvz> pvzList = jdbcTemplate.query(sql, new PvzRowMapper(),
                pageable.getPageSize(), pageable.getOffset());

        return new PageImpl<>(pvzList, pageable, total != null ? total : 0);
    }

    static class PvzRowMapper implements RowMapper<Pvz> {
        @Override
        public Pvz mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pvz pvz = new Pvz();
            pvz.setId(UUID.fromString(rs.getString("id")));
            pvz.setName(rs.getString("name"));
            pvz.setAddress(rs.getString("address"));
            pvz.setStatus(rs.getString("status"));
            pvz.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            pvz.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));
            return pvz;
        }
    }
}