package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.QueryParameters;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface GiftCertificateDao {

    List<GiftCertificate> findByParameters(QueryParameters parameters);

    Optional<GiftCertificate> findById(BigInteger id);

    GiftCertificate update(GiftCertificate giftCertificate);

    BigInteger deleteById(BigInteger id);

    GiftCertificate save(GiftCertificate giftCertificate);

    long count();
}
