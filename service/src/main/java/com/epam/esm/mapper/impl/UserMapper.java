package com.epam.esm.mapper.impl;

import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;
import com.epam.esm.mapper.DtoMapper;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements DtoMapper<User, UserDto> {
    @Override
    public UserDto mapEntityToDto(User entity) {
        UserDto dto = new UserDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        if(entity.getOrders()!=null) {
            dto.setOrders(entity.getOrders());
        }

        return dto;
    }

    @Override
    public User mapDtoToEntity(UserDto dto) {
        User user = new User();

        user.setId(dto.getId());
        user.setName(dto.getName());
        if(dto.getOrders()!=null) {
            user.setOrders(dto.getOrders());
        }

        return user;
    }
}
