package com.epam.esm.service;

import com.epam.esm.dto.TagDto;
import com.epam.esm.dto.RequestParameters;
import com.epam.esm.exception.OrderNotFoundException;
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
     * Retrieves all Order of User with parameters.
     *
     * @param idUser id of Order's User. must not be {@literal null}.
     * @param requestParameters parameters to search orders. must not be {@literal null}.
     * @return list of OrderDto.
     * @throws OrderNotFoundException if Order with given id doesn't exists.
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
