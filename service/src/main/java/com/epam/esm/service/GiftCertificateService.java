package com.epam.esm.service;


import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.RequestParameters;
import com.epam.esm.exception.GiftCertificateNotFoundException;

import java.math.BigInteger;
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
    GiftCertificateDto findById(BigInteger id);

    /**
     * Changes instance of GiftCertificate with given id.
     *
     * @param giftCertificate updated instance of GiftCertificate.
     * @param id              index of GiftCertificate to update.
     * @return the updated GiftCertificate.
     */
    GiftCertificateDto update(GiftCertificateDto giftCertificate, BigInteger id);

    /**
     * Deletes the GiftCertificate with given id.
     *
     * @param id must not be {@literal null}.
     * @return id of the deleted GiftCertificate.
     */
    BigInteger deleteById(BigInteger id);

    /**
     * Saves a given GiftCertificate.
     *
     * @param giftCertificate must not be {@literal null}.
     * @return saved GiftCertificate.
     */
    GiftCertificateDto save(GiftCertificateDto giftCertificate);
}