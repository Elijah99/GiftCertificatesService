package com.epam.esm.dao.audit.impl;

import com.epam.esm.dao.audit.GiftCertificateAuditDao;
import com.epam.esm.entity.audit.GiftCertificateAudit;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@Repository
public class GiftCertificateAuditDaoImpl implements GiftCertificateAuditDao {

    @PersistenceUnit(unitName = "my_persistence_unit")
    private final EntityManagerFactory entityManagerFactory;
    @PersistenceContext(unitName = "my_persistence_unit")
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
