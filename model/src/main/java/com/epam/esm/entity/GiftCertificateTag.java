package com.epam.esm.entity;

import com.epam.esm.listener.GiftCertificateAuditListener;
import com.epam.esm.listener.GiftCertificateTagAuditListener;

import javax.persistence.*;
import javax.persistence.Entity;
import java.math.BigInteger;

@Entity
@EntityListeners(GiftCertificateTagAuditListener.class)
@Table(name = "gift_certificate_tag")
public class GiftCertificateTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;
    @Column(name = "id_gift_certificate")
    private BigInteger idGiftCertificate;
    @Column(name = "id_tag")
    private BigInteger idTag;

    public GiftCertificateTag() {
    }

    public GiftCertificateTag(BigInteger idGiftCertificate, BigInteger idTag) {
        this.idGiftCertificate = idGiftCertificate;
        this.idTag = idTag;
    }

    public GiftCertificateTag(BigInteger id, BigInteger idGiftCertificate, BigInteger idTag) {
        this.id = id;
        this.idGiftCertificate = idGiftCertificate;
        this.idTag = idTag;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getIdGiftCertificate() {
        return idGiftCertificate;
    }

    public void setIdGiftCertificate(BigInteger idGiftCertificate) {
        this.idGiftCertificate = idGiftCertificate;
    }

    public BigInteger getIdTag() {
        return idTag;
    }

    public void setIdTag(BigInteger idTag) {
        this.idTag = idTag;
    }
}
