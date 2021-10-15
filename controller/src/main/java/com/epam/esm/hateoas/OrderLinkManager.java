package com.epam.esm.hateoas;

import com.epam.esm.controller.UserController;
import com.epam.esm.dto.RequestParameters;
import com.epam.esm.hateoas.representation.OrderRepresentation;
import com.epam.esm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class OrderLinkManager implements HateoasLinkManager<OrderRepresentation> {

    private static final int FIRST_PAGE = 1;
    private OrderService service;

    @Override
    public CollectionModel<OrderRepresentation> createLinks(List<OrderRepresentation> list, RequestParameters requestParameters) {
        CollectionModel<OrderRepresentation> model = CollectionModel.of(list);
        if (list.isEmpty()) {
            return model;
        }
        BigInteger userId = list.get(0).getIdUser();
        int page = requestParameters.getCurrentPage();
        int pageAmount = (int) service.countPages(userId, requestParameters);
        if (pageAmount != 0) {
            Link firstPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
                    getOrdersByUserId(userId,
                            FIRST_PAGE,
                            requestParameters.getPageSize(),
                            requestParameters.getSortType(),
                            requestParameters.getSortValue(),
                            requestParameters.getSearchParameter(),
                            requestParameters.getSearchValue())).withRel("first");
            model.add(firstPageLink.expand());

            if (requestParameters.getCurrentPage() != 1) {
                int prevPage = requestParameters.getCurrentPage() - 1;
                Link prevPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
                        getOrdersByUserId(userId,
                                prevPage,
                                requestParameters.getPageSize(),
                                requestParameters.getSortType(),
                                requestParameters.getSortValue(),
                                requestParameters.getSearchParameter(),
                                requestParameters.getSearchValue())).withRel("prev");
                model.add(prevPageLink.expand());
            }

            Link selfRelLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
                    getOrdersByUserId(userId,
                            requestParameters.getCurrentPage(),
                            requestParameters.getPageSize(),
                            requestParameters.getSortType(),
                            requestParameters.getSortValue(),
                            requestParameters.getSearchParameter(),
                            requestParameters.getSearchValue())).withSelfRel();
            model.add(selfRelLink.expand());


            if (page != pageAmount) {
                int nextPage = requestParameters.getCurrentPage() + 1;
                Link nextPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
                        getOrdersByUserId(userId,
                                nextPage,
                                requestParameters.getPageSize(),
                                requestParameters.getSortType(),
                                requestParameters.getSortValue(),
                                requestParameters.getSearchParameter(),
                                requestParameters.getSearchValue())).withRel("next");
                model.add(nextPageLink.expand());
            }

            Link lastPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
                    getOrdersByUserId(userId,
                            pageAmount,
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
    public void setService(OrderService service) {
        this.service = service;
    }
}
