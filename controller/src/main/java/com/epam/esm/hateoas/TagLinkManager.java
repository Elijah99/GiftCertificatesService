package com.epam.esm.hateoas;

import com.epam.esm.controller.TagsController;
import com.epam.esm.dto.RequestParameters;
import com.epam.esm.hateoas.representation.TagRepresentation;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagLinkManager implements HateoasLinkManager<TagRepresentation> {

    private static final int FIRST_PAGE = 1;

    private final TagsController controller = WebMvcLinkBuilder.methodOn(TagsController.class);
    private TagService service;

    @Override
    public CollectionModel<TagRepresentation> createLinks(List<TagRepresentation> list, RequestParameters requestParameters) {
        CollectionModel<TagRepresentation> model = CollectionModel.of(list);
        if (list.isEmpty()) {
            return model;
        }
        int page = requestParameters.getCurrentPage();
        int pageAmount = (int) service.countPages(requestParameters);
        if (pageAmount != 0) {
            Link firstPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagsController.class).
                    getAllTags(FIRST_PAGE,
                            requestParameters.getPageSize(),
                            requestParameters.getSortType(),
                            requestParameters.getSortValue(),
                            requestParameters.getSearchParameter(),
                            requestParameters.getSearchValue())).withRel("first");
            model.add(firstPageLink.expand());

            if (requestParameters.getCurrentPage() != 1) {
                int prevPage = requestParameters.getCurrentPage() - 1;
                Link prevPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagsController.class).
                        getAllTags(
                                prevPage,
                                requestParameters.getPageSize(),
                                requestParameters.getSortType(),
                                requestParameters.getSortValue(),
                                requestParameters.getSearchParameter(),
                                requestParameters.getSearchValue())).withRel("prev");
                model.add(prevPageLink.expand());
            }

            Link selfRelLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagsController.class).
                    getAllTags(requestParameters.getCurrentPage(),
                            requestParameters.getPageSize(),
                            requestParameters.getSortType(),
                            requestParameters.getSortValue(),
                            requestParameters.getSearchParameter(),
                            requestParameters.getSearchValue())).withSelfRel();
            model.add(selfRelLink.expand());


            if (page != pageAmount) {
                int nextPage = requestParameters.getCurrentPage() + 1;
                Link nextPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagsController.class).
                        getAllTags(nextPage,
                                requestParameters.getPageSize(),
                                requestParameters.getSortType(),
                                requestParameters.getSortValue(),
                                requestParameters.getSearchParameter(),
                                requestParameters.getSearchValue())).withRel("next");
                model.add(nextPageLink.expand());
            }

            Link lastPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagsController.class).
                    getAllTags(pageAmount,
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
    public void setService(TagService service) {
        this.service = service;
    }
}
