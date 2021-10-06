package com.epam.esm.service;

import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.RequestParameters;
import com.epam.esm.exception.OrderNotFoundException;

import java.math.BigInteger;
import java.util.List;

/**
 * Interface for operations on Order class
 *
 * @author Ilya Ramanouski
 */
public interface OrderService {
    /**
     * Creates and saves a given Order.
     *
     * @param idUser   id of User. must not be {@literal null}.
     * @param orderDto OrderDto to create. must not be {@literal null}.
     * @return saved GiftCertificate.
     */
    OrderDto createOrder(BigInteger idUser, OrderDto orderDto);

    /**
     * Retrieves all Order of User with parameters.
     *
     * @param idUser            id of Order's User. must not be {@literal null}.
     * @param requestParameters parameters to search orders. must not be {@literal null}.
     * @return list of OrderDto.
     * @throws OrderNotFoundException if Order with given id doesn't exists.
     */
    List<OrderDto> findOrdersByUserId(BigInteger idUser, RequestParameters requestParameters);

    /**
     * Returns all of Orders find by parameters.
     *
     * @return list of all OrderDto
     */
    List<OrderDto> findAll(RequestParameters parameters);

    /**
     * Retrieves an Order of User by its id.
     *
     * @param userId  id of Order's User. must not be {@literal null}.
     * @param orderId id of requested Order. must not be {@literal null}.
     * @return the OrderDto with the given id.
     * @throws OrderNotFoundException if Order with given id doesn't exists.
     */
    OrderDto findOrderById(BigInteger userId, BigInteger orderId);

    /**
     * Counts number of all Order records at database.
     *
     * @param userId            id of Order's User. must not be {@literal null}.
     * @param requestParameters parameters of request. must not be {@literal null}.
     * @return counted number of Order records.
     */
    long countPages(BigInteger userId, RequestParameters requestParameters);
}
