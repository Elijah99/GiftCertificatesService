package com.epam.esm.service;


import com.epam.esm.entity.GiftCertificate;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface GiftCertificateService {

    List<GiftCertificate> findAll();

    Optional<GiftCertificate> findById(BigInteger id);

    void update(GiftCertificate giftCertificate);

    void deleteById(BigInteger id);

    void save(GiftCertificate giftCertificate);
}
