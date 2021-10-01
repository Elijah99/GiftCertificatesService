package com.epam.esm.hateoas.representation;

import com.epam.esm.controller.TagsController;
import com.epam.esm.dto.TagDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.io.Serializable;
import java.math.BigInteger;

public class TagRepresentation extends RepresentationModel<TagRepresentation> implements Serializable {

    private TagsController controller = WebMvcLinkBuilder.methodOn(TagsController.class);

    private BigInteger id;
    private String name;

    public TagRepresentation() {
        createLinks();
    }

    public TagRepresentation(TagDto tagDto) {
        this.id = tagDto.getId();
        this.name = tagDto.getName();

        createLinks();
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TagDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    private void createLinks(){
        Link selfRel = WebMvcLinkBuilder.linkTo(controller.getTag(getId())).withSelfRel();
        add(selfRel);
    }
}
