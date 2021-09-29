package com.epam.esm.entity;

import com.epam.esm.listener.GiftCertificateAuditListener;
import com.epam.esm.listener.TagAuditListener;

import javax.persistence.Entity;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@EntityListeners(TagAuditListener.class)
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;
    @Column(name = "name")
    private String name;

    public Tag() {
    }

    public Tag(BigInteger id, String name) {
        this.id = id;
        this.name = name;
    }

    public Tag(String name) {
        this.name = name;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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
        Tag tag = (Tag) o;
        return name.equals(tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
