package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.mapper.TagRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class TagDaoImpl implements TagDao {

    private static final String SELECT_ALL = "SELECT * FROM tag";
    private static final String SELECT_BY_ID = "SELECT * FROM tag WHERE id = ?";
    private static final String SELECT_BY_NAME = "SELECT id, name FROM tag WHERE name = ?";
    private static final String SELECT_BY_GIFT_CERTIFICATE_ID = "SELECT * FROM tag INNER JOIN gift_certificate_tag" +
            " ON tag.id = gift_certificate_tag.id_tag WHERE gift_certificate_tag.id_gift_certificate = ?";
    private static final String INSERT = "INSERT INTO tag (name) VALUES (?)";
    private static final String DELETE_BY_ID = "DELETE FROM tag WHERE id = ?";
    private static final String DELETE_FROM_LINK_TABLE_BY_ID = "DELETE FROM gift_certificate_tag WHERE id_tag = ?";

    private final JdbcTemplate jdbcTemplate;
    private final TagRowMapper rowMapper;

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
    public List<Tag> findByGiftCertificateId(BigInteger id) {
        return jdbcTemplate.query(SELECT_BY_GIFT_CERTIFICATE_ID, rowMapper, id);
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return jdbcTemplate.query(SELECT_BY_NAME, rowMapper, name).stream().findAny();
    }

    @Override
    public BigInteger deleteById(BigInteger id) {
        jdbcTemplate.update(DELETE_FROM_LINK_TABLE_BY_ID, id);
        jdbcTemplate.update(DELETE_BY_ID, id);
        return id;
    }

    @Override
    public Tag save(Tag tag) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tag.getName());
            return ps;
        }, keyHolder);
        Long idLong;
        if (keyHolder.getKeys().size() > 1) {
            idLong = (Long) keyHolder.getKeys().get("id");
        } else {
            idLong = keyHolder.getKey().longValue();
        }
        tag.setId(BigInteger.valueOf(idLong));
        return tag;
    }

}
