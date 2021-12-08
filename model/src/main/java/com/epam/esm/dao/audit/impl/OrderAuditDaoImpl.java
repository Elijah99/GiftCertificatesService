package com.epam.esm.dao.audit.impl;

import com.epam.esm.dao.audit.OrderAuditDao;
import com.epam.esm.entity.audit.OrderAudit;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@Repository
public class OrderAuditDaoImpl implements OrderAuditDao {

    @PersistenceUnit(unitName = "my_persistence_unit")
    private final EntityManagerFactory entityManagerFactory;
    @PersistenceContext(unitName = "my_persistence_unit")
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
