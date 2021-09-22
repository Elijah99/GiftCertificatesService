package com.epam.esm.dao;

import com.epam.esm.entity.Order;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface OrderDao {
    List<Order> findAll();

    Optional<Order> findById(BigInteger id);

    BigInteger deleteById(BigInteger id);

    List<Order> findByUserId(BigInteger idUser);

    Order save(Order order);

    long countByUserId(BigInteger userId);
}
