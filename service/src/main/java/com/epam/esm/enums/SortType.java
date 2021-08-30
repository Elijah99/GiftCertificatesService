package com.epam.esm.enums;

public enum SortType {
    asc("asc"),
    desc("desc");

    public final String value;

    private SortType(String value) {
        this.value = value;
    }
}
