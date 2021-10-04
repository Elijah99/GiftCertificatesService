package com.epam.esm.service;

import com.epam.esm.dto.UserDto;
import com.epam.esm.dto.RequestParameters;
import com.epam.esm.exception.OrderNotFoundException;
import com.epam.esm.exception.UserNotFoundException;

import java.math.BigInteger;
import java.util.List;

/**
 * Interface for operations on Tag class
 *
 * @author Ilya Ramanouski
 */
public interface UserService {

    /**
     * Retrieves User by its id.
     *
     * @param id id of User. must not be {@literal null}.
     * @return UserDto.
     * @throws UserNotFoundException if User with given id doesn't exists.
     */
    UserDto findById(BigInteger id);

    /**
     * Retrieves all Users with parameters.
     *
     * @param requestParameters parameters to search users. must not be {@literal null}.
     * @return list of UserDto.
     */
    List<UserDto> findAll(RequestParameters requestParameters);

    /**
     * Counts number of all User records at database.
     *
     * @param requestParameters parameters of request. must not be {@literal null}.
     * @return counted number of User records.
     */
    long count(RequestParameters requestParameters);
}
