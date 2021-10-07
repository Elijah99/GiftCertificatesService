package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.QueryParameters;
import com.epam.esm.entity.Tag;
import com.epam.esm.specification.OrderSpecification;
import com.epam.esm.specification.PaginationSpecification;
import com.epam.esm.specification.impl.OrderSpecificationImpl;
import com.epam.esm.specification.impl.PaginationSpecificationImpl;
import com.epam.esm.specification.impl.SearchTagByNameSpecification;
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
public class TagDaoImpl implements TagDao {

    private static final String QUERY_MOST_USED_TAG = "SELECT tag.id,tag.name" +
            " FROM tag WHERE tag.id = (SELECT gift_certificate_tag.id_tag" +
            " FROM gift_certificate_tag WHERE gift_certificate_tag.id_gift_certificate IN" +
            " (SELECT id_gift_certificate FROM \"order\" WHERE id_user=" +
            "(SELECT id_user FROM \"order\" WHERE cost =" +
            "(SELECT MAX(cost) FROM \"order\")LIMIT 1))" +
            " GROUP BY id_tag ORDER BY COUNT(id_tag) DESC LIMIT 1);";

    @PersistenceUnit
    private final EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public TagDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<Tag> findByParameters(QueryParameters parameters) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
        Root<Tag> tagRoot = query.from(Tag.class);
        CriteriaQuery<Tag> all = query.select(tagRoot);

        List<Predicate> predicates = new ArrayList<>();
        if (parameters.getSearchValue() != null) {
            parameters.getSearchValue().forEach(searchValue -> {
                predicates.add(new SearchTagByNameSpecification(searchValue).createPredicate(tagRoot, builder));
            });
        }
        Predicate search = builder.and(predicates.toArray(new Predicate[0]));
        all.where(search);

        OrderSpecification<Tag> orderSpecification = new OrderSpecificationImpl<Tag>(
                parameters.getSearchParameter(),
                parameters.getSortType());

        all.orderBy(orderSpecification.createOrder(tagRoot, builder));

        TypedQuery<Tag> typedQuery = entityManager.createQuery(all);

        PaginationSpecification<Tag> paginationSpecification = new PaginationSpecificationImpl<Tag>(typedQuery, parameters);

        typedQuery = paginationSpecification.createPaginationTypedQuery();

        return typedQuery.getResultList();
    }

    @Override
    public Optional<Tag> findById(BigInteger id) {
        Tag foundTag = entityManager.find(Tag.class, id);
        return Optional.ofNullable(foundTag);
    }

    @Override
    public BigInteger deleteById(BigInteger id) {
        Tag tag = entityManager.find(Tag.class, id);
        if (tag != null) {
            if (tag.getGiftCertificateTags() != null) {
                tag.getGiftCertificateTags().forEach(entityManager::remove);
            }
            entityManager.remove(tag);
            return id;
        }
        throw new EntityNotFoundException();
    }

    @Override
    public Tag save(Tag tag) {
        try {
            entityManager.persist(tag);
        } finally {
            entityManager.close();
        }
        return tag;
    }

    @Override
    public Optional<Tag> findByName(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
        Root<Tag> tagRoot = query.from(Tag.class);
        CriteriaQuery<Tag> allQuery = query.select(tagRoot);

        Predicate searchByTagName = new SearchTagByNameSpecification(name).createPredicate(tagRoot, builder);
        allQuery.where(searchByTagName);

        TypedQuery<Tag> typedQuery = entityManager.createQuery(allQuery);

        return Optional.ofNullable(typedQuery.getSingleResult());
    }

    @Override
    public Tag findMostUsedTag() {
        return (Tag) entityManager.createNativeQuery(QUERY_MOST_USED_TAG, Tag.class).getSingleResult();
    }

    @Override
    public long count() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Tag> tagRoot = criteriaQuery.from(Tag.class);

        criteriaQuery.select(builder.count(tagRoot));
        TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);

        return query.getSingleResult();
    }
}
