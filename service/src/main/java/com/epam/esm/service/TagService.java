package com.epam.esm.service;

import com.epam.esm.dto.RequestParameters;
import com.epam.esm.dto.TagDto;
import com.epam.esm.exception.OrderNotFoundException;
import com.epam.esm.exception.TagNotFoundException;

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
     * @param parameters parameters to search orders. must not be {@literal null}.
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
    TagDto findById(Long id);

    /**
     * Deletes the Tag with given id.
     *
     * @param id must not be {@literal null}.
     */
    Long deleteById(Long id);

    /**
     * Saves a given Tag.
     *
     * @param tag must not be {@literal null}.
     * @return saved Tag instance
     */
    TagDto save(TagDto tag);

    TagDto getMostWidelyUsedTagOfAUserWithTheHighestCostOfAllOrders(Long idUser);

    /**
     * Counts number of all Tag records pages.
     *
     * @param requestParameters parameters of request. must not be {@literal null}.
     * @return counted number of Tag records pages.
     */
    long countPages(RequestParameters requestParameters);
}
