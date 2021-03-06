package com.epam.esm.dao.impl;

import com.epam.esm.dao.OrderDao;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.QueryParameters;
import com.epam.esm.specification.OrderSpecification;
import com.epam.esm.specification.PaginationSpecification;
import com.epam.esm.specification.impl.OrderSpecificationImpl;
import com.epam.esm.specification.impl.PaginationSpecificationImpl;
import com.epam.esm.specification.impl.SearchOrdersByUserIdSpecification;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDaoImpl implements OrderDao {

    @PersistenceUnit(unitName = "my_persistence_unit")
    private final EntityManagerFactory entityManagerFactory;
    @PersistenceContext(unitName = "my_persistence_unit")
    private final EntityManager entityManager;

    public OrderDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<Order> findByParameters(QueryParameters parameters) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        Root<Order> orderRoot = query.from(Order.class);
        CriteriaQuery<Order> all = query.select(orderRoot);


        List<Predicate> predicates = new ArrayList<>();
        if (parameters.getSearchValue() != null) {
            parameters.getSearchValue().forEach(searchValue -> {
                predicates.add(new SearchOrdersByUserIdSpecification(Long.valueOf(searchValue)).createPredicate(orderRoot, builder));
            });
        }
        Predicate search = builder.and(predicates.toArray(new Predicate[0]));
        all.where(search);

        OrderSpecification<Order> orderSpecification = new OrderSpecificationImpl<Order>(
                parameters.getSortValue(),
                parameters.getSortType());

        all.orderBy(orderSpecification.createOrder(orderRoot, builder));

        TypedQuery<Order> typedQuery = entityManager.createQuery(all);

        PaginationSpecification<Order> paginationSpecification = new PaginationSpecificationImpl<Order>(typedQuery, parameters);

        typedQuery = paginationSpecification.createPaginationTypedQuery();

        return typedQuery.getResultList();
    }

    @Override
    public Optional<Order> findById(Long id) {
        Order foundOrder = entityManager.find(Order.class, id);
        return Optional.ofNullable(foundOrder);
    }

    @Override
    public List<Order> findByUserId(Long idUser, QueryParameters parameters) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = builder.createQuery(Order.class);
        Root<Order> orderRoot = criteriaQuery.from(Order.class);
        CriteriaQuery<Order> all = criteriaQuery.select(orderRoot);

        Predicate searchByUserId = new SearchOrdersByUserIdSpecification(idUser).createPredicate(orderRoot, builder);
        all.where(searchByUserId);

        OrderSpecification<Order> orderSpecification = new OrderSpecificationImpl<Order>(
                parameters.getSearchParameter(),
                parameters.getSortType());

        all.orderBy(orderSpecification.createOrder(orderRoot, builder));

        TypedQuery<Order> typedQuery = entityManager.createQuery(all);

        PaginationSpecification<Order> paginationSpecification = new PaginationSpecificationImpl<Order>(typedQuery, parameters);

        typedQuery = paginationSpecification.createPaginationTypedQuery();

        return typedQuery.getResultList();
    }

    @Override
    public Order save(Order order) {
        entityManager.persist(order);
        entityManager.flush();
        return order;
    }

    @Override
    public long countByUserId(Long userId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Order> orderRoot = criteriaQuery.from(Order.class);

        criteriaQuery.select(builder.count(orderRoot));
        criteriaQuery.where(builder.equal(orderRoot.get("user").get("id"), userId));
        TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);

        return query.getSingleResult();
    }

}
