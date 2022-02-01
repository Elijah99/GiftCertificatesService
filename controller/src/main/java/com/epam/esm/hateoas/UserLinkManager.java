package com.epam.esm.hateoas;

import com.epam.esm.controller.UserController;
import com.epam.esm.dto.RequestParameters;
import com.epam.esm.hateoas.representation.UserRepresentation;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserLinkManager implements HateoasLinkManager<UserRepresentation> {

    private static final int FIRST_PAGE = 1;
    private UserService userService;

    @Override
    public PagedModel<UserRepresentation> createLinks(List<UserRepresentation> list, RequestParameters requestParameters) {
        if (list.isEmpty()) {
            return PagedModel.empty();
        }
        List<Link> links = new ArrayList<>();
        long page = requestParameters.getCurrentPage();
        long totalElements = list.size();
        long totalPages = countPages(totalElements, requestParameters.getPageSize());
        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(requestParameters.getPageSize(), page, totalElements, totalPages);
        if (totalPages != 0) {
            Link firstPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
                    getAllUsers(FIRST_PAGE,
                            requestParameters.getPageSize(),
                            requestParameters.getSortType(),
                            requestParameters.getSortValue(),
                            requestParameters.getSearchParameter(),
                            requestParameters.getSearchValue())).withRel("first");
            links.add(firstPageLink.expand());

            if (requestParameters.getCurrentPage() != 1) {
                long prevPage = requestParameters.getCurrentPage() - 1;
                Link prevPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
                        getAllUsers((int) prevPage,
                                requestParameters.getPageSize(),
                                requestParameters.getSortType(),
                                requestParameters.getSortValue(),
                                requestParameters.getSearchParameter(),
                                requestParameters.getSearchValue())).withRel("prev");
                links.add(prevPageLink.expand());
            }

            Link selfRelLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
                    getAllUsers(requestParameters.getCurrentPage(),
                            requestParameters.getPageSize(),
                            requestParameters.getSortType(),
                            requestParameters.getSortValue(),
                            requestParameters.getSearchParameter(),
                            requestParameters.getSearchValue())).withSelfRel();
            links.add(selfRelLink.expand());

            if (page != totalPages) {
                long nextPage = requestParameters.getCurrentPage() + 1;
                Link nextPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
                        getAllUsers((int) nextPage,
                                requestParameters.getPageSize(),
                                requestParameters.getSortType(),
                                requestParameters.getSortValue(),
                                requestParameters.getSearchParameter(),
                                requestParameters.getSearchValue())).withRel("next");
                links.add(nextPageLink.expand());
            }

            Link lastPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
                    getAllUsers((int) totalPages,
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
    public void setService(UserService userService) {
        this.userService = userService;
    }
}
