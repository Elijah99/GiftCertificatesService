package com.epam.esm.enums;

public enum SearchParameter {

    name("name"),
    id("id"),
    tag("tag"),
    description("description");

    public final String value;

    private SearchParameter(String value) {
        this.value = value;
    }
}
