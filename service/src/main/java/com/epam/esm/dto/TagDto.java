package com.epam.esm.dto;

import org.springframework.hateoas.RepresentationModel;

import java.math.BigInteger;

public class TagDto extends RepresentationModel<TagDto> {

    private BigInteger id;
    private String name;

    public TagDto() {
    }

    public TagDto(String name) {
        this.name = name;
    }

    public TagDto(BigInteger id, String name) {
        this.id = id;
        this.name = name;
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
}
