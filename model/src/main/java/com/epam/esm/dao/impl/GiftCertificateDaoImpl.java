package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.QueryParameters;
import com.epam.esm.specification.OrderSpecification;
import com.epam.esm.specification.PaginationSpecification;
import com.epam.esm.specification.PredicateSpecification;
import com.epam.esm.specification.SpecificationBuilder;
import com.epam.esm.specification.impl.OrderSpecificationImpl;
import com.epam.esm.specification.impl.PaginationSpecificationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class GiftCertificateDaoImpl implements GiftCertificateDao {

    @PersistenceUnit
    private final EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    private final EntityManager entityManager;
    private SpecificationBuilder specificationBuilder;

    public GiftCertificateDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<GiftCertificate> findByParameters(QueryParameters parameters) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GiftCertificate> query = builder.createQuery(GiftCertificate.class);
        Root<GiftCertificate> rootEntry = query.from(GiftCertificate.class);
        CriteriaQuery<GiftCertificate> all = query.select(rootEntry);

        List<PredicateSpecification<GiftCertificate>> specifications = specificationBuilder.createPredicateSpecifications(parameters);
        List<Predicate> predicates = new ArrayList<>();
        specifications.forEach(s -> {
            predicates.add(s.createPredicate(rootEntry, builder));
        });
        all.where(predicates.toArray(new Predicate[]{}));

        OrderSpecification<GiftCertificate> orderSpecification = new OrderSpecificationImpl<GiftCertificate>(
                parameters.getSearchParameter(),
                parameters.getSortType());

        all.orderBy(orderSpecification.createOrder(rootEntry, builder));

        TypedQuery<GiftCertificate> typedQuery = entityManager.createQuery(all);

        PaginationSpecification<GiftCertificate> paginationSpecification = new PaginationSpecificationImpl<GiftCertificate>(typedQuery, parameters);

        typedQuery = paginationSpecification.createPaginationTypedQuery();

        return typedQuery.getResultList();
    }

    @Override
    public Optional<GiftCertificate> findById(BigInteger id) {
        GiftCertificate foundGiftCertificate = entityManager.find(GiftCertificate.class, id);
        return Optional.ofNullable(foundGiftCertificate);
    }


    @Override
    public GiftCertificate save(GiftCertificate giftCertificate) {
        try {
            entityManager.persist(giftCertificate);
        } finally {
            entityManager.close();
        }
        return giftCertificate;
    }

    @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        try {
            entityManager.merge(giftCertificate);
        } finally {
            entityManager.close();
        }
        return findById(giftCertificate.getId()).get();
    }

    @Override
    public BigInteger deleteById(BigInteger id) {
        try {
            GiftCertificate certificate = entityManager.find(GiftCertificate.class, id);
            if (certificate != null) {
                entityManager.remove(certificate);
                return id;
            }
        } finally {
            entityManager.close();
        }
        throw new EntityNotFoundException();
    }

    @Override
    public long count() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<GiftCertificate> giftCertificateRoot = criteriaQuery.from(GiftCertificate.class);

        criteriaQuery.select(builder.count(giftCertificateRoot));
        TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);

        return query.getSingleResult();
    }

    @Autowired
    public void setSpecificationBuilder(SpecificationBuilder specificationBuilder) {
        this.specificationBuilder = specificationBuilder;
    }
}
