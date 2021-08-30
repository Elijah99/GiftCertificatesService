package com.epam.esm.enums;

public enum SortParameter {
    name("name"),
    id("id"),
    description("description");

    public final String value;

    private SortParameter(String value) {
        this.value = value;
    }
}
