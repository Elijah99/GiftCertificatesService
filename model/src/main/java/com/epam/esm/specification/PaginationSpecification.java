package com.epam.esm.specification;

import com.epam.esm.entity.QueryParameters;

import javax.persistence.TypedQuery;

public interface PaginationSpecification<T> {

    TypedQuery<T> createPaginationTypedQuery();
}
