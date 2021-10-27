package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.OrderDao;
import com.epam.esm.dao.UserDao;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.RequestParameters;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.QueryParameters;
import com.epam.esm.entity.User;
import com.epam.esm.exception.GiftCertificateNotFoundException;
import com.epam.esm.exception.OrderNotFoundException;
import com.epam.esm.exception.UserNotFoundException;
import com.epam.esm.mapper.impl.OrderMapper;
import com.epam.esm.mapper.impl.RequestParametersMapper;
import com.epam.esm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private GiftCertificateDao giftCertificateDao;
    private UserDao userDao;
    private OrderMapper orderMapper;
    private RequestParametersMapper requestParametersMapper;

    @Override
    public OrderDto createOrder(Long idUser, OrderDto orderDto) {
        Order order = setOrder(idUser, orderDto);
        User user = userDao.findById(idUser).orElseThrow(UserNotFoundException::new);
        order.setUser(user);
        return orderMapper.mapEntityToDto(orderDao.save(order));
    }

    @Override
    public List<OrderDto> findOrdersByUserId(Long idUser, RequestParameters requestParameters) {
        QueryParameters queryParameters = requestParametersMapper.mapDtoToEntity(requestParameters);
        return orderMapper.mapListEntityToListDto(orderDao.findByUserId(idUser, queryParameters));
    }

    @Override
    public List<OrderDto> findAll(RequestParameters parameters) {
        List<Order> orders = orderDao.findByParameters(requestParametersMapper.mapDtoToEntity(parameters));
        return orderMapper.mapListEntityToListDto(orders);
    }

    @Override
    public OrderDto findOrderById(Long userId, Long orderId) {
        Optional<Order> optionalOrder = orderDao.findById(orderId);
        if (!optionalOrder.isPresent()) {
            throw new OrderNotFoundException();
        }
        return orderMapper.mapEntityToDto(optionalOrder.get());
    }

    private BigDecimal calculateOrderCost(List<GiftCertificate> gifts) {
        List<BigDecimal> costs =
                gifts.stream().map(GiftCertificate::getPrice).collect(Collectors.toList());
        return costs.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Order setOrder(Long userId, OrderDto orderDto) {
        //orderDto.setIdUser(userId);
        Order order = orderMapper.mapDtoToEntity(orderDto);

        List<GiftCertificate> giftCertificates = new ArrayList<>();
        for (GiftCertificate giftCertificate : order.getGiftCertificates()) {
            Long giftCertificateDtoId = giftCertificate.getId();
            Optional<GiftCertificate> giftCertificateOptional = giftCertificateDao.findById(giftCertificateDtoId);
            if (giftCertificateOptional.isPresent()) {
                giftCertificates.add(giftCertificateOptional.get());
            } else {
                throw new GiftCertificateNotFoundException();
            }
        }
        order.setGiftCertificates(giftCertificates);

        order.setCost(calculateOrderCost(order.getGiftCertificates()));
        order.setPurchaseDate(LocalDateTime.now());

        return order;
    }

    @Override
    public long countPages(Long userId, RequestParameters requestParameters) {
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

    @Autowired
    public void setRequestParametersMapper(RequestParametersMapper requestParametersMapper) {
        this.requestParametersMapper = requestParametersMapper;
    }
}
