package com.epam.esm.enums;

public enum SortType {
    asc("asc"),
    desc("desc");

    public final String value;

    SortType(String value) {
        this.value = value;
    }
}
