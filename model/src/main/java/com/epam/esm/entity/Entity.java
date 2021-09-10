package com.epam.esm.entity;

import java.io.Serializable;
import java.math.BigInteger;

public abstract class Entity implements Serializable, Cloneable {

    private BigInteger id;

    public Entity(BigInteger id) {
        this.id = id;
    }

    public Entity() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
}
