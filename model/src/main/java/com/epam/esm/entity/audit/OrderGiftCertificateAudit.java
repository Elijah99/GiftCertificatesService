package com.epam.esm.entity.audit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "order_gift_certificate_audit", schema = "public")
public class OrderGiftCertificateAudit extends AbstractEntityAudit{

    @Column(name = "id_row")
    private BigInteger idRow;
    @Column(name = "id_order")
    private BigInteger idOrder;
    @Column(name = "id_gift_certificate")
    private BigInteger idGiftCertificate;

    public BigInteger getIdRow() {
        return idRow;
    }

    public void setIdRow(BigInteger idRow) {
        this.idRow = idRow;
    }

    public BigInteger getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(BigInteger idOrder) {
        this.idOrder = idOrder;
    }

    public BigInteger getIdGiftCertificate() {
        return idGiftCertificate;
    }

    public void setIdGiftCertificate(BigInteger idGiftCertificate) {
        this.idGiftCertificate = idGiftCertificate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderGiftCertificateAudit that = (OrderGiftCertificateAudit) o;
        return Objects.equals(idRow, that.idRow) &&
                Objects.equals(idOrder, that.idOrder) &&
                Objects.equals(idGiftCertificate, that.idGiftCertificate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idRow, idOrder, idGiftCertificate);
    }
}
