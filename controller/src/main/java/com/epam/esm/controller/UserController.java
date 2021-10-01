package com.epam.esm.controller;

import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.enums.RequestParameters;
import com.epam.esm.enums.SearchParameter;
import com.epam.esm.enums.SortType;
import com.epam.esm.hateoas.OrderLinkManager;
import com.epam.esm.hateoas.UserLinkManager;
import com.epam.esm.hateoas.representation.OrderRepresentation;
import com.epam.esm.hateoas.representation.UserRepresentation;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

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
    public CollectionModel<UserRepresentation> getAllUsers(@RequestParam(required = false, defaultValue = "1") int page,
                                                @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                @RequestParam(required = false) String sortType,
                                                @RequestParam(required = false) String sortValue,
                                                @RequestParam(required = false) String searchParameter,
                                                @RequestParam(required = false) String searchValue) {

        RequestParameters requestParameters = new RequestParameters(page, pageSize, sortType, sortValue, searchParameter, searchValue);

        List<UserRepresentation> links = userService.findAll(requestParameters).stream().map(UserRepresentation::new).collect(Collectors.toList());

        return userLinkManager.createLinks(links, requestParameters);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserRepresentation getUserById(@PathVariable("id") BigInteger id) {
        UserDto userDto = userService.findById(id);


        return new UserRepresentation(userDto);
    }

    @PostMapping(value = "/{id}/orders")
    @ResponseStatus(HttpStatus.OK)
    public OrderRepresentation createOrder(@PathVariable("id") BigInteger id, @RequestBody OrderDto dto) {
        OrderDto orderDto = orderService.createOrder(id, dto);
        return new OrderRepresentation(orderDto);
    }

    @GetMapping(value = "/{id_user}/orders/{id_order}")
    @ResponseStatus(HttpStatus.OK)
    public OrderRepresentation getOrderById(
            @PathVariable("id_user") BigInteger userId, @PathVariable("id_order") BigInteger orderId) {
        OrderDto orderDto = orderService.findOrderById(userId, orderId);
        return new OrderRepresentation(orderDto);
    }

    @GetMapping(value = "/{id}/orders")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<OrderRepresentation> getOrdersByUserId(@PathVariable("id") BigInteger id,
                                                       @RequestParam(required = false, defaultValue = "1") Integer page,
                                                       @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                       @RequestParam(required = false) String sortType,
                                                       @RequestParam(required = false) String sortValue,
                                                       @RequestParam(required = false) String searchParameter,
                                                       @RequestParam(required = false) String searchValue) {
        RequestParameters requestParameters = new RequestParameters(page, pageSize, sortType, sortValue, searchParameter, searchValue);

        List<OrderRepresentation> links = orderService.findOrdersByUserId(id,requestParameters).stream().map(OrderRepresentation::new).collect(Collectors.toList());

        return orderLinkManager.createLinks(links, requestParameters);
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
