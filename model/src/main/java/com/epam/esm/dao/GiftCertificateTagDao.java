package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificateTag;
import com.epam.esm.entity.Tag;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 *  DAO interface responsible for processing
 *  operations with database for linking table
 *  between GiftCertificates and Tags, which
 *  have many-to-many relationships
 *
 *  @author Ilya Ramanouski
 */
public interface GiftCertificateTagDao {

    /**
     * Retrieves a GiftCertificateTag from database by its id.
     * @param id must not be {@literal null}.
     * @return the GiftCertificateTag with the given id
     * or {@literal Optional#empty()} if none found.
     */
    Optional<GiftCertificateTag> findById(BigInteger id);

    /**
     * Retrieves a GiftCertificateTag from database by GiftCertificates id.
     * @param id must not be {@literal null}.
     * @return the GiftCertificateTag with the given GiftCertificates id
     * or {@literal Optional#empty()} if none found.
     */
    List<GiftCertificateTag> findByGiftCertificateId(BigInteger id);

    /**
     * Retrieves a GiftCertificateTag from database by Tag id.
     * @param id must not be {@literal null}.
     * @return the GiftCertificateTag with the given Tag id
     * or {@literal Optional#empty()} if none found.
     */
    List<GiftCertificateTag> findByTagId(BigInteger id);

    /**
     * Deletes the GiftCertificateTag with given id.
     * @param id must not be {@literal null}.
     */
    void deleteById(BigInteger id);

    /**
     * Saves a given GiftCertificateTag.
     * @param giftCertificateTag must not be {@literal null}.
     */
    void save(GiftCertificateTag giftCertificateTag);
}
