package com.epam.esm.entity.audit;

import com.epam.esm.entity.Role;
import com.epam.esm.entity.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "user_audit", schema = "public")
public class UserAudit extends AbstractEntityAudit {

    @Column(name = "id_row")
    private Long idRow;
    @Column(name = "name")
    private String name;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private Role role;

    public UserAudit() {
    }

    public UserAudit(User user, AuditOperationEnum operation) {
        super(operation);
        this.idRow = user.getId();
        this.name = user.getName();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

    public Long getIdRow() {
        return idRow;
    }

    public void setIdRow(Long id) {
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
                Objects.equals(name, userAudit.name) &&
                Objects.equals(login, userAudit.login) &&
                Objects.equals(password, userAudit.password) &&
                Objects.equals(role, userAudit.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRow, name, login, password, role);
    }
}
