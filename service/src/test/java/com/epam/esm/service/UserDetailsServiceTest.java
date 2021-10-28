package com.epam.esm.service;


import com.epam.esm.dao.UserDao;
import com.epam.esm.exception.UserNotFoundException;
import com.epam.esm.service.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.Optional;

import static com.epam.esm.DaoTestData.FIRST_USER;
import static com.epam.esm.DaoTestData.FIRST_USER_OPTIONAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceTest {

    private static final User FIRST_USER_DETAILS = new User(FIRST_USER.getLogin(),
            FIRST_USER.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority(FIRST_USER.getRole().getValue())));

    @Mock
    private UserDao userDaoMock;
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    public void testLoadByUsername() {
        when(userDaoMock.findByLogin(FIRST_USER.getLogin())).thenReturn(FIRST_USER_OPTIONAL);

        assertEquals(FIRST_USER_DETAILS, userDetailsService.loadUserByUsername(FIRST_USER.getLogin()));

        verify(userDaoMock).findByLogin(FIRST_USER.getLogin());
        verifyNoMoreInteractions(userDaoMock);
    }

    @Test
    public void testLoadByUsernameShouldThrowUserNotFoundException() {
        when(userDaoMock.findByLogin(FIRST_USER.getLogin())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername(FIRST_USER.getLogin());
        });

        verify(userDaoMock).findByLogin(FIRST_USER.getLogin());
        verifyNoMoreInteractions(userDaoMock);
    }
}
