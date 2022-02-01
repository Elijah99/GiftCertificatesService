package com.epam.esm.hateoas;

import com.epam.esm.controller.TagsController;
import com.epam.esm.dto.RequestParameters;
import com.epam.esm.hateoas.representation.TagRepresentation;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagLinkManager implements HateoasLinkManager<TagRepresentation> {

    private static final int FIRST_PAGE = 1;
    private TagService service;

    @Override
    public PagedModel<TagRepresentation> createLinks(List<TagRepresentation> list, RequestParameters requestParameters) {
        if (list.isEmpty()) {
            return PagedModel.empty();
        }
        List<Link> links = new ArrayList<>();
        long page = requestParameters.getCurrentPage();
        long totalPages = service.countPages(requestParameters);
        long totalElements = service.count();
        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(requestParameters.getPageSize(), page, totalElements, totalPages);
        if (totalPages != 0) {
            Link firstPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagsController.class).
                    getAllTags(FIRST_PAGE,
                            requestParameters.getPageSize(),
                            requestParameters.getSortType(),
                            requestParameters.getSortValue(),
                            requestParameters.getSearchParameter(),
                            requestParameters.getSearchValue())).withRel("first");
            links.add(firstPageLink.expand());

            if (requestParameters.getCurrentPage() != 1) {
                long prevPage = requestParameters.getCurrentPage() - 1;
                Link prevPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagsController.class).
                        getAllTags((int) prevPage,
                                requestParameters.getPageSize(),
                                requestParameters.getSortType(),
                                requestParameters.getSortValue(),
                                requestParameters.getSearchParameter(),
                                requestParameters.getSearchValue())).withRel("prev");
                links.add(prevPageLink.expand());
            }

            Link selfRelLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagsController.class).
                    getAllTags(requestParameters.getCurrentPage(),
                            requestParameters.getPageSize(),
                            requestParameters.getSortType(),
                            requestParameters.getSortValue(),
                            requestParameters.getSearchParameter(),
                            requestParameters.getSearchValue())).withSelfRel();
            links.add(selfRelLink.expand());


            if (page != totalPages) {
                long nextPage = requestParameters.getCurrentPage() + 1;
                Link nextPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagsController.class).
                        getAllTags((int) nextPage,
                                requestParameters.getPageSize(),
                                requestParameters.getSortType(),
                                requestParameters.getSortValue(),
                                requestParameters.getSearchParameter(),
                                requestParameters.getSearchValue())).withRel("next");
                links.add(nextPageLink.expand());
            }

            Link lastPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagsController.class).
                    getAllTags((int) totalPages,
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
    public void setService(TagService service) {
        this.service = service;
    }
}
