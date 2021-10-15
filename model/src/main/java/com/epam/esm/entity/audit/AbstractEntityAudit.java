package com.epam.esm.entity.audit;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractEntityAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;
    @Enumerated(EnumType.STRING)
    @Column(name = "operation")
    private AuditOperationEnum operation;
    @Column(name = "audit_date")
    private LocalDateTime auditDate;

    public AbstractEntityAudit(AuditOperationEnum operation) {
        this.operation = operation;
        this.auditDate = LocalDateTime.now();
    }

    public AbstractEntityAudit() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public AuditOperationEnum getOperation() {
        return operation;
    }

    public void setOperation(AuditOperationEnum operation) {
        this.operation = operation;
    }

    public LocalDateTime getAuditDate() {
        return auditDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntityAudit that = (AbstractEntityAudit) o;
        return Objects.equals(id, that.id) &&
                operation == that.operation &&
                Objects.equals(auditDate, that.auditDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, operation, auditDate);
    }

    @Override
    public String toString() {
        return "AbstractEntityAudit{" +
                "id=" + id +
                ", operation=" + operation +
                ", auditDate=" + auditDate +
                '}';
    }
}
