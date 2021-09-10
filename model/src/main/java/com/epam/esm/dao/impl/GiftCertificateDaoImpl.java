package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.entity.GiftCertificate;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.TransactionScoped;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {

    @PersistenceUnit
    private final EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    private final EntityManager entityManager;

    public GiftCertificateDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<GiftCertificate> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GiftCertificate> query = builder.createQuery(GiftCertificate.class);
        Root<GiftCertificate> rootEntry = query.from(GiftCertificate.class);
        CriteriaQuery<GiftCertificate> all = query.select(rootEntry);
        TypedQuery<GiftCertificate> allQuery = entityManager.createQuery(all);

        return allQuery.getResultList();
    }

    @Override
    public Optional<GiftCertificate> findById(BigInteger id) {
        GiftCertificate foundGiftCertificate = entityManager.find(GiftCertificate.class, id);
        return Optional.ofNullable(foundGiftCertificate);
    }


    @Override
    public GiftCertificate save(GiftCertificate giftCertificate) {
        entityManager.persist(giftCertificate);
        entityManager.flush();
        return giftCertificate;
    }

    @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        entityManager.detach(giftCertificate);
        entityManager.merge(giftCertificate);
        return findById(giftCertificate.getId()).get();
    }

    @Override
    public BigInteger deleteById(BigInteger id) {
        GiftCertificate certificate = entityManager.find(GiftCertificate.class, id);
        if (certificate != null) {
            entityManager.remove(certificate);
            return id;
        }
        throw new EntityNotFoundException();
    }

    @Override
    public List<GiftCertificate> searchByColumn(String searchParameter, String value) {
        //return jdbcTemplate.query(String.format(SELECT_BY_COLUMN, searchParameter), rowMapper, "%" + value + "%");
        return new ArrayList<GiftCertificate>();
    }

    @Override
    public List<GiftCertificate> searchByTagName(String value) {
        //return jdbcTemplate.query(SELECT_BY_TAG_NAME, rowMapper, "%" + value + "%");
        return new ArrayList<GiftCertificate>();

    }

    @Override
    public List<GiftCertificate> findAllWithOrder(String sortParameter, String sortType) {
        //return jdbcTemplate.query(String.format(SELECT_ALL_WITH_ORDER, sortParameter, sortType), rowMapper);
        return new ArrayList<GiftCertificate>();
    }
}
