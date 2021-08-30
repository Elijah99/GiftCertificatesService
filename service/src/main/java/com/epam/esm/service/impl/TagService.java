package com.epam.esm.service.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.TagNotFoundException;

import java.math.BigInteger;
import java.util.List;

/**
 *  Interface for operations on Tag class
 *
 *  @author Ilya Ramanouski
 */
public interface TagService {

    /**
     * Returns all of Tags.
     * @return list of all Tags
     */
    List<Tag> findAll();

    /**
     * Retrieves a Tag by its id.
     * @param id must not be {@literal null}.
     * @return the Tag with the given id.
     * @throws TagNotFoundException if Tag with
     * given id doesn't exists.
     */
    Tag findById(BigInteger id);

    /**
     * Deletes the Tag with given id.
     * @param id must not be {@literal null}.
     */
    void deleteById(BigInteger id);

    /**
     * Saves a given Tag.
     * @param tag must not be {@literal null}.
     */
    void save(Tag tag);
}
