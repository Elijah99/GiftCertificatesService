package com.epam.esm.service;

import com.epam.esm.dao.UserDao;
import com.epam.esm.mapper.impl.RequestParametersMapper;
import com.epam.esm.mapper.impl.UserMapper;
import com.epam.esm.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static com.epam.esm.ServiceTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserMapper userMapperMock;
    @Mock
    private UserDao userDaoMock;
    @Mock
    private RequestParametersMapper requestParametersMapperMock;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testFindById() {
        when(userDaoMock.findById(FIRST_USER.getId())).thenReturn(FIRST_USER_OPTIONAL);
        when(userMapperMock.mapEntityToDto(FIRST_USER)).thenReturn(FIRST_USER_DTO);

        assertEquals(FIRST_USER_DTO, userService.findById(FIRST_USER.getId()));

        verify(userDaoMock).findById(FIRST_USER.getId());
        verify(userMapperMock).mapEntityToDto(FIRST_USER);
        verifyNoMoreInteractions(userDaoMock, userMapperMock);
    }

    @Test
    public void testFindAll() {
        when(requestParametersMapperMock.mapDtoToEntity(DEFAULT_REQUEST_PARAMETERS)).thenReturn(DEFAULT_QUERY_PARAMETERS);
        when(userMapperMock.mapListEntityToListDto(ALL_USERS)).thenReturn(ALL_USERS_DTO);
        when(userDaoMock.findByParameters(DEFAULT_QUERY_PARAMETERS)).thenReturn(ALL_USERS);

        assertEquals(ALL_USERS_DTO, userService.findAll(DEFAULT_REQUEST_PARAMETERS));

        verify(userDaoMock).findByParameters(DEFAULT_QUERY_PARAMETERS);
        verify(userMapperMock).mapListEntityToListDto(ALL_USERS);
        verify(requestParametersMapperMock).mapDtoToEntity(DEFAULT_REQUEST_PARAMETERS);
        verifyNoMoreInteractions(userDaoMock, userMapperMock, requestParametersMapperMock);
    }

    @Test
    public void testCountPagesWhenPageSizeIsMultipleOfNumberRecords() {
        when(userDaoMock.count()).thenReturn(10L);
        long expected = 10;

        assertEquals(expected, userService.countPages(REQUEST_PARAMETERS_WITH_PAGE_SIZE_1));
    }

    @Test
    public void testCountPagesWhenPageSizeNotMultipleOfNumberRecords() {
        when(userDaoMock.count()).thenReturn(10L);
        long expected = 2;

        assertEquals(expected, userService.countPages(REQUEST_PARAMETERS_WITH_PAGE_SIZE_9));
    }

    @Test
    public void testCountPagesWhenPageSizeMoreThanNumberRecords() {
        when(userDaoMock.count()).thenReturn(10L);
        long expected = 1;

        assertEquals(expected, userService.countPages(REQUEST_PARAMETERS_WITH_PAGE_SIZE_100));
    }
}
