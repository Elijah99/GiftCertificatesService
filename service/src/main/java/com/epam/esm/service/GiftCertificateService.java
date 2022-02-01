package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.RequestParameters;
import com.epam.esm.exception.GiftCertificateNotFoundException;

import java.util.List;

/**
 * Interface for operations on GiftCertificate class
 *
 * @author Ilya Ramanouski
 */
public interface GiftCertificateService {

    /**
     * Returns all of GiftCertificates find by parameters.
     *
     * @return list of all GiftCertificateDto
     */
    List<GiftCertificateDto> findAll(RequestParameters parameters);

    /**
     * Retrieves an GiftCertificate by its id.
     *
     * @param id must not be {@literal null}.
     * @return the GiftCertificate with the given id.
     * @throws GiftCertificateNotFoundException if GiftCertificate
     *                                          with given id doesn't exists.
     */
    GiftCertificateDto findById(Long id);

    /**
     * Changes instance of GiftCertificate with given id.
     *
     * @param giftCertificate updated instance of GiftCertificate.
     * @param id              index of GiftCertificate to update.
     * @return the updated GiftCertificate.
     */
    GiftCertificateDto update(GiftCertificateDto giftCertificate, Long id);

    /**
     * Deletes the GiftCertificate with given id.
     *
     * @param id must not be {@literal null}.
     * @return id of the deleted GiftCertificate.
     */
    Long deleteById(Long id);

    /**
     * Saves a given GiftCertificate.
     *
     * @param giftCertificate must not be {@literal null}.
     * @return saved GiftCertificate.
     */
    GiftCertificateDto save(GiftCertificateDto giftCertificate);

    /**
     * Counts number of all GiftCertificate records pages.
     *
     * @param requestParameters parameters of request. must not be {@literal null}.
     * @return counted number of GiftCertificate records pages.
     */
    long countPages(RequestParameters requestParameters);

    /**
     * Counts number of all GiftCertificate records.
     *
     * @return counted number of GiftCertificate records.
     */
    long count(RequestParameters requestParameters);
}