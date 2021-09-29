package com.epam.esm.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface PredicateSpecification<T> {
    Predicate createPredicate(Root<T> root, CriteriaBuilder criteriaBuilder);
}
