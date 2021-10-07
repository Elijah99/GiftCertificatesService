package com.epam.esm.mapper;

import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;
import com.epam.esm.mapper.impl.OrderMapper;
import com.epam.esm.mapper.impl.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.epam.esm.ServiceTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class UserMapperTest {

    @Mock
    private OrderMapper orderMapperMock;
    @InjectMocks
    private UserMapper userMapper;

    @Test
    public void testMapEntityToDto() {
        when(orderMapperMock.mapListEntityToListDto(FIRST_USER.getOrders()))
                .thenReturn(FIRST_USER_DTO.getOrders());

        UserDto actual = userMapper.mapEntityToDto(FIRST_USER);
        assertEquals(FIRST_USER_DTO, actual);

        verify(orderMapperMock).mapListEntityToListDto(FIRST_USER.getOrders());
        verifyNoMoreInteractions(orderMapperMock);
    }

    @Test
    public void testMapDtoToEntity() {
        when(orderMapperMock.mapListDtoToListEntity(FIRST_USER_DTO.getOrders()))
                .thenReturn(FIRST_USER.getOrders());

        User actual = userMapper.mapDtoToEntity(FIRST_USER_DTO);
        assertEquals(FIRST_USER, actual);

        verify(orderMapperMock).mapListDtoToListEntity(FIRST_USER_DTO.getOrders());
        verifyNoMoreInteractions(orderMapperMock);
    }

    @Test
    public void testMapListDtoToListEntity() {
        when(orderMapperMock.mapListDtoToListEntity(FIRST_USER_DTO.getOrders()))
                .thenReturn(FIRST_USER.getOrders());
        when(orderMapperMock.mapListDtoToListEntity(SECOND_USER_DTO.getOrders()))
                .thenReturn(SECOND_USER.getOrders());
        when(orderMapperMock.mapListDtoToListEntity(THIRD_USER_DTO.getOrders()))
                .thenReturn(THIRD_USER.getOrders());
        when(orderMapperMock.mapListDtoToListEntity(FOURTH_USER_DTO.getOrders()))
                .thenReturn(FOURTH_USER.getOrders());


        List<User> actual = userMapper.mapListDtoToListEntity(ALL_USERS_DTO);

        assertEquals(ALL_USERS, actual);

        verify(orderMapperMock).mapListDtoToListEntity(FIRST_USER_DTO.getOrders());
        verify(orderMapperMock).mapListDtoToListEntity(SECOND_USER_DTO.getOrders());
        verify(orderMapperMock).mapListDtoToListEntity(THIRD_USER_DTO.getOrders());
        verify(orderMapperMock).mapListDtoToListEntity(FOURTH_USER_DTO.getOrders());
        verifyNoMoreInteractions(orderMapperMock);
    }

    @Test
    public void testMapListEntityToListDto() {
        when(orderMapperMock.mapListEntityToListDto(FIRST_USER.getOrders()))
                .thenReturn(FIRST_USER_DTO.getOrders());
        when(orderMapperMock.mapListEntityToListDto(SECOND_USER.getOrders()))
                .thenReturn(SECOND_USER_DTO.getOrders());
        when(orderMapperMock.mapListEntityToListDto(THIRD_USER.getOrders()))
                .thenReturn(THIRD_USER_DTO.getOrders());
        when(orderMapperMock.mapListEntityToListDto(FOURTH_USER.getOrders()))
                .thenReturn(FOURTH_USER_DTO.getOrders());

        List<UserDto> actual = userMapper.mapListEntityToListDto(ALL_USERS);
        assertEquals(ALL_USERS_DTO, actual);

        verify(orderMapperMock).mapListEntityToListDto(FIRST_USER.getOrders());
        verify(orderMapperMock).mapListEntityToListDto(SECOND_USER.getOrders());
        verify(orderMapperMock).mapListEntityToListDto(THIRD_USER.getOrders());
        verify(orderMapperMock).mapListEntityToListDto(FOURTH_USER.getOrders());
        verifyNoMoreInteractions(orderMapperMock);
    }
}
