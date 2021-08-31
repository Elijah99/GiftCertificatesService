package com.epam.esm.mapper;

import com.epam.esm.entity.GiftCertificateTag;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GiftCertificateTagRowMapper implements RowMapper<GiftCertificateTag> {

    @Override
    public GiftCertificateTag mapRow(ResultSet resultSet, int i) throws SQLException {
        BigInteger id = new BigInteger(resultSet.getString("id"));
        BigInteger idGiftCertificate = new BigInteger(resultSet.getString("id_gift_certificate"));
        BigInteger idTag = new BigInteger(resultSet.getString("id_tag"));

        return new GiftCertificateTag(id, idGiftCertificate, idTag);
    }
}
