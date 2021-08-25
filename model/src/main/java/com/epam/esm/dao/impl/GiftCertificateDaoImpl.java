package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.mapper.GiftCertificateRowMapper;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {

    private static final String SELECT_ALL = "SELECT * FROM gift_certificate";
    private static final String SELECT_BY_ID = "SELECT * FROM gift_certificate WHERE id = ?";
    private static final String INSERT = "INSERT INTO gift_certificate (name, description, price, create_date, last_update_date, duration) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_BY_ID = "DELETE FROM gift_certificate WHERE id = ?";

    private JdbcTemplate jdbcTemplate;
    private GiftCertificateRowMapper rowMapper;

    @Autowired
    public GiftCertificateDaoImpl(BasicDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.rowMapper = new GiftCertificateRowMapper();
    }

    @Override
    public List<GiftCertificate> findAll() {
        return jdbcTemplate.query(SELECT_ALL, rowMapper);
    }

    @Override
    public Optional<GiftCertificate> findById(BigInteger id) {
        GiftCertificate result = jdbcTemplate.queryForObject(SELECT_BY_ID, new Object[]{id}, rowMapper);
        return Optional.of(result);
    }

    @Override
    public GiftCertificate save(GiftCertificate giftCertificate) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(INSERT,
                giftCertificate.getName(),
                giftCertificate.getDescription(),
                giftCertificate.getPrice(),
                giftCertificate.getCreateDate(),
                giftCertificate.getLastUpdateDate(),
                giftCertificate.getDuration());
        BigInteger id = BigInteger.valueOf(holder.getKey().longValue());
        giftCertificate.setId(id);
        return giftCertificate;
    }

    @Override
    public void update(GiftCertificate giftCertificate) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteById(BigInteger id) {
        jdbcTemplate.update(DELETE_BY_ID, id);
    }
}
