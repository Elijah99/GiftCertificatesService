package com.epam.esm.dao;

import com.epam.esm.entity.Tag;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * DAO interface responsible for processing
 * operations with database for Tag class
 *
 * @author Ilya Ramanouski
 */
public interface TagDao {

    /**
     * Retrieves all tags from database.
     *
     * @return list of all tags.
     */
    List<Tag> findAll();

    /**
     * Retrieves a tag from database by its id.
     *
     * @param id must not be {@literal null}.
     * @return the Tag with the given id or {@literal Optional#empty()} if none found.
     */
    Optional<Tag> findById(BigInteger id);

    /**
     * Retrieves a tag from database by its name.
     *
     * @param name must not be {@literal null}.
     * @return the Tag with the given name or {@literal Optional#empty()} if none found.
     */
    Optional<Tag> findByName(String name);

    /**
     * Deletes the tag by its id.
     *
     * @param id must not be {@literal null}.
     * @return
     */
    BigInteger deleteById(BigInteger id);

    /**
     * Saves a given Tag.
     *
     * @param tag must not be {@literal null}.
     * @return
     */
    Tag save(Tag tag);
}
