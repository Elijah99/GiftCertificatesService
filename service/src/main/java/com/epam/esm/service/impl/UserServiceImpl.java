package com.epam.esm.service.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;
import com.epam.esm.exception.UserNotFoundException;
import com.epam.esm.mapper.impl.UserMapper;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private UserMapper userMapper;

    @Override
    public UserDto findById(BigInteger id) {
        Optional<User> userOptional = userDao.findById(id);
        return userMapper.mapEntityToDto(
                userOptional.orElseThrow(UserNotFoundException::new));
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userDao.findAll();
        return userMapper.mapListEntityToListDto(users);
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
