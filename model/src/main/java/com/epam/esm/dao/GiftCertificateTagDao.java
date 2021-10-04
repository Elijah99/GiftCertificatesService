package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.GiftCertificateTag;

import java.math.BigInteger;
import java.util.Optional;

public interface GiftCertificateTagDao {

    Optional<GiftCertificateTag> findById(BigInteger id);

    GiftCertificateTag update(GiftCertificateTag giftCertificate);

    BigInteger deleteById(BigInteger id);

    GiftCertificateTag save(GiftCertificateTag giftCertificate);
}
