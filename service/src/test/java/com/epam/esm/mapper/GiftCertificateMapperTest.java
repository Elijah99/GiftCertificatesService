package com.epam.esm.mapper;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.mapper.impl.GiftCertificateMapper;
import com.epam.esm.mapper.impl.TagMapper;
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
public class GiftCertificateMapperTest {

    @Mock
    private TagMapper tagMapperMock;
    @InjectMocks
    private GiftCertificateMapper giftCertificateMapper;

    @Test
    public void testMapEntityToDto() {
        when(tagMapperMock.mapListEntityToListDto(FIRST_GIFT_CERTIFICATE.getTags()))
                .thenReturn(FIRST_GIFT_CERTIFICATE_DTO.getTags());

        GiftCertificateDto actual = giftCertificateMapper.mapEntityToDto(FIRST_GIFT_CERTIFICATE);

        assertEquals(FIRST_GIFT_CERTIFICATE_DTO, actual);

        verify(tagMapperMock).mapListEntityToListDto(FIRST_GIFT_CERTIFICATE.getTags());
        verifyNoMoreInteractions(tagMapperMock);
    }

    @Test
    public void testMapDtoToEntity() {
        when(tagMapperMock.mapDtoToEntity(any())).thenReturn(FIRST_TAG).thenReturn(SECOND_TAG);

        GiftCertificate actual = giftCertificateMapper.mapDtoToEntity(FIRST_GIFT_CERTIFICATE_DTO);
        assertEquals(FIRST_GIFT_CERTIFICATE, actual);

        verify(tagMapperMock, times(2)).mapDtoToEntity(any());
    }

    @Test
    public void testMapListDtoToListEntity() {
        when(tagMapperMock.mapDtoToEntity(FIRST_TAG_DTO))
                .thenReturn(FIRST_TAG);
        when(tagMapperMock.mapDtoToEntity(SECOND_TAG_DTO))
                .thenReturn(FIRST_TAG);
        when(tagMapperMock.mapDtoToEntity(THIRD_TAG_DTO))
                .thenReturn(FIRST_TAG);
        when(tagMapperMock.mapDtoToEntity(FOURTH_TAG_DTO))
                .thenReturn(FIRST_TAG);

        List<GiftCertificate> actual = giftCertificateMapper.mapListDtoToListEntity(ALL_GIFT_CERTIFICATES_DTO);
        assertEquals(ALL_GIFT_CERTIFICATES, actual);


    }

    @Test
    public void testMapListEntityToListDto() {
        when(tagMapperMock.mapListEntityToListDto(FIRST_GIFT_CERTIFICATE.getTags()))
                .thenReturn(FIRST_GIFT_CERTIFICATE_DTO.getTags());
        when(tagMapperMock.mapListEntityToListDto(SECOND_GIFT_CERTIFICATE.getTags()))
                .thenReturn(SECOND_GIFT_CERTIFICATE_DTO.getTags());
        when(tagMapperMock.mapListEntityToListDto(THIRD_GIFT_CERTIFICATE.getTags()))
                .thenReturn(THIRD_GIFT_CERTIFICATE_DTO.getTags());
        when(tagMapperMock.mapListEntityToListDto(FOURTH_GIFT_CERTIFICATE.getTags()))
                .thenReturn(FOURTH_GIFT_CERTIFICATE_DTO.getTags());

        List<GiftCertificateDto> actual = giftCertificateMapper.mapListEntityToListDto(ALL_GIFT_CERTIFICATES);
        assertEquals(ALL_GIFT_CERTIFICATES_DTO, actual);

        verify(tagMapperMock).mapListEntityToListDto(FIRST_GIFT_CERTIFICATE.getTags());
        verify(tagMapperMock).mapListEntityToListDto(SECOND_GIFT_CERTIFICATE.getTags());
        verify(tagMapperMock).mapListEntityToListDto(THIRD_GIFT_CERTIFICATE.getTags());
        verify(tagMapperMock).mapListEntityToListDto(FOURTH_GIFT_CERTIFICATE.getTags());
        verifyNoMoreInteractions(tagMapperMock);
    }
}
