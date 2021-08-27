package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.mapper.GiftCertificateRowMapper;
import com.epam.esm.mapper.TagRowMapper;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public class TagDaoImpl implements TagDao {

    private static final String SELECT_ALL = "SELECT * FROM tag";
    private static final String SELECT_BY_ID = "SELECT * FROM tag WHERE id = ?";
    private static final String SELECT_BY_NAME = "SELECT id, name FROM tag WHERE name = ?";
    private static final String INSERT = "INSERT INTO tag (name) VALUES (?)";
    private static final String DELETE_BY_ID = "DELETE FROM tag WHERE id = ?";


    private JdbcTemplate jdbcTemplate;
    private TagRowMapper rowMapper;

    @Autowired
    public TagDaoImpl(JdbcTemplate jdbcTemplate, TagRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query(SELECT_ALL, rowMapper);
    }

    @Override
    public Optional<Tag> findById(BigInteger id) {
        return jdbcTemplate.query(SELECT_BY_ID, rowMapper, id).stream().findAny();
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return jdbcTemplate.query(SELECT_BY_NAME, rowMapper, name).stream().findAny();
    }

    @Override
    public void deleteById(BigInteger id) {
        jdbcTemplate.update(DELETE_BY_ID, id);
    }

    @Override
    public void save(Tag tag) {
        jdbcTemplate.update(INSERT, tag.getName());
    }
}
