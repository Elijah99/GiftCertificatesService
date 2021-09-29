package com.epam.esm.dao.audit.impl;

import com.epam.esm.dao.audit.UserAuditDao;
import com.epam.esm.entity.audit.UserAudit;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

@Repository
public class UserAuditDaoImpl implements UserAuditDao {

    @PersistenceUnit
    private final EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    private final EntityManager entityManager;

    public UserAuditDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void save(UserAudit userAudit) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.persist(userAudit);
        } finally {
            entityManager.close();
        }
    }
}
