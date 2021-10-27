package com.epam.esm.service.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.entity.User;
import com.epam.esm.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userDao.findByLogin(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new org.springframework.security.core.userdetails.User(
                    user.getLogin(),
                    user.getPassword(),
                    getAuthority(user));
        } else {
            throw new UserNotFoundException();
        }
    }

    private List<SimpleGrantedAuthority> getAuthority(User user) {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getValue()));
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
