package com.epam.esm.specification.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.specification.PredicateSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SearchByStringGiftCertificateSpecification implements PredicateSpecification<GiftCertificate> {

    private final String column;
    private final String value;

    public SearchByStringGiftCertificateSpecification(String column, String value) {
        this.column = column;
        this.value = value;
    }

    @Override
    public Predicate createPredicate(Root<GiftCertificate> root, CriteriaBuilder criteriaBuilder) {
        if (column == null || column.isEmpty()) {
            if (isTagValue(value)) {
                return new SearchByTagGiftCertificateSpecification(value.substring(2, value.length()-1)).createPredicate(root, criteriaBuilder);
            } else {
                return criteriaBuilder.or(
                        criteriaBuilder.like(root.get("name"), "%" + value + "%"),
                        criteriaBuilder.like(root.get("description"), "%" + value + "%")
                );
            }
        } else {
            return criteriaBuilder.like(root.get(column), "%" + value + "%");
        }
    }

    public boolean isTagValue(String value) {
        return value.startsWith("*(") && value.endsWith(")");
    }
}
