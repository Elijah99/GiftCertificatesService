package com.epam.esm.entity.audit;

public enum AuditOperationEnum {
    INSERT("INSERT"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    public final String value;

    AuditOperationEnum(String value) {
        this.value = value;
    }
}
