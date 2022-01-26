package com.epam.esm.service.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dto.RequestParameters;
import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.Role;
import com.epam.esm.entity.User;
import com.epam.esm.exception.UserNotFoundException;
import com.epam.esm.mapper.impl.RequestParametersMapper;
import com.epam.esm.mapper.impl.UserMapper;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private UserMapper userMapper;
    private RequestParametersMapper requestParametersMapper;

    @Override
    public UserDto findById(Long id) {
        Optional<User> userOptional = userDao.findById(id);
        if (userOptional.isPresent()) {
            return userMapper.mapEntityToDto(userOptional.get());
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<UserDto> findAll(RequestParameters requestParameters) {
        List<User> users = userDao.findByParameters(requestParametersMapper.mapDtoToEntity(requestParameters));
        return userMapper.mapListEntityToListDto(users);
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = userMapper.mapDtoToEntity(userDto);
        user.setRole(Role.ROLE_USER);
        User savedUser = userDao.save(user);
        return userMapper.mapEntityToDto(savedUser);
    }

    @Override
    public long countPages(RequestParameters requestParameters) {
        long pageSize = requestParameters.getPageSize();
        long elementsAmount = userDao.count();
        return elementsAmount % pageSize == 0
                ? elementsAmount / pageSize
                : elementsAmount / pageSize + 1;
    }

    @Override
    public long count() {
        return userDao.count();
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
