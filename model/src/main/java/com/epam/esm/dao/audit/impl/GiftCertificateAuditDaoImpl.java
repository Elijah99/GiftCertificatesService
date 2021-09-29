package com.epam.esm.dao.audit.impl;

import com.epam.esm.dao.audit.GiftCertificateAuditDao;
import com.epam.esm.entity.audit.GiftCertificateAudit;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

@Repository
public class GiftCertificateAuditDaoImpl implements GiftCertificateAuditDao {

    @PersistenceUnit
    private final EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    private final EntityManager entityManager;

    public GiftCertificateAuditDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void save(GiftCertificateAudit giftCertificateAudit) {
        try {
            entityManager.persist(giftCertificateAudit);
        } finally {
            entityManager.close();
        }
    }
}
