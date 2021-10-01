package com.epam.esm.entity.audit;

import com.epam.esm.entity.GiftCertificateTag;
import com.epam.esm.listener.GiftCertificateTagAuditListener;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "gift_certificate_tag_audit", schema = "public")
public class GiftCertificateTagAudit extends AbstractEntityAudit {

    @Column(name = "id_row")
    private BigInteger idRow;
    @Column(name = "id_gift_certificate")
    private BigInteger idGiftCertificate;
    @Column(name = "id_tag")
    private BigInteger idTag;

    public GiftCertificateTagAudit() {
    }

    public GiftCertificateTagAudit(GiftCertificateTag giftCertificateTag, AuditOperationEnum operation) {
        super(operation);
        this.idRow = giftCertificateTag.getId();
        this.idGiftCertificate = giftCertificateTag.getGiftCertificate().getId();
        this.idTag = giftCertificateTag.getTag().getId();
    }

    public BigInteger getIdRow() {
        return idRow;
    }

    public void setIdRow(BigInteger id) {
        this.idRow = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftCertificateTagAudit that = (GiftCertificateTagAudit) o;
        return Objects.equals(idRow, that.idRow) &&
                Objects.equals(idGiftCertificate, that.idGiftCertificate) &&
                Objects.equals(idTag, that.idTag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRow, idGiftCertificate, idTag);
    }
}
