package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.*;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.IllegalSearchValueException;
import com.epam.esm.exception.IllegalSortTypeException;
import com.epam.esm.mapper.TagRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
    public List<Tag> findAll(QueryParameters parameters) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
        Root<Tag> rootEntry = query.from(Tag.class);
        CriteriaQuery<Tag> all = query.select(rootEntry);

        try {
            if (parameters.getSortType() != null && !parameters.getSortType().isEmpty()) {
                if(parameters.getSortValue()!=null && !parameters.getSortValue().isEmpty()) {
                    if (parameters.getSortValue().equals("asc")) {
                        all.orderBy(builder.asc(rootEntry.get(parameters.getSortType())));
                    } else {
                        if (parameters.getSortValue().equals("desc")) {
                            all.orderBy(builder.asc(rootEntry.get(parameters.getSortType())));
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new IllegalSortTypeException();
        }
        try {
            if (parameters.getSearchParameter() != null && !parameters.getSearchParameter().isEmpty()
                    && parameters.getSearchValue() != null && !parameters.getSearchValue().isEmpty()) {
                all.where(builder.like(rootEntry.get(parameters.getSearchParameter()), "%" + parameters.getSearchValue() + "%"));
            }
        } catch (Exception e) {
            throw new IllegalSearchValueException();
        }
        TypedQuery<Tag> allQuery = entityManager.createQuery(all);
        if (parameters.getCurrentPage() != 0 && parameters.getPageSize() != 0) {
            allQuery.setFirstResult((parameters.getCurrentPage() - 1) * parameters.getPageSize());
            allQuery.setMaxResults(parameters.getPageSize());
        } else {
            allQuery.setFirstResult(QueryParameters.DEFAULT_PAGE);
            allQuery.setMaxResults(QueryParameters.DEFAULT_PAGE_SIZE);
        }

        return allQuery.getResultList();
    }

    @Override
    public Optional<Tag> findById(BigInteger id) {
        Tag foundTag = entityManager.find(Tag.class, id);
        return Optional.ofNullable(foundTag);    }

    @Override
    public List<Tag> findByGiftCertificateId(BigInteger id) {
        //return jdbcTemplate.query(SELECT_BY_GIFT_CERTIFICATE_ID, rowMapper, id);
        return new ArrayList<Tag>();
    }

    @Override
    public Optional<Tag> findByName(String name) {
        //return jdbcTemplate.query(SELECT_BY_NAME, rowMapper, name).stream().findAny();
        return Optional.empty();
    }

    @Override
    public BigInteger deleteById(BigInteger id) {
//        jdbcTemplate.update(DELETE_FROM_LINK_TABLE_BY_ID, id);
//        jdbcTemplate.update(DELETE_BY_ID, id);
//        return id;
        return new BigInteger("0");
    }

    @Override
    public Tag save(Tag tag) {
        entityManager.persist(tag);
        entityManager.flush();
        return tag;
    }

    @Override
    public List<Tag> findByGiftCertificateIdIn(List<BigInteger> idTags) {
//        String idTagsParams = String.join(",", idTags.stream().map(id -> "?").collect(Collectors.toList()));
//        return jdbcTemplate.query(String.format(SELECT_BY_GIFT_CERTIFICATE_ID_IN, idTagsParams), rowMapper, idTags.toArray());
        return new ArrayList<Tag>();
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
