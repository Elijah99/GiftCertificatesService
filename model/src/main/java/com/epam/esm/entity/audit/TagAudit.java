package com.epam.esm.entity.audit;

import com.epam.esm.entity.Tag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "tag_audit")
public class TagAudit extends AbstractEntityAudit {

    @Column(name = "id_row")
    private Long idRow;
    @Column(name = "name")
    private String name;

    public TagAudit() {
    }

    public TagAudit(Tag tag, AuditOperationEnum operation) {
        super(operation);
        this.idRow = tag.getId();
        this.name = tag.getName();
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
        TagAudit tagAudit = (TagAudit) o;
        return name.equals(tagAudit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
