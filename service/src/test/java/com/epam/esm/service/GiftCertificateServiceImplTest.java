package com.epam.esm.service;

import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.enums.SearchParameter;
import com.epam.esm.enums.SortParameter;
import com.epam.esm.enums.SortType;
import com.epam.esm.exception.GiftCertificateNotFoundException;
import com.epam.esm.mapper.impl.GiftCertificateMapper;
import com.epam.esm.service.impl.GiftCertificateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    private static final Optional<GiftCertificate> GIFT_CERTIFICATE_OPTIONAL = Optional.of(GIFT_CERTIFICATE);
    private static final BigInteger GIFT_CERTIFICATE_ID = new BigInteger("1");
    private static final String SEARCH_VALUE = "name";


    private static List<GiftCertificate> certificateList;
    private static List<GiftCertificateDto> certificateDtoList;

    @InjectMocks
    GiftCertificateServiceImpl giftCertificateService;
    @Mock
    private GiftCertificateDaoImpl giftCertificateDaoMock;
    @Mock
    private TagDaoImpl tagDaoMock;
    @Mock
    private GiftCertificateMapper giftCertificateMapperMock;

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
        when(giftCertificateMapperMock.mapListEntityToListDto(certificateList)).thenReturn(certificateDtoList);

        assertEquals(giftCertificateService.findAll(), certificateDtoList);

        verify(giftCertificateDaoMock).findAll();
        verifyNoMoreInteractions(giftCertificateDaoMock);
    }

    @Test
    public void testFindByIdShouldReturnGiftCertificate() {
        when(giftCertificateDaoMock.findById(any())).thenReturn(Optional.of(GIFT_CERTIFICATE));
        when(giftCertificateMapperMock.mapEntityToDto(GIFT_CERTIFICATE)).thenReturn(GIFT_CERTIFICATE_DTO);

        assertEquals(giftCertificateService.findById(any()), GIFT_CERTIFICATE_DTO);

        verify(giftCertificateDaoMock).findById(any());
        verifyNoMoreInteractions(giftCertificateDaoMock);
        verifyNoMoreInteractions(tagDaoMock);
    }

    @Test
    public void testUpdate() {
        when(giftCertificateMapperMock.mapDtoToEntity(GIFT_CERTIFICATE_DTO)).thenReturn(GIFT_CERTIFICATE);
        when(giftCertificateDaoMock.findById(GIFT_CERTIFICATE_ID)).thenReturn(GIFT_CERTIFICATE_OPTIONAL);

        giftCertificateService.update(GIFT_CERTIFICATE_DTO, GIFT_CERTIFICATE_ID);

        verify(giftCertificateDaoMock).update(any());
        verifyNoMoreInteractions(giftCertificateDaoMock);
        verifyNoInteractions(tagDaoMock);
    }

    @Test
    public void testSaveShouldReturnGiftCertificate() {
        when(giftCertificateMapperMock.mapDtoToEntity(GIFT_CERTIFICATE_DTO)).thenReturn(GIFT_CERTIFICATE);
        when(giftCertificateDaoMock.save(GIFT_CERTIFICATE)).thenReturn(GIFT_CERTIFICATE_WITH_ID);
        when(giftCertificateMapperMock.mapEntityToDto(GIFT_CERTIFICATE_WITH_ID)).thenReturn(GIFT_CERTIFICATE_DTO_WITH_ID);

        assertEquals(GIFT_CERTIFICATE_DTO_WITH_ID, giftCertificateService.save(GIFT_CERTIFICATE_DTO));

        verify(giftCertificateDaoMock).save(GIFT_CERTIFICATE);
        verifyNoMoreInteractions(giftCertificateDaoMock);
        verifyNoInteractions(tagDaoMock);
    }

    @Test
    public void testDeleteByIdShouldReturnIdDeleted() {
        when(giftCertificateDaoMock.findById(GIFT_CERTIFICATE_ID)).thenReturn(GIFT_CERTIFICATE_OPTIONAL);
        when(giftCertificateDaoMock.deleteById(GIFT_CERTIFICATE_ID)).thenReturn(GIFT_CERTIFICATE_ID);

        assertEquals(GIFT_CERTIFICATE_ID, giftCertificateService.deleteById(GIFT_CERTIFICATE_ID));

        verify(giftCertificateDaoMock).deleteById(GIFT_CERTIFICATE_ID);
        verifyNoMoreInteractions(giftCertificateDaoMock);
        verifyNoInteractions(tagDaoMock);
    }

    @Test
    public void testDeleteShouldThrowNotFoundExceptionWhenGiftCertificateNotFound() {
        when(giftCertificateDaoMock.findById(GIFT_CERTIFICATE_ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(GiftCertificateNotFoundException.class, () -> {
            assertEquals(GIFT_CERTIFICATE_ID, giftCertificateService.deleteById(GIFT_CERTIFICATE_ID));
        });

        verify(giftCertificateDaoMock).findById(GIFT_CERTIFICATE_ID);
        verifyNoMoreInteractions(giftCertificateDaoMock);
        verifyNoInteractions(tagDaoMock);
    }

    @Test
    public void testUpdateShouldThrowNotFoundExceptionWhenGiftCertificateNotFound() {
        when(giftCertificateDaoMock.findById(GIFT_CERTIFICATE_ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(GiftCertificateNotFoundException.class, () -> {
            giftCertificateService.update(GIFT_CERTIFICATE_DTO, GIFT_CERTIFICATE_ID);
        });

        verify(giftCertificateDaoMock).findById(GIFT_CERTIFICATE_ID);
        verifyNoMoreInteractions(giftCertificateDaoMock);
        verifyNoInteractions(tagDaoMock);
    }

    @Test
    public void testSearchByValue() {
        when(giftCertificateDaoMock.searchByColumn(SearchParameter.name.value, SEARCH_VALUE)).thenReturn(certificateList);
        when(giftCertificateMapperMock.mapListEntityToListDto(certificateList)).thenReturn(certificateDtoList);

        assertEquals(certificateDtoList, giftCertificateService.searchByValue(SearchParameter.name, SEARCH_VALUE));

        verify(giftCertificateDaoMock).searchByColumn(SearchParameter.name.value, SEARCH_VALUE);
        verify(giftCertificateMapperMock).mapListEntityToListDto(certificateList);
        verifyNoMoreInteractions(giftCertificateDaoMock);
        verifyNoInteractions(tagDaoMock);
    }

    @Test
    public void testSortByParameter() {
        when(giftCertificateDaoMock.findAllWithOrder(SortParameter.name.value, SortType.asc.value)).thenReturn(certificateList);
        when(giftCertificateMapperMock.mapListEntityToListDto(certificateList)).thenReturn(certificateDtoList);

        assertEquals(certificateDtoList, giftCertificateService.sortByParameter(SortParameter.name, SortType.asc));

        verify(giftCertificateDaoMock).findAllWithOrder(SortParameter.name.value, SortType.asc.value);
        verify(giftCertificateMapperMock).mapListEntityToListDto(certificateList);
        verifyNoMoreInteractions(giftCertificateDaoMock);
        verifyNoInteractions(tagDaoMock);
    }
}
