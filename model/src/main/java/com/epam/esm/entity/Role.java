package com.epam.esm.entity;

import com.epam.esm.exception.UnknownRoleException;

public enum Role {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public static Role getRole(String value) {
        if (value == null || value.isEmpty()) {
            throw new UnknownRoleException();
        }

        for (Role role : Role.values()) {
            if (value.equals(role.value)) {
                return role;
            }
        }
        throw new UnknownRoleException();
    }

    public String getValue() {
        return value;
    }
}
