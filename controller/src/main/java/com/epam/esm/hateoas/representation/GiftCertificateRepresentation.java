package com.epam.esm.hateoas.representation;


import com.epam.esm.controller.GiftCertificatesController;
import com.epam.esm.dto.GiftCertificateDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class GiftCertificateRepresentation extends RepresentationModel<GiftCertificateRepresentation> implements Serializable {

    private final GiftCertificatesController controller = methodOn(GiftCertificatesController.class);

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private int duration;
    private List<TagRepresentation> tags = new ArrayList<>();

    public GiftCertificateRepresentation() {
        createLinks();
    }

    public GiftCertificateRepresentation(GiftCertificateDto giftCertificateDto) {
        this.id = giftCertificateDto.getId();
        this.name = giftCertificateDto.getName();
        this.description = giftCertificateDto.getDescription();
        this.price = giftCertificateDto.getPrice();
        this.createDate = giftCertificateDto.getCreateDate();
        this.lastUpdateDate = giftCertificateDto.getLastUpdateDate();
        this.duration = giftCertificateDto.getDuration();
        if (giftCertificateDto.getTags() != null) {
            this.tags = giftCertificateDto.getTags().stream().map(TagRepresentation::new).collect(Collectors.toList());
        }
        createLinks();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    private void createLinks() {
        Link selfRel = linkTo(controller.getGiftCertificate(id)).withSelfRel();
        add(selfRel);

        Link postLink = linkTo(controller.createGiftCertificate(null)).withRel("create");
        add(postLink);

        Link putLink = linkTo(controller.updateGiftCertificate(null, getId())).withRel("update");
        add(putLink);

        Link deleteLink = linkTo(controller.deleteGiftCertificate(getId())).withRel("delete");
        add(deleteLink);
    }
}
