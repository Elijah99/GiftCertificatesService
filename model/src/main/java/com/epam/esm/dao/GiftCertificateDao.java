package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.mapper.GiftCertificateRowMapper;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public class GiftCertificateDao implements Dao<GiftCertificate> {

    private static final String SELECT_ALL = "SELECT * FROM gift_certificate";

    private JdbcTemplate jdbcTemplate;
    private RowMapper<GiftCertificate> rowMapper;

    @Autowired
    public GiftCertificateDao(BasicDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.rowMapper = new GiftCertificateRowMapper();
    }

    @Override
    public List<GiftCertificate> findAll() throws DaoException {
        return jdbcTemplate.query(SELECT_ALL, rowMapper);
    }

    @Override
    public Optional<GiftCertificate> findEntityById(BigInteger id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void create(GiftCertificate entity) throws DaoException {

    }

    @Override
    public void update(GiftCertificate entity) throws DaoException {

    }

    @Override
    public void deleteById(BigInteger id) throws DaoException {

    }

    @Override
    public void save(GiftCertificate entity) throws DaoException {

    }
}
