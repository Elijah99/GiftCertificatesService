package com.epam.esm.entity.audit;

import com.epam.esm.entity.Order;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "order_audit", schema = "public")
public class OrderAudit extends AbstractEntityAudit{

    @Column(name = "id_row")
    private BigInteger idRow;
    @Column(name = "cost")
    private BigDecimal cost;
    @Column(name = "purchase_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime purchaseDate;


    public OrderAudit() {
    }

    public OrderAudit(Order order, AuditOperationEnum operation) {
        super(operation);
        this.idRow = order.getId();
        this.cost = order.getCost();
        this.purchaseDate = order.getPurchaseDate();
    }

    public BigInteger getIdRow() {
        return idRow;
    }

    public void setIdRow(BigInteger id) {
        this.idRow = id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderAudit orderAudit = (OrderAudit) o;
        return Objects.equals(idRow, orderAudit.idRow) &&
                Objects.equals(cost, orderAudit.cost) &&
                Objects.equals(purchaseDate, orderAudit.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRow, cost, purchaseDate);
    }

    @Override
    public String toString() {
        return "OrderAudit{" +
                "idRow=" + idRow +
                ", cost=" + cost +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
