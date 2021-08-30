package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 *  DAO interface responsible for processing
 *  operations with database for GiftCertificate class
 *
 *  @author Ilya Ramanouski
 */
public interface GiftCertificateDao {

    /**
     * Retrieves all of GiftCertificates from database.
     * @return list of all GiftCertificates
     */
    List<GiftCertificate> findAll();

    /**
     * Retrieves a GiftCertificate from database by its id.
     * @param id must not be {@literal null}.
     * @return the GiftCertificate with the given id or {@literal Optional#empty()} if none found.
     */
    Optional<GiftCertificate> findById(BigInteger id);

    /**
     * Changes instance of GiftCertificate with given id.
     * @param giftCertificate updated instance of GiftCertificate.
     */
    void update(GiftCertificate giftCertificate);

    /**
     * Deletes the GiftCertificate with given id.
     * @param id must not be {@literal null}.
     * @return
     */
    BigInteger deleteById(BigInteger id);

    /**
     * Saves a given GiftCertificate.
     * @param giftCertificate must not be {@literal null}.
     */
    GiftCertificate save(GiftCertificate giftCertificate);

    /**
     * Returns list of all found by column GiftCertificates.
     * @param searchParameter column name to search.
     * @param value given column's value to search.
     * @return list of found GiftCertificates.
     */
    List<GiftCertificate> searchByColumn(String searchParameter, String value);

    /**
     * Returns list of all found by tag name GiftCertificates.
     * @param value given value of tag name to search.
     * @return list of found GiftCertificates.
     */
    List<GiftCertificate> searchByTagName(String value);

    /**
     * Returns sorted by parameter list of GiftCertificates.
     * @param sortParameter column to sort
     * @param sortType asc/desc sort.
     * @return sorted list of GiftCertificates.
     */
    List<GiftCertificate> findAllWithOrder(String sortParameter, String sortType);
}
