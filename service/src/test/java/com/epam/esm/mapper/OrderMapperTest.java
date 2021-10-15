package com.epam.esm.mapper;

import com.epam.esm.dto.OrderDto;
import com.epam.esm.entity.Order;
import com.epam.esm.mapper.impl.GiftCertificateMapper;
import com.epam.esm.mapper.impl.OrderMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static com.epam.esm.DaoTestData.ALL_ORDERS;
import static com.epam.esm.DaoTestData.FIFTH_ORDER;
import static com.epam.esm.DaoTestData.FIRST_GIFT_CERTIFICATE;
import static com.epam.esm.DaoTestData.FIRST_ORDER;
import static com.epam.esm.DaoTestData.FOURTH_ORDER;
import static com.epam.esm.DaoTestData.SECOND_GIFT_CERTIFICATE;
import static com.epam.esm.DaoTestData.SECOND_ORDER;
import static com.epam.esm.DaoTestData.THIRD_ORDER;
import static com.epam.esm.ServiceTestData.ALL_ORDERS_DTO;
import static com.epam.esm.ServiceTestData.FIFTH_ORDER_DTO;
import static com.epam.esm.ServiceTestData.FIRST_GIFT_CERTIFICATE_DTO;
import static com.epam.esm.ServiceTestData.FIRST_ORDER_DTO;
import static com.epam.esm.ServiceTestData.FOURTH_ORDER_DTO;
import static com.epam.esm.ServiceTestData.SECOND_GIFT_CERTIFICATE_DTO;
import static com.epam.esm.ServiceTestData.SECOND_ORDER_DTO;
import static com.epam.esm.ServiceTestData.THIRD_ORDER_DTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class OrderMapperTest {

    @Mock
    private GiftCertificateMapper giftCertificateMapperMock;
    @InjectMocks
    private OrderMapper orderMapper;

    @Test
    public void testMapEntityToDto() {
        when(giftCertificateMapperMock.mapListEntityToListDto(Arrays.asList(FIRST_GIFT_CERTIFICATE, SECOND_GIFT_CERTIFICATE)))
                .thenReturn(Arrays.asList(FIRST_GIFT_CERTIFICATE_DTO, SECOND_GIFT_CERTIFICATE_DTO));

        OrderDto actual = orderMapper.mapEntityToDto(FIRST_ORDER);
        assertEquals(FIRST_ORDER_DTO, actual);

        verify(giftCertificateMapperMock).mapListEntityToListDto(Arrays.asList(FIRST_GIFT_CERTIFICATE, SECOND_GIFT_CERTIFICATE));
        verifyNoMoreInteractions(giftCertificateMapperMock);
    }

    @Test
    public void testMapDtoToEntity() {
        when(giftCertificateMapperMock.mapListDtoToListEntity(FIRST_ORDER_DTO.getGiftCertificates()))
                .thenReturn(FIRST_ORDER.getGiftCertificates());

        Order actual = orderMapper.mapDtoToEntity(FIRST_ORDER_DTO);
        assertEquals(FIRST_ORDER, actual);

        verify(giftCertificateMapperMock).mapListDtoToListEntity(FIRST_ORDER_DTO.getGiftCertificates());
        verifyNoMoreInteractions(giftCertificateMapperMock);
    }

    @Test
    public void testMapListDtoToListEntity() {
        when(giftCertificateMapperMock.mapListDtoToListEntity(FIRST_ORDER_DTO.getGiftCertificates()))
                .thenReturn(FIRST_ORDER.getGiftCertificates());
        when(giftCertificateMapperMock.mapListDtoToListEntity(SECOND_ORDER_DTO.getGiftCertificates()))
                .thenReturn(SECOND_ORDER.getGiftCertificates());
        when(giftCertificateMapperMock.mapListDtoToListEntity(THIRD_ORDER_DTO.getGiftCertificates()))
                .thenReturn(THIRD_ORDER.getGiftCertificates());
        when(giftCertificateMapperMock.mapListDtoToListEntity(FOURTH_ORDER_DTO.getGiftCertificates()))
                .thenReturn(FOURTH_ORDER.getGiftCertificates());
        when(giftCertificateMapperMock.mapListDtoToListEntity(FIFTH_ORDER_DTO.getGiftCertificates()))
                .thenReturn(FIFTH_ORDER.getGiftCertificates());

        List<Order> actual = orderMapper.mapListDtoToListEntity(ALL_ORDERS_DTO);

        assertEquals(ALL_ORDERS, actual);

        verify(giftCertificateMapperMock).mapListDtoToListEntity(FIRST_ORDER_DTO.getGiftCertificates());
        verify(giftCertificateMapperMock).mapListDtoToListEntity(SECOND_ORDER_DTO.getGiftCertificates());
        verify(giftCertificateMapperMock).mapListDtoToListEntity(THIRD_ORDER_DTO.getGiftCertificates());
        verify(giftCertificateMapperMock).mapListDtoToListEntity(FOURTH_ORDER_DTO.getGiftCertificates());
        verify(giftCertificateMapperMock).mapListDtoToListEntity(FIFTH_ORDER_DTO.getGiftCertificates());
        verifyNoMoreInteractions(giftCertificateMapperMock);
    }

    @Test
    public void testMapListEntityToListDto() {
        when(giftCertificateMapperMock.mapListEntityToListDto(FIRST_ORDER.getGiftCertificates()))
                .thenReturn(FIRST_ORDER_DTO.getGiftCertificates());
        when(giftCertificateMapperMock.mapListEntityToListDto(SECOND_ORDER.getGiftCertificates()))
                .thenReturn(SECOND_ORDER_DTO.getGiftCertificates());
        when(giftCertificateMapperMock.mapListEntityToListDto(THIRD_ORDER.getGiftCertificates()))
                .thenReturn(THIRD_ORDER_DTO.getGiftCertificates());
        when(giftCertificateMapperMock.mapListEntityToListDto(FOURTH_ORDER.getGiftCertificates()))
                .thenReturn(FOURTH_ORDER_DTO.getGiftCertificates());
        when(giftCertificateMapperMock.mapListEntityToListDto(FIFTH_ORDER.getGiftCertificates()))
                .thenReturn(FIFTH_ORDER_DTO.getGiftCertificates());

        List<OrderDto> actual = orderMapper.mapListEntityToListDto(ALL_ORDERS);
        assertEquals(ALL_ORDERS_DTO, actual);

        verify(giftCertificateMapperMock).mapListEntityToListDto(FIRST_ORDER.getGiftCertificates());
        verify(giftCertificateMapperMock).mapListEntityToListDto(SECOND_ORDER.getGiftCertificates());
        verify(giftCertificateMapperMock).mapListEntityToListDto(THIRD_ORDER.getGiftCertificates());
        verify(giftCertificateMapperMock).mapListEntityToListDto(FOURTH_ORDER.getGiftCertificates());
        verify(giftCertificateMapperMock).mapListEntityToListDto(FIFTH_ORDER.getGiftCertificates());
        verifyNoMoreInteractions(giftCertificateMapperMock);
    }
}
