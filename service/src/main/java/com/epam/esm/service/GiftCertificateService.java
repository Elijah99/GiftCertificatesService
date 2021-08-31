package com.epam.esm.service;


import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.enums.SearchParameter;
import com.epam.esm.enums.SortParameter;
import com.epam.esm.enums.SortType;
import com.epam.esm.exception.GiftCertificateNotFoundException;

import java.math.BigInteger;
import java.util.List;

/**
 *  Interface for operations on GiftCertificate class
 *
 *  @author Ilya Ramanouski
 */
public interface GiftCertificateService {

    /**
     * Returns all of GiftCertificates.
     * @return list of all GiftCertificates
     */
    List<GiftCertificateDto> findAll();

    /**
     * Retrieves an GiftCertificate by its id.
     * @param id must not be {@literal null}.
     * @return the GiftCertificate with the given id.
     * @throws GiftCertificateNotFoundException if GiftCertificate
     * with given id doesn't exists.
     */
    GiftCertificateDto findById(BigInteger id);

    /**
     * Changes instance of GiftCertificate with given id.
     * @param giftCertificate updated instance of GiftCertificate.
     * @param id index of GiftCertificate to update.
     */
    void update(GiftCertificateDto giftCertificate, BigInteger id);

    /**
     * Deletes the GiftCertificate with given id.
     * @param id must not be {@literal null}.
     */
    void deleteById(BigInteger id);

    /**
     * Saves a given GiftCertificate.
     * @param giftCertificate must not be {@literal null}.
     */
    void save(GiftCertificateDto giftCertificate);

    /**
     * Returns list of all found by parameters GiftCertificates.
     * @param searchParameter column name to search.
     * @param value given value to search.
     * @return list of found GiftCertificates.
     */
    List<GiftCertificateDto> searchByValue(SearchParameter searchParameter, String value);

    /**
     * Returns sorted by parameter list of GiftCertificates.
     * @param sortParameter column to sort
     * @param sortType asc/desc sort.
     * @return sorted list of GiftCertificates.
     */
    List<GiftCertificateDto> sortByParameter(SortParameter sortParameter, SortType sortType);
}