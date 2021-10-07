package com.epam.esm.dao;

import com.epam.esm.entity.Order;
import com.epam.esm.entity.QueryParameters;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface OrderDao {
    List<Order> findByParameters(QueryParameters parameters);

    Optional<Order> findById(BigInteger id);

    List<Order> findByUserId(BigInteger idUser, QueryParameters queryParameters);

    Order save(Order order);

    long countByUserId(BigInteger userId);
}
