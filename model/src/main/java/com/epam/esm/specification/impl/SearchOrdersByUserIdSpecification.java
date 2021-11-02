package com.epam.esm.specification.impl;

import com.epam.esm.entity.Order;
import com.epam.esm.specification.PredicateSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SearchOrdersByUserIdSpecification implements PredicateSpecification<Order> {

    private final Long idUser;

    public SearchOrdersByUserIdSpecification(Long idUser) {
        this.idUser = idUser;
    }

    @Override
    public Predicate createPredicate(Root<Order> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("user").get("id"), idUser);
    }
}
