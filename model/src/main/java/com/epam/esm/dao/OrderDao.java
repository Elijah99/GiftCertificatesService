package com.epam.esm.dao;

import com.epam.esm.entity.Order;
import com.epam.esm.entity.QueryParameters;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    List<Order> findByParameters(QueryParameters parameters);

    Optional<Order> findById(Long id);

    List<Order> findByUserId(Long idUser, QueryParameters queryParameters);

    Order save(Order order);

    long countByUserId(Long userId);
}
