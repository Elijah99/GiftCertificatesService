package com.epam.esm.dao.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.entity.QueryParameters;
import com.epam.esm.entity.User;
import com.epam.esm.exception.IllegalSortTypeException;
import com.epam.esm.exception.IllegalSearchValueException;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
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
    public Optional<User> findById(BigInteger id) {
        User foundUser = entityManager.find(User.class, id);
        return Optional.ofNullable(foundUser);
    }

    @Override
    public List<User> findAll(QueryParameters parameters) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> rootEntry = query.from(User.class);
        CriteriaQuery<User> all = query.select(rootEntry);
        try {
            all.orderBy(builder.asc(rootEntry.get(parameters.getSortType())));
        } catch (Exception e){
            throw new IllegalSortTypeException();
        }
        try {
            all.where(builder.like(rootEntry.get(parameters.getSearchParameter()), "%" + parameters.getSearchValue() + "%"));
        } catch (Exception e){
            throw new IllegalSearchValueException();
        }
        TypedQuery<User> allQuery = entityManager.createQuery(all);
        if (parameters.getCurrentPage() != 0 && parameters.getPageSize() != 0) {
            allQuery.setFirstResult((parameters.getCurrentPage() - 1) * parameters.getPageSize());
            allQuery.setMaxResults(parameters.getPageSize());
        } else {
            allQuery.setFirstResult(QueryParameters.DEFAULT_PAGE);
            allQuery.setMaxResults(QueryParameters.DEFAULT_PAGE_SIZE);
        }

        return allQuery.getResultList();
    }
}
