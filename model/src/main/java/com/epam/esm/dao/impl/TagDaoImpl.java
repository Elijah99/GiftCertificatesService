package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.*;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.IllegalSearchValueException;
import com.epam.esm.exception.IllegalSortTypeException;
import com.epam.esm.mapper.TagRowMapper;
import com.epam.esm.specification.OrderSpecification;
import com.epam.esm.specification.PaginationSpecification;
import com.epam.esm.specification.impl.OrderSpecificationImpl;
import com.epam.esm.specification.impl.PaginationSpecificationImpl;
import com.epam.esm.specification.impl.SearchTagByNameSpecification;
import com.epam.esm.specification.impl.SearchUserByNameSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TagDaoImpl implements TagDao {

    private static String QUERY_MOST_USED_TAG = "SELECT tag.id,tag.name" +
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

        Predicate searchByTagId = new SearchTagByNameSpecification(parameters.getSearchValue()).createPredicate(tagRoot, builder);
        all.where(searchByTagId);

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
    public Tag findMostUsedTag() {
        return (Tag) entityManager.createNativeQuery(QUERY_MOST_USED_TAG,Tag.class).getSingleResult();
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
