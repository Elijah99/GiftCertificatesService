package com.epam.esm.service;

import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.entity.GiftCertificate;
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

    private static final GiftCertificate GIFT_CERTIFICATE = new GiftCertificate(new BigInteger("1"), "example",
            "description", new BigDecimal("100"), LocalDateTime.now(), LocalDateTime.now(), 7);

    private static List<GiftCertificate> certificateList;

    @Mock
    private GiftCertificateDaoImpl giftCertificateDaoMock;
    @Mock
    private TagDaoImpl tagDaoMock;
    @InjectMocks
    GiftCertificateServiceImpl giftCertificateService;

    @BeforeAll
    public static void setUp() {
        initCertificatesList();
    }

    private static void initCertificatesList() {
        certificateList = Arrays.asList(new GiftCertificate(new BigInteger("1"), "1st name",
                        "1st description", new BigDecimal("10"),
                        LocalDateTime.now(), LocalDateTime.now(), 7),
                new GiftCertificate(new BigInteger("2"), "2st name", "2nd description",
                        new BigDecimal("20"), LocalDateTime.now(), LocalDateTime.now(), 9));
    }

    @Test
    public void testGetAllShouldReturnGiftCertificateList() {
        when(giftCertificateDaoMock.findAll()).thenReturn(certificateList);
        assertEquals(giftCertificateService.findAll(), certificateList);
        verify(giftCertificateDaoMock).findAll();
        verifyNoMoreInteractions(giftCertificateDaoMock);
    }

    @Test
    public void testGetByIdShouldReturnGiftCertificate() {
        when(giftCertificateDaoMock.findById(any())).thenReturn(Optional.of(GIFT_CERTIFICATE));
        assertEquals(giftCertificateService.findById(any()), GIFT_CERTIFICATE);
        verify(giftCertificateDaoMock).findById(any());
        verifyNoMoreInteractions(giftCertificateDaoMock);
        verifyNoMoreInteractions(tagDaoMock);
    }

    @Test
    public void testDelete() {
        giftCertificateService.deleteById(any());
        verify(giftCertificateDaoMock).deleteById(any());
        verifyNoMoreInteractions(giftCertificateDaoMock);
        verifyNoInteractions(tagDaoMock);
    }
}
