package com.epam.esm.specification.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.specification.PredicateSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SearchTagByNameSpecification implements PredicateSpecification<Tag> {
    private final String name;

    public SearchTagByNameSpecification(String name) {
        this.name = name;
    }

    @Override
    public Predicate createPredicate(Root<Tag> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(root.get("name"), "%" +  name + "%");
    }
}
