package com.epam.esm.dao.audit.impl;

import com.epam.esm.dao.audit.GiftCertificateTagAuditDao;
import com.epam.esm.entity.audit.GiftCertificateTagAudit;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@Repository
public class GiftCertificateTagAuditDaoImpl implements GiftCertificateTagAuditDao {

    @PersistenceUnit
    private final EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    private final EntityManager entityManager;

    public GiftCertificateTagAuditDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void save(GiftCertificateTagAudit giftCertificateTagAudit) {
        try {
            entityManager.persist(giftCertificateTagAudit);
        } finally {
            entityManager.close();
        }
    }
}
