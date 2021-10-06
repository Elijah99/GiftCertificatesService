package com.epam.esm.specification.impl;

import com.epam.esm.entity.User;
import com.epam.esm.specification.PredicateSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SearchUserByNameSpecification implements PredicateSpecification<User> {

    private final String name;

    public SearchUserByNameSpecification(String name) {
        this.name = name;
    }

    @Override
    public Predicate createPredicate(Root<User> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("name"), name);
    }
}
