package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.Tag;
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
    public List<Tag> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
        Root<Tag> rootEntry = query.from(Tag.class);
        CriteriaQuery<Tag> all = query.select(rootEntry);
        TypedQuery<Tag> allQuery = entityManager.createQuery(all);

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
}
