package com.epam.esm.dao.audit.impl;

import com.epam.esm.dao.audit.TagAuditDao;
import com.epam.esm.entity.audit.TagAudit;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@Repository
public class TagAuditDaoImpl implements TagAuditDao {

    @PersistenceUnit(unitName = "my_persistence_unit")
    private final EntityManagerFactory entityManagerFactory;
    @PersistenceContext(unitName = "my_persistence_unit")
    private final EntityManager entityManager;

    public TagAuditDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void save(TagAudit tagAudit) {
        try {
            entityManager.persist(tagAudit);
        } finally {
            entityManager.close();
        }
    }
}
