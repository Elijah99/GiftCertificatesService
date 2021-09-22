package com.epam.esm.hateoas;

import com.epam.esm.controller.TagsController;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.enums.RequestParameters;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class TagLinkManager implements HateoasLinkManager<TagDto> {

    private TagsController controller = WebMvcLinkBuilder.methodOn(TagsController.class);
    private TagService service;

    @Override
    public CollectionModel<TagDto> createLinks(List<TagDto> list, RequestParameters requestParameters) {
        CollectionModel<TagDto> model = CollectionModel.of(list);
        list.forEach(this::createLinks);
        return model;
    }

    @Override
    public TagDto createLinks(TagDto tagDto) {
        BigInteger dtoId = tagDto.getId();
        Link dtoLink = WebMvcLinkBuilder.linkTo(controller.getTag(dtoId)).withSelfRel();
        tagDto.add(dtoLink);
        return tagDto;
    }

    @Autowired
    public void setService(TagService service) {
        this.service = service;
    }
}
