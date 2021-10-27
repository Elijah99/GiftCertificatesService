package com.epam.esm.dao.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.entity.QueryParameters;
import com.epam.esm.entity.User;
import com.epam.esm.specification.OrderSpecification;
import com.epam.esm.specification.PaginationSpecification;
import com.epam.esm.specification.impl.OrderSpecificationImpl;
import com.epam.esm.specification.impl.PaginationSpecificationImpl;
import com.epam.esm.specification.impl.SearchUserByLoginSpecification;
import com.epam.esm.specification.impl.SearchUserByNameSpecification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceUnit
    private final EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    private final EntityManager entityManager;

    public UserDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public User save(User user) {
        try {
            entityManager.persist(user);
        } finally {
            entityManager.close();
        }
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        User foundUser = entityManager.find(User.class, id);
        return Optional.ofNullable(foundUser);
    }

    @Override
    public List<User> findByParameters(QueryParameters parameters) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> userRoot = query.from(User.class);
        CriteriaQuery<User> all = query.select(userRoot);

        List<Predicate> predicates = new ArrayList<>();
        if (parameters.getSearchValue() != null) {
            parameters.getSearchValue().forEach(searchValue -> {
                predicates.add(new SearchUserByNameSpecification(searchValue).createPredicate(userRoot, builder));
            });
        }
        Predicate search = builder.and(predicates.toArray(new Predicate[0]));
        all.where(search);

        OrderSpecification<User> orderSpecification = new OrderSpecificationImpl<User>(
                parameters.getSortValue(),
                parameters.getSortType());

        all.orderBy(orderSpecification.createOrder(userRoot, builder));

        TypedQuery<User> typedQuery = entityManager.createQuery(all);

        PaginationSpecification<User> paginationSpecification = new PaginationSpecificationImpl<User>(typedQuery, parameters);

        typedQuery = paginationSpecification.createPaginationTypedQuery();

        return typedQuery.getResultList();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> userRoot = query.from(User.class);
        CriteriaQuery<User> all = query.select(userRoot);

        Predicate searchByLoginPredicate = new SearchUserByLoginSpecification(login).createPredicate(userRoot, builder);

        all.where(searchByLoginPredicate);

        TypedQuery<User> typedQuery = entityManager.createQuery(all);

        return Optional.ofNullable(typedQuery.getSingleResult());
    }

    @Override
    public long count() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<User> userRoot = criteriaQuery.from(User.class);

        criteriaQuery.select(builder.count(userRoot));
        TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);

        return query.getSingleResult();
    }

}
