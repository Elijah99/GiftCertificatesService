package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificateTag;

import java.util.Optional;

public interface GiftCertificateTagDao {

    Optional<GiftCertificateTag> findById(Long id);

    GiftCertificateTag update(GiftCertificateTag giftCertificate);

    Long deleteById(Long id);

    GiftCertificateTag save(GiftCertificateTag giftCertificate);
}
