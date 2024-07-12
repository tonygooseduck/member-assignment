package com.example.member.repositories;

import com.example.member.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(Member member) {
        String sql = "INSERT INTO member (mid, mname, email) values (?, ?, ?)";
        jdbcTemplate.update(sql, member.getId(), member.getName(), member.getEmail());
    }

    public List<Member> findById(String id) {
        String sql = "SELECT * FROM member WHERE mid = ?";
        RowMapper<Member> memberRowMapper = (r, i) -> {
            Member rowObject = new Member();
            rowObject.setId(r.getString("mid"));
            rowObject.setName(r.getString("mname"));
            rowObject.setEmail(r.getString("email"));
            return rowObject;
        };

        return jdbcTemplate.query(sql, memberRowMapper, id);
    }

    public List<Member> findByEmail(String email) {
        String sql = "SELECT * FROM member WHERE email = ?";
        RowMapper<Member> memberRowMapper = (r, i) -> {
            Member rowObject = new Member();
            rowObject.setId(r.getString("mid"));
            rowObject.setName(r.getString("mname"));
            rowObject.setEmail(r.getString("email"));
            return rowObject;
        };

        return jdbcTemplate.query(sql, memberRowMapper, email);
    }
}
