package com.epam.esm.specification;

import com.epam.esm.entity.GiftCertificate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

public interface OrderSpecification<T> {

    public static final String DEFAULT_ORDER_COLUMN = "id";

    public Order createOrder(Root<GiftCertificate> root, CriteriaBuilder criteriaBuilder);
}
