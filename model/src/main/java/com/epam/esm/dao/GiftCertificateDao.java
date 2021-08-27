package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface GiftCertificateDao {
    List<GiftCertificate> findAll();

    Optional<GiftCertificate> findById(BigInteger id);

    void update(GiftCertificate giftCertificate);

    void deleteById(BigInteger id);

    GiftCertificate save(GiftCertificate giftCertificate);

    List<GiftCertificate> searchByColumn(String searchParameter, String value);

    List<GiftCertificate> searchByTagName(String value);

    List<GiftCertificate> findAllWithOrder(String sortParameter, String sortType);
}
