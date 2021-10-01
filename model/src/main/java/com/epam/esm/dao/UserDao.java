package com.epam.esm.dao;

import com.epam.esm.entity.QueryParameters;
import com.epam.esm.entity.User;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findById(BigInteger id);

    List<User> findByParameters(QueryParameters parameters);

    long count();
}
