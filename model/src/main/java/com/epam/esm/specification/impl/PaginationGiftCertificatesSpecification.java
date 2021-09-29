package com.epam.esm.specification.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.QueryParameters;
import com.epam.esm.specification.PaginationSpecification;

import javax.persistence.TypedQuery;

public class PaginationGiftCertificatesSpecification implements PaginationSpecification<GiftCertificate> {

    private final TypedQuery<GiftCertificate> query;
    private final QueryParameters parameters;

    public PaginationGiftCertificatesSpecification(TypedQuery<GiftCertificate> query, QueryParameters parameters) {
    this.query = query;
    this.parameters = parameters;
    }

    @Override
    public TypedQuery<GiftCertificate> createPaginationTypedQuery() {
        if (parameters.getCurrentPage() != 0 && parameters.getPageSize() != 0) {
            query.setFirstResult((parameters.getCurrentPage() - 1) * parameters.getPageSize());
            query.setMaxResults(parameters.getPageSize());
        } else {
            query.setFirstResult(QueryParameters.DEFAULT_PAGE);
            query.setMaxResults(QueryParameters.DEFAULT_PAGE_SIZE);
        }
        return query;
    }
}
