package com.epam.esm.controller;

import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private OrderService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable("id") BigInteger id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "/{id}/orders", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String createOrder(@PathVariable("id") BigInteger id, @RequestBody OrderDto dto) {
        OrderDto orderDto = orderService.createOrder(id, dto);
        return "created:" + orderDto.getId();
    }

    @RequestMapping(value = "/{id_user}/orders/{id_order}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getOrderById(
            @PathVariable("id_user") BigInteger userId, @PathVariable("id_order") BigInteger orderId) {
        return orderService.findOrderById(userId, orderId);
    }

    @RequestMapping(value = "/{id}/orders", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> ordersByUserId(@PathVariable("id") BigInteger id) {
        return orderService.findOrdersByUserId(id);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
