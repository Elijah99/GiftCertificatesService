package com.epam.esm.service.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;
import com.epam.esm.dto.RequestParameters;
import com.epam.esm.exception.UserNotFoundException;
import com.epam.esm.mapper.impl.RequestParametersMapper;
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
    private RequestParametersMapper requestParametersMapper;

    @Override
    public UserDto findById(BigInteger id) {
        Optional<User> userOptional = userDao.findById(id);
        return userMapper.mapEntityToDto(
                userOptional.orElseThrow(UserNotFoundException::new));
    }

    @Override
    public List<UserDto> findAll(RequestParameters requestParameters) {
        List<User> users = userDao.findByParameters(requestParametersMapper.mapDtoToEntity(requestParameters));
        return userMapper.mapListEntityToListDto(users);
    }

    @Override
    public long count(RequestParameters requestParameters) {
        int pageSize = requestParameters.getPageSize();
        long elementsAmount = userDao.count();
        return elementsAmount % pageSize == 0
                ? elementsAmount / pageSize
                : elementsAmount / pageSize + 1;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setRequestParametersMapper(RequestParametersMapper requestParametersMapper) {
        this.requestParametersMapper = requestParametersMapper;
    }
}
