package com.epam.esm.service;


import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.service.utils.SearchParameter;
import com.epam.esm.service.utils.SortParameter;
import com.epam.esm.service.utils.SortType;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface GiftCertificateService {

    List<GiftCertificate> findAll();

    Optional<GiftCertificate> findById(BigInteger id);

    void update(GiftCertificate giftCertificate, BigInteger id);

    void deleteById(BigInteger id);

    void save(GiftCertificate giftCertificate);

    List<GiftCertificate> searchByValue(SearchParameter searchParameter, String value);

    List<GiftCertificate> sortByParameter(SortParameter sortParameter, SortType sortType);
}