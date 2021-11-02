package com.epam.esm.controller;

import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.RequestParameters;
import com.epam.esm.dto.TagDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.hateoas.OrderLinkManager;
import com.epam.esm.hateoas.UserLinkManager;
import com.epam.esm.hateoas.representation.OrderRepresentation;
import com.epam.esm.hateoas.representation.TagRepresentation;
import com.epam.esm.hateoas.representation.UserRepresentation;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.TagService;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides a centralized request handling
 * to Users and Users Orders resources
 *
 * @author Ilya Ramanouski
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private OrderService orderService;
    private TagService tagService;
    private UserLinkManager userLinkManager;
    private OrderLinkManager orderLinkManager;

    /**
     * Provides GET requests to the all Users with parameters
     * request examples:
     * .../users/  - returns all list of Users with default pagination
     * .../users?searchBy=column&value=val  - finds Users which contains value 'val' at column 'column'
     * .../users?sortBy=column&sortType=desc    - returns Users with default pagination sorted by column 'column' in descending order
     * .../users/page=1&pageSize=10  - returns first 10 records of Users
     *
     * @param page            requested page
     * @param pageSize        requested number of rows at page
     * @param sortType        asc/desc value for order rows
     * @param sortValue       column to sort rows
     * @param searchParameter column to search
     * @param searchValue     list of values to search
     * @return CollectionModel of UserRepresentation.
     */
    @GetMapping(produces = "application/hal+json")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<UserRepresentation> getAllUsers(@RequestParam(required = false, defaultValue = "1") int page,
                                                           @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                           @RequestParam(required = false) String sortType,
                                                           @RequestParam(required = false) String sortValue,
                                                           @RequestParam(required = false) String searchParameter,
                                                           @RequestParam(required = false) List<String> searchValue) {

        RequestParameters requestParameters = new RequestParameters(page, pageSize, sortType, sortValue, searchParameter, searchValue);

        List<UserRepresentation> links = userService.findAll(requestParameters).stream().map(UserRepresentation::new).collect(Collectors.toList());

        return userLinkManager.createLinks(links, requestParameters);
    }

    /**
     * Provides GET request to the one User by its id
     * request example:
     * .../users/1  -  returns User with id '1'
     *
     * @param id path variable,id of requested User
     * @return UserRepresentation if its presents
     */
    @GetMapping(value = "/{id}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.OK)
    public UserRepresentation getUserById(@PathVariable("id") Long id) {
        UserDto userDto = userService.findById(id);


        return new UserRepresentation(userDto);
    }

    /**
     * Provides POST request to add new Order
     * request example:
     * .../orders/  - saves new Order, requires request body in json format
     *
     * @param order OrderDto object bases on json object in request body
     * @return created OrderRepresentation
     */
    @PostMapping(value = "/{id}/orders")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.OK)
    public OrderRepresentation createOrder(@PathVariable("id") Long id, @RequestBody OrderDto order) {
        OrderDto orderDto = orderService.createOrder(id, order);
        return new OrderRepresentation(orderDto);
    }

    /**
     * Provides GET request to the one User's Order by its id
     * request example:
     * .../users/1/orders/1  -  returns Order with id '1' of User with id '1'
     *
     * @param userId  path variable,id of User
     * @param orderId path variable,id of requested Order
     * @return OrderRepresentation if its presents
     */
    @GetMapping(value = "/{id_user}/orders/{id_order}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.OK)
    public OrderRepresentation getOrderById(
            @PathVariable("id_user") Long userId, @PathVariable("id_order") Long orderId) {
        OrderDto orderDto = orderService.findOrderById(userId, orderId);
        return new OrderRepresentation(orderDto);
    }

    /**
     * Provides GET request to the all User's Orders with parameters
     * request example:
     * .../users/1/orders/  -  returns Orders of User with id '1' with default pagination
     * <p>
     * .../users/1/orders?searchBy=column&value=val  - finds Orders of User with id '1' which contains value 'val' at column 'column'
     * .../users/1/orders?sortBy=column&sortType=desc    - returns Orders of User with id '1' with default pagination
     * sorted by column 'column' in descending order
     * .../users/1/orders?page=1&pageSize=10  - returns first 10 records of Orders of User with id '1'
     *
     * @param id              path variable,id of User
     * @param page            requested page
     * @param pageSize        requested number of rows at page
     * @param sortType        asc/desc value for order rows
     * @param sortValue       column to sort rows
     * @param searchParameter column to search
     * @param searchValue     list of values to search
     * @return OrderRepresentation if its presents
     */
    @GetMapping(value = "/{id}/orders")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<OrderRepresentation> getOrdersByUserId(@PathVariable("id") Long id,
                                                                  @RequestParam(required = false, defaultValue = "1") Integer page,
                                                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                                  @RequestParam(required = false) String sortType,
                                                                  @RequestParam(required = false) String sortValue,
                                                                  @RequestParam(required = false) String searchParameter,
                                                                  @RequestParam(required = false) List<String> searchValue) {
        RequestParameters requestParameters = new RequestParameters(page, pageSize, sortType, sortValue, searchParameter, searchValue);

        List<OrderRepresentation> links = orderService.findOrdersByUserId(id, requestParameters).stream().map(OrderRepresentation::new).collect(Collectors.toList());

        return orderLinkManager.createLinks(links, requestParameters);
    }

    /**
     * Provides GET mapping to find most widely used
     * tag of a user with highest cost of all orders
     *
     * @return TagRepresentation if its present
     */
    @GetMapping(value = "/{id}/mostUsedTag")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.OK)
    public TagRepresentation getMostWidelyUsedTagOfAUserWithTheHighestCostOfAllOrders(@PathVariable("id") Long idUser) {
        TagDto tagDto = tagService.getMostWidelyUsedTagOfAUserWithTheHighestCostOfAllOrders(idUser);
        return new TagRepresentation(tagDto);
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

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }
}
