package com.epam.esm.mapper;

import com.epam.esm.entity.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;


public class GiftCertificateRowMapper implements RowMapper<GiftCertificate> {
    @Override
    public GiftCertificate mapRow(ResultSet resultSet, int i) throws SQLException {
        BigInteger id = new BigInteger(resultSet.getString("id"));
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        BigDecimal price = resultSet.getBigDecimal("price");
        OffsetDateTime createDate = resultSet.getObject("create_date", OffsetDateTime.class);
        OffsetDateTime lastUpdateDate = resultSet.getObject("last_update_date", OffsetDateTime.class);
        int duration = resultSet.getInt("duration");

        return new GiftCertificate(id, name, description, price, createDate, lastUpdateDate, duration);
    }
}
