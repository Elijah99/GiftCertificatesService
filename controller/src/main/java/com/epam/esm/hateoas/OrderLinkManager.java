package com.epam.esm.hateoas;

import com.epam.esm.controller.UserController;
import com.epam.esm.dto.RequestParameters;
import com.epam.esm.hateoas.representation.OrderRepresentation;
import com.epam.esm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderLinkManager implements HateoasLinkManager<OrderRepresentation> {

    private static final int FIRST_PAGE = 1;
    private OrderService service;

    @Override
    public PagedModel<OrderRepresentation> createLinks(List<OrderRepresentation> list, RequestParameters requestParameters) {
        if (list.isEmpty()) {
            return PagedModel.empty();
        }
        Long userId = new Long(list.get(0).getIdUser().toString());
        List<Link> links = new ArrayList<>();
        long page = requestParameters.getCurrentPage();
        long totalPages = service.countPages(userId, requestParameters);
        long totalElements = service.count(userId);
        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(requestParameters.getPageSize(), page, totalElements, totalPages);
        if (totalPages != 0) {
            Link firstPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
                    getOrdersByUserId(userId,
                            FIRST_PAGE,
                            requestParameters.getPageSize(),
                            requestParameters.getSortType(),
                            requestParameters.getSortValue(),
                            requestParameters.getSearchParameter(),
                            requestParameters.getSearchValue())).withRel("first");
            links.add(firstPageLink.expand());

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
                links.add(prevPageLink.expand());
            }

            Link selfRelLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
                    getOrdersByUserId(userId,
                            requestParameters.getCurrentPage(),
                            requestParameters.getPageSize(),
                            requestParameters.getSortType(),
                            requestParameters.getSortValue(),
                            requestParameters.getSearchParameter(),
                            requestParameters.getSearchValue())).withSelfRel();
            links.add(selfRelLink.expand());


            if (page != totalPages) {
                int nextPage = requestParameters.getCurrentPage() + 1;
                Link nextPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
                        getOrdersByUserId(userId,
                                nextPage,
                                requestParameters.getPageSize(),
                                requestParameters.getSortType(),
                                requestParameters.getSortValue(),
                                requestParameters.getSearchParameter(),
                                requestParameters.getSearchValue())).withRel("next");
                links.add(nextPageLink.expand());
            }

            Link lastPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
                    getOrdersByUserId(userId,
                            (int) totalPages,
                            requestParameters.getPageSize(),
                            requestParameters.getSortType(),
                            requestParameters.getSortValue(),
                            requestParameters.getSearchParameter(),
                            requestParameters.getSearchValue())).withRel("last");
            links.add(lastPageLink.expand());


        }
        return PagedModel.of(list, metadata, links);
    }


    @Autowired
    public void setService(OrderService service) {
        this.service = service;
    }
}
