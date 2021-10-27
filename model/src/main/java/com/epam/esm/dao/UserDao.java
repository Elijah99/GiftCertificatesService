package com.epam.esm.dao;

import com.epam.esm.entity.QueryParameters;
import com.epam.esm.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findById(Long id);

    List<User> findByParameters(QueryParameters parameters);

    Optional<User> findByLogin(String login);

    User save(User user);

    long count();
}
