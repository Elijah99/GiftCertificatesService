package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.QueryParameters;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateDao {

    List<GiftCertificate> findByParameters(QueryParameters parameters);

    Optional<GiftCertificate> findById(Long id);

    GiftCertificate update(GiftCertificate giftCertificate);

    Long deleteById(Long id);

    GiftCertificate save(GiftCertificate giftCertificate);

    long count();
}
