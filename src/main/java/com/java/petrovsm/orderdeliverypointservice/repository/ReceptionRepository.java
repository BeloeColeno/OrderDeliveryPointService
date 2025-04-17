package com.java.petrovsm.orderdeliverypointservice.repository;

import com.java.petrovsm.orderdeliverypointservice.entity.Reception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public class ReceptionRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReceptionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Reception> findAllByPvzId(UUID pvzId) {
        String sql = "SELECT * FROM reception WHERE pvz_id = ?";
        return jdbcTemplate.query(sql, new ReceptionRowMapper(), pvzId);
    }

    static class ReceptionRowMapper implements RowMapper<Reception> {
        @Override
        public Reception mapRow(ResultSet rs, int rowNum) throws SQLException {
            Reception reception = new Reception();
            reception.setId(UUID.fromString(rs.getString("id")));
            reception.setReceptionDate(rs.getObject("reception_date", LocalDateTime.class));
            reception.setStatus(rs.getString("status"));
            reception.setPvzId(UUID.fromString(rs.getString("pvz_id")));
            return reception;
        }
    }
}