package com.epam.esm.hateoas.representation;


import com.epam.esm.controller.GiftCertificatesController;
import com.epam.esm.dto.GiftCertificateDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class GiftCertificateRepresentation extends RepresentationModel<GiftCertificateRepresentation> implements Serializable {

    private final GiftCertificatesController controller = methodOn(GiftCertificatesController.class);

    private BigInteger id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private int duration;
    private List<TagRepresentation> tags;

    public GiftCertificateRepresentation() {
    }

    public GiftCertificateRepresentation(GiftCertificateDto giftCertificateDto) {
        this.id = giftCertificateDto.getId();
        this.name = giftCertificateDto.getName();
        this.description = giftCertificateDto.getDescription();
        this.price = giftCertificateDto.getPrice();
        this.createDate = giftCertificateDto.getCreateDate();
        this.lastUpdateDate = giftCertificateDto.getLastUpdateDate();
        this.duration = giftCertificateDto.getDuration();
        this.tags = giftCertificateDto.getTags().stream().map(TagRepresentation::new).collect(Collectors.toList());

        createLinks();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<TagRepresentation> getTags() {
        return tags;
    }

    public void setTags(List<TagRepresentation> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "GiftCertificateDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", duration=" + duration +
                ", tags=" + tags +
                '}';
    }

    private void createLinks(){
        Link selfRel = linkTo(controller.getGiftCertificate(id)).withSelfRel();
        add(selfRel);
    }
}
