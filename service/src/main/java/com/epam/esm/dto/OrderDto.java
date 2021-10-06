package com.epam.esm.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderDto implements Serializable {

    private BigInteger id;
    private BigDecimal cost;
    private LocalDateTime purchaseDate;
    private List<GiftCertificateDto> giftCertificates = new ArrayList<>();
    private BigInteger idUser;

    public OrderDto() {
    }

    public OrderDto(BigInteger id, BigDecimal cost, LocalDateTime purchaseDate, List<GiftCertificateDto> giftCertificates, BigInteger idUser) {
        this.id = id;
        this.cost = cost;
        this.purchaseDate = purchaseDate;
        this.giftCertificates = giftCertificates;
        this.idUser = idUser;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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

    public List<GiftCertificateDto> getGiftCertificates() {
        return giftCertificates;
    }

    public void setGiftCertificates(List<GiftCertificateDto> giftCertificates) {
        this.giftCertificates = giftCertificates;
    }

    public BigInteger getIdUser() {
        return idUser;
    }

    public void setIdUser(BigInteger idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(id, orderDto.id) &&
                Objects.equals(cost, orderDto.cost) &&
                Objects.equals(purchaseDate, orderDto.purchaseDate) &&
                Objects.equals(giftCertificates, orderDto.giftCertificates) &&
                Objects.equals(idUser, orderDto.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cost, purchaseDate, giftCertificates, idUser);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", cost=" + cost +
                ", purchaseDate=" + purchaseDate +
                ", giftCertificates=" + giftCertificates +
                ", idUser=" + idUser +
                '}';
    }
}
