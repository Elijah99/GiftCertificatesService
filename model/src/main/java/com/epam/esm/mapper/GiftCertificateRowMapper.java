package com.epam.esm.mapper;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class GiftCertificateRowMapper implements RowMapper<GiftCertificate> {
    @Override
    public GiftCertificate mapRow(ResultSet resultSet, int i) throws SQLException {
        BigInteger id = new BigInteger(resultSet.getString("id"));
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        BigDecimal price = resultSet.getBigDecimal("price");
        LocalDateTime createDate = resultSet.getTimestamp("create_date").toLocalDateTime();
        LocalDateTime lastUpdateDate = resultSet.getTimestamp("last_update_date").toLocalDateTime();
        int duration = resultSet.getInt("duration");
        String tagName = resultSet.getString("name");

        return new GiftCertificate(id, name, description, price, createDate, lastUpdateDate, duration, Arrays.asList(new Tag(tagName)));
    }
}
