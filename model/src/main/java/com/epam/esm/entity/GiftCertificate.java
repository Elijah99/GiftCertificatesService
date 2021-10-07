package com.epam.esm.entity;


import com.epam.esm.listener.GiftCertificateAuditListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity(name = "GiftCertificate")
@EntityListeners(GiftCertificateAuditListener.class)
@Table(name = "gift_certificate")
public class GiftCertificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "create_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createDate;
    @Column(name = "last_update_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime lastUpdateDate;
    @Column(name = "duration")
    private int duration;

    @OneToMany(mappedBy = "giftCertificate",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<GiftCertificateTag> giftCertificateTags;

    public GiftCertificate(BigInteger id, String name, String description, BigDecimal price, LocalDateTime createDate, LocalDateTime lastUpdateDate, int duration, List<Tag> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.duration = duration;
        if (tags != null) {
            tags.forEach(this::addTag);
        }
    }

    public GiftCertificate(String name, String description, BigDecimal price, LocalDateTime createDate, LocalDateTime lastUpdateDate, int duration) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.duration = duration;
    }

    public GiftCertificate(BigInteger id, String name, String description, LocalDateTime lastUpdateDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.lastUpdateDate = lastUpdateDate;
    }

    public GiftCertificate() {
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


    public List<GiftCertificateTag> getGiftCertificateTags() {
        return giftCertificateTags;
    }

    public void setGiftCertificateTags(List<GiftCertificateTag> giftCertificateTags) {
        this.giftCertificateTags = giftCertificateTags;
    }

    public List<Tag> getTags() {
        List<Tag> tags = new ArrayList<>();
        if (this.giftCertificateTags != null) {
            this.giftCertificateTags.forEach(giftCertificateTag -> tags.add(giftCertificateTag.getTag()));
            return tags;
        }
        return null;
    }

    public void addTag(Tag tag) {
        GiftCertificateTag postTag = new GiftCertificateTag(this, tag);
        if (giftCertificateTags == null) {
            giftCertificateTags = new ArrayList<GiftCertificateTag>();
        }
        giftCertificateTags.add(postTag);
        tag.getGiftCertificateTags().add(postTag);
    }

    public void removeTag(Tag tag) {
        for (Iterator<GiftCertificateTag> iterator = giftCertificateTags.iterator();
             iterator.hasNext(); ) {
            GiftCertificateTag postTag = iterator.next();

            if (postTag.getGiftCertificate().equals(this) &&
                    postTag.getTag().equals(tag)) {
                iterator.remove();
                postTag.getTag().getGiftCertificateTags().remove(postTag);
                postTag.setGiftCertificate(null);
                postTag.setTag(null);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftCertificate that = (GiftCertificate) o;
        return duration == that.duration &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(price, that.price) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(lastUpdateDate, that.lastUpdateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price, createDate, lastUpdateDate, duration);
    }

    @Override
    public String toString() {
        return "GiftCertificate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", duration=" + duration +
                '}';
    }
}

