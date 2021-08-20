package com.epam.esm.mapper;

import com.epam.esm.entity.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class GiftCertificateRowMapper implements RowMapper<GiftCertificate> {
    @Override
    public GiftCertificate mapRow(ResultSet resultSet, int i) throws SQLException {
        BigInteger id = (BigInteger) resultSet.getObject("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        BigDecimal price = resultSet.getBigDecimal("price");
        Timestamp createDate = resultSet.getTimestamp("create_date");
        Timestamp lastUpdateDate = resultSet.getTimestamp("last_update_date");
        int duration = resultSet.getInt("duration");

        return new GiftCertificate(id,name,description,price,createDate,lastUpdateDate,duration);
    }
}
