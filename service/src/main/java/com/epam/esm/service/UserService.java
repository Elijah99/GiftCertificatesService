package com.epam.esm.service;

import com.epam.esm.dto.RequestParameters;
import com.epam.esm.dto.UserDto;
import com.epam.esm.exception.UserNotFoundException;

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
    UserDto findById(Long id);

    /**
     * Retrieves all Users with parameters.
     *
     * @param requestParameters parameters to search users. must not be {@literal null}.
     * @return list of UserDto.
     */
    List<UserDto> findAll(RequestParameters requestParameters);

    /**
     * Saves new User to db with 'ROLE_USER' role.
     *
     * @param user new user details. must not be {@literal null}.
     * @return saved UserDto.
     */
    UserDto registerUser(UserDto user);

    /**
     * Counts number of all User records pages at database.
     *
     * @param requestParameters parameters of request. must not be {@literal null}.
     * @return counted number of User records pages.
     */
    long countPages(RequestParameters requestParameters);

    /**
     * Counts number of all User records at database.
     *
     * @return counted number of User records.
     */
    long count();
}
