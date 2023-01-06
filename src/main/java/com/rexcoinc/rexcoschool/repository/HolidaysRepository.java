package com.rexcoinc.rexcoschool.repository;

import com.rexcoinc.rexcoschool.model.Holiday;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HolidaysRepository {
    private final JdbcTemplate jdbcTemplate;

    public HolidaysRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Holiday> findAllHolidays(){
        String query = "SELECT * FROM holidays";
        var rowMapper = BeanPropertyRowMapper.newInstance(Holiday.class);
        return jdbcTemplate.query(query, rowMapper);
    }
}
