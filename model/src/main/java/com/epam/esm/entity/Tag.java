package com.epam.esm.entity;

import com.epam.esm.listener.TagAuditListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Tag")
@EntityListeners(TagAuditListener.class)
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "tag",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<GiftCertificateTag> giftCertificateTags = new ArrayList<>();

    public Tag() {
    }

    public Tag(BigInteger id, String name) {
        this.id = id;
        this.name = name;
    }

    public Tag(BigInteger id, String name, List<GiftCertificateTag> giftCertificateTags) {
        this.id = id;
        this.name = name;
        this.giftCertificateTags = giftCertificateTags;
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

    public List<GiftCertificateTag> getGiftCertificateTags() {
        return giftCertificateTags;
    }

    public void setGiftCertificateTags(List<GiftCertificateTag> giftCertificateTags) {
        this.giftCertificateTags = giftCertificateTags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) &&
                Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
