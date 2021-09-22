package com.epam.esm.controller;

import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.enums.RequestParameters;
import com.epam.esm.enums.SearchParameter;
import com.epam.esm.enums.SortType;
import com.epam.esm.hateoas.OrderLinkManager;
import com.epam.esm.hateoas.UserLinkManager;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private OrderService orderService;
    private UserLinkManager userLinkManager;
    private OrderLinkManager orderLinkManager;

    @GetMapping(produces = "application/hal+json")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<UserDto> getAllUsers(@RequestParam(required = false, defaultValue = "1") int page,
                                                                     @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                                     @RequestParam(required = false) String sortType,
                                                                     @RequestParam(required = false) String sortValue,
                                                                     @RequestParam(required = false) String searchParameter,
                                                                     @RequestParam(required = false) String searchValue) {

        RequestParameters requestParameters = new RequestParameters(page, pageSize, sortType, sortValue, searchParameter, searchValue);

        return userLinkManager.createLinks(userService.findAll(requestParameters), requestParameters);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable("id") BigInteger id) {
        UserDto userDto = userService.findById(id);
        userDto.add(linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel());
        return userDto;
    }

    @PostMapping(value = "/{id}/orders")
    @ResponseStatus(HttpStatus.OK)
    public String createOrder(@PathVariable("id") BigInteger id, @RequestBody OrderDto dto) {
        OrderDto orderDto = orderService.createOrder(id, dto);
        return "created:" + orderDto.getId();
    }

    @GetMapping(value = "/{id_user}/orders/{id_order}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getOrderById(
            @PathVariable("id_user") BigInteger userId, @PathVariable("id_order") BigInteger orderId) {
        return orderLinkManager.createLinks(orderService.findOrderById(userId, orderId));
    }

    @GetMapping(value = "/{id}/orders")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<OrderDto> getOrdersByUserId(@PathVariable("id") BigInteger id,
                                            @RequestParam(required = false, defaultValue = "1") Integer page,
                                            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                            @RequestParam(required = false) String sortType,
                                            @RequestParam(required = false) String sortValue,
                                            @RequestParam(required = false) String searchParameter,
                                            @RequestParam(required = false) String searchValue) {
        RequestParameters requestParameters = new RequestParameters(page, pageSize, sortType, sortValue, searchParameter, searchValue);

        return orderLinkManager.createLinks(orderService.findOrdersByUserId(id),requestParameters);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setUserLinkManager(UserLinkManager userLinkManager) {
        this.userLinkManager = userLinkManager;
    }

    @Autowired
    public void setOrderLinkManager(OrderLinkManager orderLinkManager) {
        this.orderLinkManager = orderLinkManager;
    }



    public TagDto getTheMostWidelyUsedTagOfUserWithTheHighestCostOfAllOrders(BigInteger idUser) {
        return null;
    }
}
