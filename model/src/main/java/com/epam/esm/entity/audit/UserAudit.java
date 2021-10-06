package com.epam.esm.entity.audit;

import com.epam.esm.entity.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "user_audit", schema = "public")
public class UserAudit extends AbstractEntityAudit {

    @Column(name = "id_row")
    private BigInteger idRow;
    @Column(name = "name")
    private String name;

    public UserAudit() {
    }

    public UserAudit(User user, AuditOperationEnum operation) {
        super(operation);
        this.idRow = user.getId();
        this.name = user.getName();
    }

    public BigInteger getIdRow() {
        return idRow;
    }

    public void setIdRow(BigInteger id) {
        this.idRow = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAudit userAudit = (UserAudit) o;
        return Objects.equals(idRow, userAudit.idRow) &&
                Objects.equals(name, userAudit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRow, name);
    }
}
