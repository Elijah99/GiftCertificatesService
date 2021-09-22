package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.OrderDao;
import com.epam.esm.dao.UserDao;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import com.epam.esm.enums.RequestParameters;
import com.epam.esm.exception.GiftCertificateNotFoundException;
import com.epam.esm.exception.OrderNotFoundException;
import com.epam.esm.exception.UserNotFoundException;
import com.epam.esm.mapper.impl.OrderMapper;
import com.epam.esm.service.OrderService;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private GiftCertificateDao giftCertificateDao;
    private UserDao userDao;
    private OrderMapper orderMapper;

    @Override
    public OrderDto createOrder(BigInteger id, OrderDto dto) {
        Order order = setOrder(id, dto);
        User user = userDao.findById(id).orElseThrow(UserNotFoundException::new);
        order.setUser(user);
        return orderMapper.mapEntityToDto(orderDao.save(order));
    }

    @Override
    public List<OrderDto> findOrdersByUserId(BigInteger idUser) {
        return orderMapper.mapListEntityToListDto(orderDao.findByUserId(idUser));
    }

    @Override
    public List<OrderDto> findAll() {
        return orderMapper.mapListEntityToListDto(orderDao.findAll());
    }

    @Override
    public OrderDto findOrderById(BigInteger userId, BigInteger orderId) {
        Optional<Order> optionalOrder = orderDao.findById(orderId);
        if (!optionalOrder.isPresent()) {
            throw new OrderNotFoundException();
        }
        return orderMapper.mapEntityToDto(optionalOrder.get());
    }

    BigDecimal calculateOrderCost(List<GiftCertificate> gifts) {
        List<BigDecimal> costs =
                gifts.stream().map(GiftCertificate::getPrice).collect(Collectors.toList());
        return costs.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    Order setOrder(BigInteger userId, OrderDto orderDto) {
        orderDto.setIdUser(userId);
        Order order = orderMapper.mapDtoToEntity(orderDto);

        List<GiftCertificate> giftCertificates = new ArrayList<>();
        for (GiftCertificate giftCertificate : order.getGiftCertificates()) {
            BigInteger giftCertificateDtoId = giftCertificate.getId();
            Optional<GiftCertificate> giftCertificateOptional = giftCertificateDao.findById(giftCertificateDtoId);
            if(giftCertificateOptional.isPresent()){
                giftCertificates.add(giftCertificateOptional.get());
            } else{
                throw new GiftCertificateNotFoundException();
            }
        }
        order.setGiftCertificates(giftCertificates);

        order.setCost(calculateOrderCost(order.getGiftCertificates()));
        order.setPurchaseDate(LocalDateTime.now());

        return order;
    }

    @Override
    public long count(BigInteger userId, RequestParameters requestParameters) {
        int pageSize = requestParameters.getPageSize();
        long elementsAmount = orderDao.countByUserId(userId);
        return elementsAmount % pageSize == 0
                ? elementsAmount / pageSize
                : elementsAmount / pageSize + 1;
    }

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Autowired
    public void setGiftCertificateDao(GiftCertificateDao giftCertificateDao) {
        this.giftCertificateDao = giftCertificateDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
