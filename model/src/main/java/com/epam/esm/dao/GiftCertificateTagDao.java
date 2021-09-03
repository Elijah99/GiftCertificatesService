package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificateTag;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface GiftCertificateTagDao {

    Optional<GiftCertificateTag> findById(BigInteger id);

    List<GiftCertificateTag> findByGiftCertificateId(BigInteger id);

    List<GiftCertificateTag> findByTagId(BigInteger id);

    void deleteById(BigInteger id);

    void save(GiftCertificateTag giftCertificateTag);

    List<GiftCertificateTag> findAll();

    List<GiftCertificateTag> findByGiftCertificateIdIn(List<BigInteger> idGiftCertificates);
}
