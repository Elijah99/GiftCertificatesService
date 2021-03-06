package com.epam.esm.hateoas.representation;

import com.epam.esm.controller.UserController;
import com.epam.esm.dto.UserDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserRepresentation extends RepresentationModel<UserRepresentation> implements Serializable {

    private final UserController usersController = WebMvcLinkBuilder.methodOn(UserController.class);

    private Long id;
    private String name;
    private String login;
    private List<OrderRepresentation> orders = new ArrayList<>();

    public UserRepresentation() {
        createLinks();
    }

    public UserRepresentation(UserDto userDto) {
        this.id = userDto.getId();
        this.name = userDto.getName();
        this.login = userDto.getLogin();
        if (userDto.getOrders() != null) {
            this.orders = userDto.getOrders().stream().map(OrderRepresentation::new).collect(Collectors.toList());
        }

        createLinks();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<OrderRepresentation> getOrders() {
        return orders;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRepresentation userRepresentation = (UserRepresentation) o;
        return Objects.equals(id, userRepresentation.id) &&
                Objects.equals(name, userRepresentation.name) &&
                Objects.equals(orders, userRepresentation.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    private void createLinks() {
        Link selfLink = WebMvcLinkBuilder.linkTo(usersController.getUserById(getId())).withSelfRel();
        Link ordersLink =
                WebMvcLinkBuilder.linkTo(usersController.getOrdersByUserId(getId(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null)).withRel("orders");
        Link userTagLink =
                WebMvcLinkBuilder.linkTo(
                                usersController.getMostWidelyUsedTagOfAUserWithTheHighestCostOfAllOrders(getId()))
                        .withRel("most_used_tag");
        add(selfLink, ordersLink, userTagLink);
    }
}
