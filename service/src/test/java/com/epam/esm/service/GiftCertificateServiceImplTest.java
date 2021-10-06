package com.epam.esm.service;

import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.exception.GiftCertificateNotFoundException;
import com.epam.esm.mapper.impl.GiftCertificateMapper;
import com.epam.esm.service.impl.GiftCertificateServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.epam.esm.ServiceTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GiftCertificateServiceImplTest {


    @InjectMocks
    GiftCertificateServiceImpl giftCertificateService;
    @Mock
    private GiftCertificateDaoImpl giftCertificateDaoMock;
    @Mock
    private GiftCertificateMapper giftCertificateMapperMock;

    @Test
    public void testFindByIdShouldReturnGiftCertificate() {
        when(giftCertificateDaoMock.findById(FIRST_GIFT_CERTIFICATE.getId())).thenReturn(FIRST_GIFT_CERTIFICATE_OPTIONAL);
        when(giftCertificateMapperMock.mapEntityToDto(FIRST_GIFT_CERTIFICATE)).thenReturn(FIRST_GIFT_CERTIFICATE_DTO);

        assertEquals(giftCertificateService.findById(FIRST_GIFT_CERTIFICATE.getId()), FIRST_GIFT_CERTIFICATE_DTO);

        verify(giftCertificateDaoMock).findById(FIRST_GIFT_CERTIFICATE.getId());
        verifyNoMoreInteractions(giftCertificateDaoMock);
    }

    @Test
    public void testUpdate() {
        when(giftCertificateDaoMock.findById(FIRST_GIFT_CERTIFICATE.getId())).thenReturn(FIRST_GIFT_CERTIFICATE_OPTIONAL);

        giftCertificateService.update(FIRST_GIFT_CERTIFICATE_DTO, FIRST_GIFT_CERTIFICATE.getId());

        verify(giftCertificateDaoMock).update(any());
        verifyNoMoreInteractions(giftCertificateDaoMock);
    }

    @Test
    public void testSaveShouldReturnGiftCertificate() {
        when(giftCertificateMapperMock.mapDtoToEntity(GIFT_CERTIFICATE_FOR_SAVE_DTO)).thenReturn(GIFT_CERTIFICATE_FOR_SAVE);
        when(giftCertificateDaoMock.save(GIFT_CERTIFICATE_FOR_SAVE)).thenReturn(GIFT_CERTIFICATE_SAVED);
        when(giftCertificateMapperMock.mapEntityToDto(GIFT_CERTIFICATE_SAVED)).thenReturn(GIFT_CERTIFICATE_SAVED_DTO);

        assertEquals(GIFT_CERTIFICATE_SAVED_DTO, giftCertificateService.save(GIFT_CERTIFICATE_FOR_SAVE_DTO));

        verify(giftCertificateDaoMock).save(GIFT_CERTIFICATE_FOR_SAVE);
        verifyNoMoreInteractions(giftCertificateDaoMock);
    }

    @Test
    public void testDeleteByIdShouldReturnIdDeleted() {
        when(giftCertificateDaoMock.findById(FIRST_GIFT_CERTIFICATE.getId())).thenReturn(FIRST_GIFT_CERTIFICATE_OPTIONAL);
        when(giftCertificateDaoMock.deleteById(FIRST_GIFT_CERTIFICATE.getId())).thenReturn(FIRST_GIFT_CERTIFICATE.getId());

        assertEquals(FIRST_GIFT_CERTIFICATE.getId(), giftCertificateService.deleteById(FIRST_GIFT_CERTIFICATE.getId()));

        verify(giftCertificateDaoMock).deleteById(FIRST_GIFT_CERTIFICATE.getId());
        verifyNoMoreInteractions(giftCertificateDaoMock);
    }

    @Test
    public void testDeleteShouldThrowNotFoundExceptionWhenGiftCertificateNotFound() {
        when(giftCertificateDaoMock.findById(FIRST_GIFT_CERTIFICATE.getId())).thenReturn(Optional.empty());

        assertThrows(GiftCertificateNotFoundException.class, () -> {
            assertEquals(FIRST_GIFT_CERTIFICATE.getId(), giftCertificateService.deleteById(FIRST_GIFT_CERTIFICATE.getId()));
        });

        verify(giftCertificateDaoMock).findById(FIRST_GIFT_CERTIFICATE.getId());
        verifyNoMoreInteractions(giftCertificateDaoMock);
    }

    @Test
    public void testUpdateShouldThrowNotFoundExceptionWhenGiftCertificateNotFound() {
        when(giftCertificateDaoMock.findById(FIRST_GIFT_CERTIFICATE.getId())).thenReturn(Optional.empty());

        assertThrows(GiftCertificateNotFoundException.class, () -> {
            giftCertificateService.update(FIRST_GIFT_CERTIFICATE_DTO, FIRST_GIFT_CERTIFICATE.getId());
        });

        verify(giftCertificateDaoMock).findById(FIRST_GIFT_CERTIFICATE.getId());
        verifyNoMoreInteractions(giftCertificateDaoMock);
    }
}
