package com.epam.esm.mapper;

import com.epam.esm.entity.GiftCertificateTag;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GiftCertificateTagRowMapper implements RowMapper<GiftCertificateTag> {

    /**
     * Mapping ResultSet to GiftCertificateTag entity
     *
     * @deprecated No longer necessary. Returns Empty Entity.
     * Do not use!
     */
    @Deprecated
    @Override
    public GiftCertificateTag mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong("id");
        Long idGiftCertificate = resultSet.getLong("id_gift_certificate");
        Long idTag = resultSet.getLong("id_tag");

        return new GiftCertificateTag();
    }
}
