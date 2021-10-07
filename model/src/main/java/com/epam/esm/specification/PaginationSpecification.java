package com.epam.esm.specification;

import javax.persistence.TypedQuery;

public interface PaginationSpecification<T> {

    TypedQuery<T> createPaginationTypedQuery();
}
