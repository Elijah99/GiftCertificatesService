package com.epam.esm.dao;

import com.epam.esm.entity.QueryParameters;
import com.epam.esm.entity.Tag;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface TagDao {

    List<Tag> findAll(QueryParameters parameters);

    Optional<Tag> findById(BigInteger id);

    List<Tag> findByGiftCertificateId(BigInteger id);

    Optional<Tag> findByName(String name);

    BigInteger deleteById(BigInteger id);

    Tag save(Tag tag);

    List<Tag> findByGiftCertificateIdIn(List<BigInteger> idGiftCertificates);

    long count();
}
