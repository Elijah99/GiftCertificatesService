package com.epam.esm.hateoas;

import com.epam.esm.controller.TagsController;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.enums.RequestParameters;
import com.epam.esm.hateoas.representation.TagRepresentation;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class TagLinkManager implements HateoasLinkManager<TagRepresentation> {

    private TagsController controller = WebMvcLinkBuilder.methodOn(TagsController.class);
    private TagService service;

    @Override
    public CollectionModel<TagRepresentation> createLinks(List<TagRepresentation> list, RequestParameters requestParameters) {
        CollectionModel<TagRepresentation> model = CollectionModel.of(list);

        return model;
    }

    @Autowired
    public void setService(TagService service) {
        this.service = service;
    }
}
