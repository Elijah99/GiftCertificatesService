package com.epam.esm.specification.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.QueryParameters;
import com.epam.esm.specification.PaginationSpecification;

import javax.persistence.TypedQuery;

public class PaginationSpecificationImpl<T> implements PaginationSpecification<T> {

    private final TypedQuery<T> query;
    private final QueryParameters parameters;

    public PaginationSpecificationImpl(TypedQuery<T> query, QueryParameters parameters) {
    this.query = query;
    this.parameters = parameters;
    }

    @Override
    public TypedQuery<T> createPaginationTypedQuery() {
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
