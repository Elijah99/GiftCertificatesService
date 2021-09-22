package com.epam.esm.dao.impl;

import com.epam.esm.dao.OrderDao;
import com.epam.esm.entity.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDaoImpl implements OrderDao {

    @PersistenceUnit
    private final EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    private final EntityManager entityManager;

    public OrderDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<Order> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        Root<Order> rootEntry = query.from(Order.class);
        CriteriaQuery<Order> all = query.select(rootEntry);
        TypedQuery<Order> allQuery = entityManager.createQuery(all);

        return allQuery.getResultList();
    }

    @Override
    public Optional<Order> findById(BigInteger id) {
        Order foundOrder = entityManager.find(Order.class, id);
        return Optional.ofNullable(foundOrder);
    }

    @Override
    public BigInteger deleteById(BigInteger id) {
        Order order = entityManager.find(Order.class, id);
        if (order != null) {
            entityManager.remove(order);
            return id;
        }
        throw new EntityNotFoundException();
    }

    @Override
    public List<Order> findByUserId(BigInteger idUser) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = builder.createQuery(Order.class);
        Root<Order> tagRoot = criteriaQuery.from(Order.class);
        criteriaQuery.where(builder.equal(tagRoot.get("user").get("id"), idUser));
        TypedQuery query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public Order save(Order order) {
        entityManager.persist(order);
        entityManager.flush();
        return order;
    }

    @Override
    public long countByUserId(BigInteger userId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Order> tagRoot = criteriaQuery.from(Order.class);

        criteriaQuery.select(builder.count(tagRoot));
        criteriaQuery.where(builder.equal(tagRoot.get("user").get("id"), userId));
        TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);

        return query.getSingleResult();
    }
}
