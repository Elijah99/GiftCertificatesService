package com.epam.esm.service.enums;

public enum SortType {
    asc("asc"),
    desc("desc");

    public final String value;

    private SortType(String value) {
        this.value = value;
    }
}
