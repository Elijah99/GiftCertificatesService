package com.epam.esm.service;

import com.epam.esm.dto.UserDto;

import java.math.BigInteger;
import java.util.List;

public interface UserService {
    UserDto findById(BigInteger id);

    List<UserDto> findAll();
}
