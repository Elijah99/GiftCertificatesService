package com.epam.esm.dao;

import com.epam.esm.entity.QueryParameters;
import com.epam.esm.entity.Tag;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface TagDao {

    List<Tag> findByParameters(QueryParameters parameters);

    Optional<Tag> findById(BigInteger id);

    BigInteger deleteById(BigInteger id);

    Tag save(Tag tag);

    Tag findMostUsedTag();

    long count();
}
