package com.epam.esm.service;

import com.epam.esm.dto.TagDto;
import com.epam.esm.enums.RequestParameters;
import com.epam.esm.exception.TagNotFoundException;

import java.math.BigInteger;
import java.util.List;

/**
 * Interface for operations on Tag class
 *
 * @author Ilya Ramanouski
 */
public interface TagService {

    /**
     * Returns all of Tags.
     *
     * @return list of all Tags
     */
    List<TagDto> findAll(RequestParameters parameters);

    /**
     * Retrieves a Tag by its id.
     *
     * @param id must not be {@literal null}.
     * @return the Tag with the given id.
     * @throws TagNotFoundException if Tag with
     *                              given id doesn't exists.
     */
    TagDto findById(BigInteger id);

    /**
     * Deletes the Tag with given id.
     *
     * @param id must not be {@literal null}.
     */
    BigInteger deleteById(BigInteger id);

    /**
     * Saves a given Tag.
     *
     * @param tag must not be {@literal null}.
     * @return saved Tag instance
     */
    TagDto save(TagDto tag);

    TagDto getMostWidelyUsedTagOfAUserWithTheHighestCostOfAllOrders();
}
