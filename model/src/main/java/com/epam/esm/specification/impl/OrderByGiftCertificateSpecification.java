package com.epam.esm.specification.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.specification.OrderSpecification;
import com.epam.esm.specification.PredicateSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class OrderByGiftCertificateSpecification implements OrderSpecification<GiftCertificate> {

    private final String orderColumn;
    private final String orderType;

    public OrderByGiftCertificateSpecification(String orderColumn, String orderType) {
        this.orderColumn = orderColumn;
        this.orderType = orderType;
    }

    @Override
    public Order createOrder(Root<GiftCertificate> root, CriteriaBuilder criteriaBuilder) {
        if(isParametersNotNull()) {
            if (orderType.equals("asc")) {
                return criteriaBuilder.asc(root.get(orderColumn));
            }
            if (orderType.equals("desc")) {
                return criteriaBuilder.desc(root.get(orderColumn));
            }
        }
        return criteriaBuilder.asc(root.get(DEFAULT_ORDER_COLUMN));

    }

    private boolean isParametersNotNull(){
        if(orderType!= null && orderColumn != null
        && !orderType.isEmpty() && !orderColumn.isEmpty()){
            return true;
        }
        return false;
    }

}
