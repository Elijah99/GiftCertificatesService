package com.epam.esm.specification.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.specification.PredicateSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SearchByTagGiftCertificateSpecification implements PredicateSpecification<GiftCertificate> {

    private final String tagName;

    public SearchByTagGiftCertificateSpecification(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public Predicate createPredicate(Root<GiftCertificate> root, CriteriaBuilder criteriaBuilder) {
        Join<Tag, GiftCertificate> join = root.join("tags");
        return criteriaBuilder.equal(join.get("name"), tagName);
    }
}
