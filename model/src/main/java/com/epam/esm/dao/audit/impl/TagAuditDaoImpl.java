package com.epam.esm.dao.audit.impl;

import com.epam.esm.dao.audit.TagAuditDao;
import com.epam.esm.entity.audit.TagAudit;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

@Repository
public class TagAuditDaoImpl implements TagAuditDao {

    @PersistenceUnit
    private final EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    private final EntityManager entityManager;

    public TagAuditDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Transactional
    @Override
    public void save(TagAudit tagAudit) {
        try {
            entityManager.persist(tagAudit);
        } finally {
            entityManager.close();
        }
    }
}
