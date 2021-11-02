package com.epam.esm.service;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.OrderDao;
import com.epam.esm.dao.UserDao;
import com.epam.esm.exception.OrderNotFoundException;
import com.epam.esm.mapper.impl.OrderMapper;
import com.epam.esm.mapper.impl.RequestParametersMapper;
import com.epam.esm.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static com.epam.esm.ServiceTestData.ALL_ORDERS;
import static com.epam.esm.ServiceTestData.ALL_ORDERS_DTO;
import static com.epam.esm.ServiceTestData.DEFAULT_QUERY_PARAMETERS;
import static com.epam.esm.ServiceTestData.DEFAULT_REQUEST_PARAMETERS;
import static com.epam.esm.ServiceTestData.FIRST_ORDER;
import static com.epam.esm.ServiceTestData.FIRST_ORDER_DTO;
import static com.epam.esm.ServiceTestData.FIRST_ORDER_OPTIONAL;
import static com.epam.esm.ServiceTestData.FIRST_USER;
import static com.epam.esm.ServiceTestData.FIRST_USER_DTO;
import static com.epam.esm.ServiceTestData.FIRST_USER_OPTIONAL;
import static com.epam.esm.ServiceTestData.ORDER_FOR_SAVE;
import static com.epam.esm.ServiceTestData.ORDER_FOR_SAVE_DTO;
import static com.epam.esm.ServiceTestData.ORDER_SAVED;
import static com.epam.esm.ServiceTestData.ORDER_SAVED_DTO;
import static com.epam.esm.ServiceTestData.REQUEST_PARAMETERS_WITH_PAGE_SIZE_1;
import static com.epam.esm.ServiceTestData.REQUEST_PARAMETERS_WITH_PAGE_SIZE_100;
import static com.epam.esm.ServiceTestData.REQUEST_PARAMETERS_WITH_PAGE_SIZE_9;
import static com.epam.esm.ServiceTestData.SECOND_GIFT_CERTIFICATE;
import static com.epam.esm.ServiceTestData.SECOND_GIFT_CERTIFICATE_OPTIONAL;
import static com.epam.esm.ServiceTestData.THIRD_GIFT_CERTIFICATE_OPTIONAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrderDao orderDaoMock;
    @Mock
    private UserDao userDaoMock;
    @Mock
    private OrderMapper orderMapperMock;
    @Mock
    private GiftCertificateDao giftCertificateDaoMock;
    @Mock
    private RequestParametersMapper requestParametersMapperMock;
    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    public void setUpMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrderShouldReturnCreatedOrderDto() {
        when(userDaoMock.findById(FIRST_USER.getId())).thenReturn(FIRST_USER_OPTIONAL);
        when(orderDaoMock.save(ORDER_FOR_SAVE)).thenReturn(ORDER_SAVED);
        when(orderMapperMock.mapEntityToDto(ORDER_SAVED)).thenReturn(ORDER_SAVED_DTO);
        when(orderMapperMock.mapDtoToEntity(ORDER_FOR_SAVE_DTO)).thenReturn(ORDER_FOR_SAVE);
        when(giftCertificateDaoMock.findById(any())).thenReturn(SECOND_GIFT_CERTIFICATE_OPTIONAL).thenReturn(THIRD_GIFT_CERTIFICATE_OPTIONAL);

        assertEquals(ORDER_SAVED_DTO, orderService.createOrder(FIRST_USER.getId(), ORDER_FOR_SAVE_DTO));

        verify(userDaoMock).findById(FIRST_USER.getId());
        verify(orderDaoMock).save(ORDER_FOR_SAVE);
        verify(orderMapperMock).mapEntityToDto(ORDER_SAVED);
        verify(orderMapperMock).mapDtoToEntity(ORDER_FOR_SAVE_DTO);
        verify(giftCertificateDaoMock).findById(SECOND_GIFT_CERTIFICATE.getId());
        verifyNoMoreInteractions(userDaoMock, orderDaoMock, orderMapperMock);
    }

    @Test
    public void testFindOrderByUserId() {
        when(requestParametersMapperMock.mapDtoToEntity(DEFAULT_REQUEST_PARAMETERS)).thenReturn(DEFAULT_QUERY_PARAMETERS);
        when(orderDaoMock.findByUserId(FIRST_USER.getId(), DEFAULT_QUERY_PARAMETERS)).thenReturn(FIRST_USER.getOrders());
        when(orderMapperMock.mapListEntityToListDto(FIRST_USER.getOrders())).thenReturn(FIRST_USER_DTO.getOrders());

        assertEquals(FIRST_USER_DTO.getOrders(), orderService.findOrdersByUserId(FIRST_USER.getId(), DEFAULT_REQUEST_PARAMETERS));

        verify(requestParametersMapperMock).mapDtoToEntity(DEFAULT_REQUEST_PARAMETERS);
        verify(orderDaoMock).findByUserId(FIRST_USER.getId(), DEFAULT_QUERY_PARAMETERS);
        verify(orderMapperMock).mapListEntityToListDto(FIRST_USER.getOrders());
        verifyNoMoreInteractions(requestParametersMapperMock, orderDaoMock, orderMapperMock);
    }

    @Test
    public void testFindAll() {
        when(requestParametersMapperMock.mapDtoToEntity(DEFAULT_REQUEST_PARAMETERS)).thenReturn(DEFAULT_QUERY_PARAMETERS);
        when(orderDaoMock.findByParameters(DEFAULT_QUERY_PARAMETERS)).thenReturn(ALL_ORDERS);
        when(orderMapperMock.mapListEntityToListDto(ALL_ORDERS)).thenReturn(ALL_ORDERS_DTO);

        assertEquals(ALL_ORDERS_DTO, orderService.findAll(DEFAULT_REQUEST_PARAMETERS));

        verify(requestParametersMapperMock).mapDtoToEntity(DEFAULT_REQUEST_PARAMETERS);
        verify(orderDaoMock).findByParameters(DEFAULT_QUERY_PARAMETERS);
        verify(orderMapperMock).mapListEntityToListDto(ALL_ORDERS);
        verifyNoMoreInteractions(requestParametersMapperMock, orderDaoMock, orderMapperMock);
    }

    @Test
    public void testFindOrderById() {
        when(orderDaoMock.findById(FIRST_ORDER.getId())).thenReturn(FIRST_ORDER_OPTIONAL);
        when(orderMapperMock.mapEntityToDto(FIRST_ORDER)).thenReturn(FIRST_ORDER_DTO);

        assertEquals(FIRST_ORDER_DTO, orderService.findOrderById(FIRST_USER.getId(), FIRST_ORDER.getId()));

        verify(orderDaoMock).findById(FIRST_ORDER.getId());
        verify(orderMapperMock).mapEntityToDto(FIRST_ORDER);
        verifyNoMoreInteractions(orderDaoMock, orderMapperMock);
    }

    @Test
    public void testFindOrderByIdThrowsOrderNotFoundException() {
        when(orderDaoMock.findById(FIRST_ORDER.getId())).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> {
            orderService.findOrderById(FIRST_USER.getId(), FIRST_ORDER.getId());
        });

        verify(orderDaoMock).findById(FIRST_ORDER.getId());
        verifyNoMoreInteractions(orderDaoMock, orderMapperMock);
    }

    @Test
    public void testCountPagesWhenPageSizeIsMultipleOfNumberRecords() {
        when(orderDaoMock.countByUserId(any())).thenReturn(10L);
        long expected = 10;

        assertEquals(expected, orderService.countPages(FIRST_USER.getId(), REQUEST_PARAMETERS_WITH_PAGE_SIZE_1));
    }

    @Test
    public void testCountPagesWhenPageSizeNotMultipleOfNumberRecords() {
        when(orderDaoMock.countByUserId(any())).thenReturn(10L);
        long expected = 2;

        assertEquals(expected, orderService.countPages(FIRST_USER.getId(), REQUEST_PARAMETERS_WITH_PAGE_SIZE_9));
    }

    @Test
    public void testCountPagesWhenPageSizeMoreThanNumberRecords() {
        when(orderDaoMock.countByUserId(any())).thenReturn(10L);
        long expected = 1;

        assertEquals(expected, orderService.countPages(FIRST_USER.getId(), REQUEST_PARAMETERS_WITH_PAGE_SIZE_100));
    }

}
