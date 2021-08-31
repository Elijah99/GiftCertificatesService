package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.mapper.GiftCertificateRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {

    private static final String SELECT_ALL = "SELECT * FROM gift_certificate INNER JOIN gift_certificate_tag" +
            " ON gift_certificate.id = gift_certificate_tag.id_gift_certificate INNER JOIN tag ON gift_certificate_tag.id_tag = tag.id";
    private static final String SELECT_BY_ID = "SELECT * FROM gift_certificate INNER JOIN gift_certificate_tag" +
            " ON gift_certificate.id = gift_certificate_tag.id_gift_certificate INNER JOIN tag ON gift_certificate_tag.id_tag = tag.id WHERE gift_certificate.id = ?";
    private static final String SELECT_BY_COLUMN = "SELECT * FROM gift_certificate INNER JOIN gift_certificate_tag" +
            " ON gift_certificate.id = gift_certificate_tag.id_gift_certificate INNER JOIN tag ON gift_certificate_tag.id_tag = tag.id WHERE gift_certificate.%s like ?";
    private static final String SELECT_ALL_WITH_ORDER = "SELECT * FROM gift_certificate INNER JOIN gift_certificate_tag" +
            " ON gift_certificate.id = gift_certificate_tag.id_gift_certificate INNER JOIN tag ON gift_certificate_tag.id_tag = tag.id ORDER BY %s %s";
    private static final String SELECT_BY_TAG_NAME = "SELECT * FROM gift_certificate INNER JOIN gift_certificate_tag" +
            " ON gift_certificate.id = gift_certificate_tag.id_gift_certificate INNER JOIN tag ON gift_certificate_tag.id_tag = tag.id WHERE tag.name LIKE ?";
    private static final String INSERT = "INSERT INTO gift_certificate (name, description, price, create_date, last_update_date, duration) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE gift_certificate SET name = COALESCE(?, name), description = COALESCE(?, description)," +
            " price = COALESCE(?, price), duration = COALESCE(?, duration), create_date = COALESCE(?, create_date), last_update_date = ? WHERE id =?";
    private static final String DELETE_BY_ID = "DELETE FROM gift_certificate WHERE id = ?";


    private final JdbcTemplate jdbcTemplate;
    private final GiftCertificateRowMapper rowMapper;

    @Autowired
    public GiftCertificateDaoImpl(JdbcTemplate jdbcTemplate, GiftCertificateRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public List<GiftCertificate> findAll() {
        return jdbcTemplate.query(SELECT_ALL, rowMapper);
    }

    @Override
    public Optional<GiftCertificate> findById(BigInteger id) {
        return jdbcTemplate.query(SELECT_BY_ID, rowMapper, id).stream().findAny();
    }

    @Override
    public GiftCertificate save(GiftCertificate giftCertificate) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, giftCertificate.getName());
            ps.setString(2, giftCertificate.getDescription());
            ps.setBigDecimal(3, giftCertificate.getPrice());
            ps.setTimestamp(4, Timestamp.valueOf(giftCertificate.getCreateDate()));
            ps.setTimestamp(5, Timestamp.valueOf(giftCertificate.getLastUpdateDate()));
            ps.setInt(6, giftCertificate.getDuration());
            return ps;
        }, keyHolder);

        Long idLong;
        if (keyHolder.getKeys().size() > 1) {
            idLong = (Long) keyHolder.getKeys().get("id");
        } else {
            idLong = keyHolder.getKey().longValue();
        }
        giftCertificate.setId(BigInteger.valueOf(idLong));
        return giftCertificate;
    }

    @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        jdbcTemplate.update(UPDATE, giftCertificate.getName(), giftCertificate.getDescription(),
                giftCertificate.getPrice(), giftCertificate.getDuration(),
                giftCertificate.getCreateDate(), Timestamp.valueOf(now()), giftCertificate.getId());
        return findById(giftCertificate.getId()).get();
    }

    @Override
    public BigInteger deleteById(BigInteger id) {
        jdbcTemplate.update(DELETE_BY_ID, id);
        return id;
    }

    @Override
    public List<GiftCertificate> searchByColumn(String searchParameter, String value) {
        return jdbcTemplate.query(String.format(SELECT_BY_COLUMN, searchParameter), rowMapper, "%" + value + "%");
    }

    @Override
    public List<GiftCertificate> searchByTagName(String value) {
        return jdbcTemplate.query(SELECT_BY_TAG_NAME, rowMapper, "%" + value + "%");
    }

    @Override
    public List<GiftCertificate> findAllWithOrder(String sortParameter, String sortType) {
        return jdbcTemplate.query(String.format(SELECT_ALL_WITH_ORDER, sortParameter, sortType), rowMapper);
    }
}
