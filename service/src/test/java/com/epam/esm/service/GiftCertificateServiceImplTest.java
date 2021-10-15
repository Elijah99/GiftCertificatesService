package com.epam.esm.service;

import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.exception.GiftCertificateNotFoundException;
import com.epam.esm.mapper.impl.GiftCertificateMapper;
import com.epam.esm.mapper.impl.RequestParametersMapper;
import com.epam.esm.service.impl.GiftCertificateServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static com.epam.esm.ServiceTestData.ALL_GIFT_CERTIFICATES;
import static com.epam.esm.ServiceTestData.ALL_GIFT_CERTIFICATES_DTO;
import static com.epam.esm.ServiceTestData.DEFAULT_QUERY_PARAMETERS;
import static com.epam.esm.ServiceTestData.DEFAULT_REQUEST_PARAMETERS;
import static com.epam.esm.ServiceTestData.FIRST_GIFT_CERTIFICATE;
import static com.epam.esm.ServiceTestData.FIRST_GIFT_CERTIFICATE_DTO;
import static com.epam.esm.ServiceTestData.FIRST_GIFT_CERTIFICATE_OPTIONAL;
import static com.epam.esm.ServiceTestData.GIFT_CERTIFICATE_DTO_WITH_NULL_FIELDS;
import static com.epam.esm.ServiceTestData.GIFT_CERTIFICATE_FOR_SAVE;
import static com.epam.esm.ServiceTestData.GIFT_CERTIFICATE_FOR_SAVE_DTO;
import static com.epam.esm.ServiceTestData.GIFT_CERTIFICATE_SAVED;
import static com.epam.esm.ServiceTestData.GIFT_CERTIFICATE_SAVED_DTO;
import static com.epam.esm.ServiceTestData.REQUEST_PARAMETERS_WITH_PAGE_SIZE_1;
import static com.epam.esm.ServiceTestData.REQUEST_PARAMETERS_WITH_PAGE_SIZE_100;
import static com.epam.esm.ServiceTestData.REQUEST_PARAMETERS_WITH_PAGE_SIZE_9;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class GiftCertificateServiceImplTest {


    @InjectMocks
    GiftCertificateServiceImpl giftCertificateService;
    @Mock
    private GiftCertificateDaoImpl giftCertificateDaoMock;
    @Mock
    private GiftCertificateMapper giftCertificateMapperMock;
    @Mock
    private RequestParametersMapper requestParametersMapperMock;


    @Test
    public void testFindAll() {
        when(giftCertificateDaoMock.findByParameters(DEFAULT_QUERY_PARAMETERS)).thenReturn(ALL_GIFT_CERTIFICATES);
        when(requestParametersMapperMock.mapDtoToEntity(DEFAULT_REQUEST_PARAMETERS)).thenReturn(DEFAULT_QUERY_PARAMETERS);
        when(giftCertificateMapperMock.mapListEntityToListDto(ALL_GIFT_CERTIFICATES)).thenReturn(ALL_GIFT_CERTIFICATES_DTO);

        giftCertificateService.findAll(DEFAULT_REQUEST_PARAMETERS);

        verify(giftCertificateDaoMock).findByParameters(DEFAULT_QUERY_PARAMETERS);
        verify(requestParametersMapperMock).mapDtoToEntity(DEFAULT_REQUEST_PARAMETERS);
        verify(giftCertificateMapperMock).mapListEntityToListDto(ALL_GIFT_CERTIFICATES);
        verifyNoMoreInteractions(giftCertificateDaoMock, requestParametersMapperMock, giftCertificateMapperMock);
    }

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

        giftCertificateService.update(FIRST_GIFT_CERTIFICATE_DTO, FIRST_GIFT_CERTIFICATE_DTO.getId());

        verify(giftCertificateDaoMock).findById(FIRST_GIFT_CERTIFICATE.getId());
    }

    @Test
    public void testUpdateGiftCertificateWithoutTags() {
        when(giftCertificateDaoMock.findById(FIRST_GIFT_CERTIFICATE.getId())).thenReturn(FIRST_GIFT_CERTIFICATE_OPTIONAL);

        giftCertificateService.update(GIFT_CERTIFICATE_DTO_WITH_NULL_FIELDS, FIRST_GIFT_CERTIFICATE.getId());

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

    @Test
    public void testCountPagesWhenPageSizeIsMultipleOfNumberRecords() {
        when(giftCertificateDaoMock.count()).thenReturn(10L);
        long expected = 10;

        assertEquals(expected, giftCertificateService.countPages(REQUEST_PARAMETERS_WITH_PAGE_SIZE_1));
    }

    @Test
    public void testCountPagesWhenPageSizeNotMultipleOfNumberRecords() {
        when(giftCertificateDaoMock.count()).thenReturn(10L);
        long expected = 2;

        assertEquals(expected, giftCertificateService.countPages(REQUEST_PARAMETERS_WITH_PAGE_SIZE_9));
    }

    @Test
    public void testCountPagesWhenPageSizeMoreThanNumberRecords() {
        when(giftCertificateDaoMock.count()).thenReturn(10L);
        long expected = 1;

        assertEquals(expected, giftCertificateService.countPages(REQUEST_PARAMETERS_WITH_PAGE_SIZE_100));
    }
}
