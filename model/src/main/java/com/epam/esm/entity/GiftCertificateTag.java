package com.epam.esm.entity;

import com.epam.esm.listener.GiftCertificateTagAuditListener;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity(name = "GiftCertificateTag")
@EntityListeners(GiftCertificateTagAuditListener.class)
@Table(name = "gift_certificate_tag")
public class GiftCertificateTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_gift_certificate")
    @JsonBackReference
    private GiftCertificate giftCertificate;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_tag")
    @JsonBackReference
    private Tag tag;

    public GiftCertificateTag() {
    }

    public GiftCertificateTag(GiftCertificate giftCertificate, Tag tag) {
        this.giftCertificate = giftCertificate;
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GiftCertificate getGiftCertificate() {
        return giftCertificate;
    }

    public void setGiftCertificate(GiftCertificate giftCertificate) {
        this.giftCertificate = giftCertificate;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftCertificateTag that = (GiftCertificateTag) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(giftCertificate, that.giftCertificate) &&
                Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, giftCertificate, tag);
    }
}
