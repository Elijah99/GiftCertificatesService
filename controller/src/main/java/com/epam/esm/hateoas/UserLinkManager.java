package com.epam.esm.hateoas;

import com.epam.esm.controller.UserController;
import com.epam.esm.dto.UserDto;
import com.epam.esm.enums.RequestParameters;
import com.epam.esm.hateoas.representation.UserRepresentation;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class UserLinkManager implements HateoasLinkManager<UserRepresentation> {

    private static final int FIRST_PAGE = 1;
    private UserController usersController =
            WebMvcLinkBuilder.methodOn(UserController.class);
    private OrderLinkManager orderLinkManager;
    private UserService userService;

    @Override
    public CollectionModel<UserRepresentation> createLinks(List<UserRepresentation> list, RequestParameters requestParameters) {
        CollectionModel<UserRepresentation> model = CollectionModel.of(list);
        if(list.isEmpty()){
            return model;
        }
        BigInteger userId = list.get(0).getId();
        int page = requestParameters.getCurrentPage();
        int pageAmount = (int) userService.count(userId, requestParameters);
        if (pageAmount != 0) {
            Link firstPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
                    getAllUsers(FIRST_PAGE,
                            requestParameters.getPageSize(),
                            requestParameters.getSortType(),
                            requestParameters.getSortValue(),
                            requestParameters.getSearchParameter(),
                            requestParameters.getSearchValue())).withRel("first");
            model.add(firstPageLink.expand());

            if (requestParameters.getCurrentPage() != 1) {
                int prevPage = requestParameters.getCurrentPage() - 1;
                Link prevPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
                        getAllUsers(prevPage,
                                requestParameters.getPageSize(),
                                requestParameters.getSortType(),
                                requestParameters.getSortValue(),
                                requestParameters.getSearchParameter(),
                                requestParameters.getSearchValue())).withRel("prev");
                model.add(prevPageLink.expand());
            }

            Link selfRelLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
                    getAllUsers(requestParameters.getCurrentPage(),
                            requestParameters.getPageSize(),
                            requestParameters.getSortType(),
                            requestParameters.getSortValue(),
                            requestParameters.getSearchParameter(),
                            requestParameters.getSearchValue())).withSelfRel();
            model.add(selfRelLink.expand());

            if (page != pageAmount) {
                int nextPage = requestParameters.getCurrentPage() + 1;
                Link nextPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
                        getAllUsers(nextPage,
                                requestParameters.getPageSize(),
                                requestParameters.getSortType(),
                                requestParameters.getSortValue(),
                                requestParameters.getSearchParameter(),
                                requestParameters.getSearchValue())).withRel("next");
                model.add(nextPageLink.expand());
            }

            Link lastPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
                    getAllUsers(pageAmount,
                            requestParameters.getPageSize(),
                            requestParameters.getSortType(),
                            requestParameters.getSortValue(),
                            requestParameters.getSearchParameter(),
                            requestParameters.getSearchValue())).withRel("last");
            model.add(lastPageLink.expand());

        }

        return model;
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
