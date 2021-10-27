package com.epam.esm.entity.audit;

import com.epam.esm.entity.GiftCertificateTag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "gift_certificate_tag_audit", schema = "public")
public class GiftCertificateTagAudit extends AbstractEntityAudit {

    @Column(name = "id_row")
    private Long idRow;
    @Column(name = "id_gift_certificate")
    private Long idGiftCertificate;
    @Column(name = "id_tag")
    private Long idTag;

    public GiftCertificateTagAudit() {
    }

    public GiftCertificateTagAudit(GiftCertificateTag giftCertificateTag, AuditOperationEnum operation) {
        super(operation);
        this.idRow = giftCertificateTag.getId();
        if (giftCertificateTag.getGiftCertificate() != null) {
            this.idGiftCertificate = giftCertificateTag.getGiftCertificate().getId();
        }
        if (giftCertificateTag.getTag() != null) {
            this.idTag = giftCertificateTag.getTag().getId();
        }
    }

    public Long getIdRow() {
        return idRow;
    }

    public void setIdRow(Long id) {
        this.idRow = id;
    }

    public Long getIdGiftCertificate() {
        return idGiftCertificate;
    }

    public void setIdGiftCertificate(Long idGiftCertificate) {
        this.idGiftCertificate = idGiftCertificate;
    }

    public Long getIdTag() {
        return idTag;
    }

    public void setIdTag(Long idTag) {
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
