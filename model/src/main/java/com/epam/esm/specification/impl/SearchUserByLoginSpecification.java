package com.epam.esm.specification.impl;

import com.epam.esm.entity.User;
import com.epam.esm.specification.PredicateSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SearchUserByLoginSpecification implements PredicateSpecification<User> {

    private final String login;

    public SearchUserByLoginSpecification(String login) {
        this.login = login;
    }

    @Override
    public Predicate createPredicate(Root<User> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(root.get("login"), "%" + login + "%");
    }
}
