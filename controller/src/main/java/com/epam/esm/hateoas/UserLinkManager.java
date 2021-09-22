package com.epam.esm.hateoas;

import com.epam.esm.controller.UserController;
import com.epam.esm.dto.UserDto;
import com.epam.esm.enums.RequestParameters;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class UserLinkManager implements HateoasLinkManager<UserDto> {

    private static final int FIRST_PAGE = 1;
    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_PAGE_SIZE = 1;
    private UserController usersController =
            WebMvcLinkBuilder.methodOn(UserController.class);
    private OrderLinkManager orderLinkManager;
    private UserService userService;

    @Override
    public CollectionModel<UserDto> createLinks(List<UserDto> list, RequestParameters requestParameters) {
        CollectionModel<UserDto> model = CollectionModel.of(list);
        model.forEach(this::createLinks);
        return model;
    }

    @Override
    public UserDto createLinks(UserDto userDto) {
        BigInteger idUser = userDto.getId();
        Link selfLink = WebMvcLinkBuilder.linkTo(usersController.getUserById(idUser)).withSelfRel();
        Link ordersLink =
                WebMvcLinkBuilder.linkTo(usersController.getOrdersByUserId(idUser,null,null,null,null,null,null)).withRel("orders");
        Link userTagLink =
                WebMvcLinkBuilder.linkTo(
                        usersController.getTheMostWidelyUsedTagOfUserWithTheHighestCostOfAllOrders(idUser))
                        .withRel("most_used_tag");
        userDto.add(selfLink, ordersLink, userTagLink);
        userDto.getOrders().forEach(orderLinkManager::createLinks);
        return userDto;
    }

    @Autowired
    public void setOrderLinkManager(OrderLinkManager orderLinkManager) {
        this.orderLinkManager = orderLinkManager;
    }

    @Autowired
    public void setService(UserService userService) {
        this.userService = userService;
    }
}
