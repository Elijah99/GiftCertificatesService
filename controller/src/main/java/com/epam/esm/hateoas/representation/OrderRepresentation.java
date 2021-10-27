package com.epam.esm.hateoas.representation;

import com.epam.esm.controller.UserController;
import com.epam.esm.dto.OrderDto;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class OrderRepresentation extends RepresentationModel<OrderRepresentation> implements Serializable {

    @Transient
    private final UserController controller = methodOn(UserController.class);

    private Long id;
    private BigDecimal cost;
    private LocalDateTime purchaseDate;
    private List<GiftCertificateRepresentation> giftCertificates = new ArrayList<>();
    private Long idUser;

    public OrderRepresentation() {
        createLinks();
    }

    public OrderRepresentation(OrderDto orderDto) {
        this.id = orderDto.getId();
        this.cost = orderDto.getCost();
        this.purchaseDate = orderDto.getPurchaseDate();
        this.idUser = orderDto.getIdUser();
        if (orderDto.getGiftCertificates() != null) {
            this.giftCertificates = orderDto.getGiftCertificates().stream().map(GiftCertificateRepresentation::new).collect(Collectors.toList());
        }
        createLinks();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<GiftCertificateRepresentation> getGiftCertificates() {
        return giftCertificates;
    }

    public void setGiftCertificates(List<GiftCertificateRepresentation> giftCertificates) {
        this.giftCertificates = giftCertificates;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderRepresentation orderRepresentation = (OrderRepresentation) o;
        return Objects.equals(id, orderRepresentation.id) &&
                Objects.equals(cost, orderRepresentation.cost) &&
                Objects.equals(purchaseDate, orderRepresentation.purchaseDate) &&
                Objects.equals(giftCertificates, orderRepresentation.giftCertificates) &&
                Objects.equals(idUser, orderRepresentation.idUser);
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

    private void createLinks() {

//        Link dtoLink = WebMvcLinkBuilder.linkTo(controller.getOrderById(idUser, id)).withSelfRel();
//        Link ordersLink =
//                WebMvcLinkBuilder.linkTo(controller.getOrdersByUserId(idUser,
//                        null,
//                        null,
//                        null,
//                        null,
//                        null,
//                        null))
//                        .withRel("orders");
//
//        Link addLink = WebMvcLinkBuilder.linkTo(controller.createOrder(idUser, new OrderDto())).withRel("create");
//        add(dtoLink, addLink, ordersLink);
    }
}
