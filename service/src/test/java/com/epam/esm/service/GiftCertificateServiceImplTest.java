package com.epam.esm.service;

import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.mapper.impl.GiftCertificateMapper;
import com.epam.esm.service.impl.GiftCertificateServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GiftCertificateServiceImplTest {

    private static final GiftCertificate GIFT_CERTIFICATE = new GiftCertificate("example",
            "description", new BigDecimal("100"), LocalDateTime.now(), LocalDateTime.now(), 7);
    private static final GiftCertificateDto GIFT_CERTIFICATE_DTO = new GiftCertificateDto("example",
            "description", new BigDecimal("100"), LocalDateTime.now(), LocalDateTime.now(), 7);
    private static final GiftCertificate GIFT_CERTIFICATE_WITH_ID = new GiftCertificate(new BigInteger("1"), "example",
            "description", new BigDecimal("100"), LocalDateTime.now(), LocalDateTime.now(), 7);
    private static final GiftCertificateDto GIFT_CERTIFICATE_DTO_WITH_ID = new GiftCertificateDto(new BigInteger("1"),
            "example", "description", new BigDecimal("100"), LocalDateTime.now(), LocalDateTime.now(), 7);

    private static List<GiftCertificate> certificateList;
    private static List<GiftCertificateDto> certificateDtoList;

    @Mock
    private GiftCertificateDaoImpl giftCertificateDaoMock;
    @Mock
    private TagDaoImpl tagDaoMock;
    @Mock
    private GiftCertificateMapper giftCertificateMapper;
    @InjectMocks
    GiftCertificateServiceImpl giftCertificateService;

    @BeforeAll
    public static void setUp() {
        initCertificatesLists();
    }

    private static void initCertificatesLists() {
        certificateList = Arrays.asList(new GiftCertificate(new BigInteger("1"), "1st name",
                        "1st description", new BigDecimal("10"),
                        LocalDateTime.now(), LocalDateTime.now(), 7),
                new GiftCertificate(new BigInteger("2"), "2st name", "2nd description",
                        new BigDecimal("20"), LocalDateTime.now(), LocalDateTime.now(), 9));

        certificateDtoList = Arrays.asList(new GiftCertificateDto(new BigInteger("1"), "1st name",
                        "1st description", new BigDecimal("10"),
                        LocalDateTime.now(), LocalDateTime.now(), 7),
                new GiftCertificateDto(new BigInteger("2"), "2st name", "2nd description",
                        new BigDecimal("20"), LocalDateTime.now(), LocalDateTime.now(), 9));
    }

    @Test
    public void testFindAllShouldReturnGiftCertificateList() {
        when(giftCertificateDaoMock.findAll()).thenReturn(certificateList);
        when(giftCertificateMapper.mapListEntityToListDto(certificateList)).thenReturn(certificateDtoList);
        assertEquals(giftCertificateService.findAll(), certificateDtoList);
        verify(giftCertificateDaoMock).findAll();
        verifyNoMoreInteractions(giftCertificateDaoMock);
    }

    @Test
    public void testFindByIdShouldReturnGiftCertificate() {
        when(giftCertificateDaoMock.findById(any())).thenReturn(Optional.of(GIFT_CERTIFICATE));
        when(giftCertificateMapper.mapEntityToDto(GIFT_CERTIFICATE)).thenReturn(GIFT_CERTIFICATE_DTO);
        assertEquals(giftCertificateService.findById(any()), GIFT_CERTIFICATE_DTO);
        verify(giftCertificateDaoMock).findById(any());
        verifyNoMoreInteractions(giftCertificateDaoMock);
        verifyNoMoreInteractions(tagDaoMock);
    }

    @Test
    public void testUpdate() {
        when(giftCertificateMapper.mapDtoToEntity(GIFT_CERTIFICATE_DTO)).thenReturn(GIFT_CERTIFICATE);
        verify(giftCertificateDaoMock).update(any());
        verifyNoMoreInteractions(giftCertificateDaoMock);
        verifyNoInteractions(tagDaoMock);
    }

    @Test
    public void testSaveShouldReturnGiftCertificate() {
        when(giftCertificateMapper.mapDtoToEntity(GIFT_CERTIFICATE_DTO)).thenReturn(GIFT_CERTIFICATE);
        when(giftCertificateDaoMock.save(GIFT_CERTIFICATE)).thenReturn(GIFT_CERTIFICATE_WITH_ID);
        giftCertificateService.save(GIFT_CERTIFICATE_DTO);
        verify(giftCertificateDaoMock).save(GIFT_CERTIFICATE);
        verifyNoMoreInteractions(giftCertificateDaoMock);
        verifyNoInteractions(tagDaoMock);
    }

    @Test
    public void testDelete() {
        giftCertificateService.deleteById(any());
        verify(giftCertificateDaoMock).deleteById(any());
        verifyNoMoreInteractions(giftCertificateDaoMock);
        verifyNoInteractions(tagDaoMock);
    }
}
