package com.epam.esm.dao.audit.impl;

import com.epam.esm.dao.audit.OrderAuditDao;
import com.epam.esm.entity.audit.OrderAudit;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

@Repository
public class OrderAuditDaoImpl implements OrderAuditDao {

    @PersistenceUnit
    private final EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    private final EntityManager entityManager;

    public OrderAuditDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void save(OrderAudit orderAudit) {
        try {
            entityManager.persist(orderAudit);
        } finally {
            entityManager.close();
        }
    }
}
