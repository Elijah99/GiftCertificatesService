package com.epam.esm.service;

import com.epam.esm.dto.OrderDto;
import com.epam.esm.enums.RequestParameters;

import java.math.BigInteger;
import java.util.List;

public interface OrderService {
    OrderDto createOrder(BigInteger id, OrderDto dto);

    List<OrderDto> findOrdersByUserId(BigInteger idUser);

    List<OrderDto> findAll();

    OrderDto findOrderById(BigInteger userId, BigInteger orderId);

    long count(BigInteger userId, RequestParameters requestParameters);
}
