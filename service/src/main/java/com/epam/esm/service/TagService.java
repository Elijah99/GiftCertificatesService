package com.epam.esm.service;

import com.epam.esm.entity.Tag;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface TagService {

    List<Tag> findAll();

    Optional<Tag> findById(BigInteger id);

    void update(Tag tag);

    void deleteById(BigInteger id);

    void save(Tag tag);
}
