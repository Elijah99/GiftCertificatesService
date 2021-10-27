package com.epam.esm.dao;

import com.epam.esm.entity.QueryParameters;
import com.epam.esm.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagDao {

    List<Tag> findByParameters(QueryParameters parameters);

    Optional<Tag> findById(Long id);

    Long deleteById(Long id);

    Tag save(Tag tag);

    Optional<Tag> findByName(String name);

    Tag findMostUsedTag(Long idUser);

    long count();
}
