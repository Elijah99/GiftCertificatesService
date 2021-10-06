package com.epam.esm.specification.impl;

import com.epam.esm.specification.OrderSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

public class OrderSpecificationImpl<T> implements OrderSpecification<T> {

    private final String orderColumn;
    private final String orderType;

    public OrderSpecificationImpl(String orderColumn, String orderType) {
        this.orderColumn = orderColumn;
        this.orderType = orderType;
    }

    @Override
    public Order createOrder(Root<T> root, CriteriaBuilder criteriaBuilder) {
        if (isParametersNotNull()) {
            if (orderType.equals("asc")) {
                return criteriaBuilder.asc(root.get(orderColumn));
            }
            if (orderType.equals("desc")) {
                return criteriaBuilder.desc(root.get(orderColumn));
            }
        }
        return criteriaBuilder.asc(root.get(DEFAULT_ORDER_COLUMN));

    }

    private boolean isParametersNotNull() {
        return orderType != null && orderColumn != null
                && !orderType.isEmpty() && !orderColumn.isEmpty();
    }

}
