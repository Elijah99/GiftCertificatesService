package com.epam.esm.entity;

import java.math.BigInteger;

public class GiftCertificateTag extends Entity {

    private BigInteger idGiftCertificate;
    private BigInteger idTag;

    public GiftCertificateTag(BigInteger idGiftCertificate, BigInteger idTag) {
        this.idGiftCertificate = idGiftCertificate;
        this.idTag = idTag;
    }

    public GiftCertificateTag(BigInteger id, BigInteger idGiftCertificate, BigInteger idTag) {
        super(id);
        this.idGiftCertificate = idGiftCertificate;
        this.idTag = idTag;
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
