package com.epam.esm.dao;

import com.epam.esm.entity.Tag;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface TagDao {
    List<Tag> findAll();

    Optional<Tag> findById(BigInteger id);

    Optional<Tag> findByName(String name);

    void deleteById(BigInteger id);

    void save(Tag tag);
}
