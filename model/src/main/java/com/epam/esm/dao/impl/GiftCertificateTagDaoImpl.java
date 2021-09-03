package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateTagDao;
import com.epam.esm.entity.GiftCertificateTag;
import com.epam.esm.mapper.GiftCertificateTagRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public class GiftCertificateTagDaoImpl implements GiftCertificateTagDao {

    private static final String SELECT_ALL = "SELECT * FROM gift_certificate_tag";
    private static final String SELECT_BY_ID = "SELECT * FROM gift_certificate_tag WHERE id = ?";
    private static final String SELECT_BY_GIFT_CERTIFICATE_ID = "SELECT * FROM gift_certificate_tag WHERE id_gift_certificate = ?";
    private static final String SELECT_BY_TAG_ID = "SELECT * FROM gift_certificate_tag WHERE id_tag = ?";
    private static final String INSERT = "INSERT INTO  gift_certificate_tag (id_gift_certificate, id_tag) VALUES (?, ?)";
    private static final String DELETE_BY_ID = "DELETE FROM  gift_certificate_tag WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;
    private final GiftCertificateTagRowMapper rowMapper;

    @Autowired
    public GiftCertificateTagDaoImpl(JdbcTemplate jdbcTemplate, GiftCertificateTagRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public List<GiftCertificateTag> findAll() {
        return jdbcTemplate.query(SELECT_ALL, rowMapper);
    }

    @Override
    public Optional<GiftCertificateTag> findById(BigInteger id) {
        GiftCertificateTag result = jdbcTemplate.queryForObject(SELECT_BY_ID, new Object[]{id}, rowMapper);
        return Optional.of(result);
    }

    @Override
    public List<GiftCertificateTag> findByGiftCertificateId(BigInteger id) {
        return jdbcTemplate.query(SELECT_BY_GIFT_CERTIFICATE_ID, new Object[]{id}, rowMapper);
    }

    @Override
    public List<GiftCertificateTag> findByTagId(BigInteger id) {
        return jdbcTemplate.query(SELECT_BY_TAG_ID, new Object[]{id}, rowMapper);
    }

    @Override
    public void deleteById(BigInteger id) {
        jdbcTemplate.update(DELETE_BY_ID, id);
    }

    @Override
    public void save(GiftCertificateTag giftCertificateTag) {
        jdbcTemplate.update(INSERT,
                giftCertificateTag.getIdGiftCertificate(),
                giftCertificateTag.getIdTag());
    }

}
