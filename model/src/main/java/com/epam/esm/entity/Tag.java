package com.epam.esm.entity;

import java.math.BigInteger;

public class Tag extends Entity {

    private String name;

    public Tag() {
    }

    public Tag(BigInteger id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
