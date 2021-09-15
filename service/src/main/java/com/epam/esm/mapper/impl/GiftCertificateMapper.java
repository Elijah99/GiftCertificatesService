package com.epam.esm.mapper.impl;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.mapper.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GiftCertificateMapper implements DtoMapper<GiftCertificate, GiftCertificateDto> {

    private final TagMapper tagMapper;

    @Autowired
    public GiftCertificateMapper(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Override
    public GiftCertificateDto mapEntityToDto(GiftCertificate entity) {
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto();
        giftCertificateDto.setId(entity.getId());
        giftCertificateDto.setName(entity.getName());
        giftCertificateDto.setDescription(entity.getDescription());
        giftCertificateDto.setPrice(entity.getPrice());
        giftCertificateDto.setDuration(entity.getDuration());
        giftCertificateDto.setCreateDate(entity.getCreateDate());
        giftCertificateDto.setLastUpdateDate(entity.getLastUpdateDate());
        if (entity.getTags() != null) {
            giftCertificateDto.setTags(tagMapper.mapListEntityToListDto(entity.getTags()));
        }
        return giftCertificateDto;
    }

    @Override
    public GiftCertificate mapDtoToEntity(GiftCertificateDto dto) {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(dto.getId());
        giftCertificate.setName(dto.getName());
        giftCertificate.setDescription(dto.getDescription());
        giftCertificate.setPrice(dto.getPrice());
        giftCertificate.setDuration(dto.getDuration());
        giftCertificate.setCreateDate(dto.getCreateDate());
        giftCertificate.setLastUpdateDate(dto.getLastUpdateDate());
        if (dto.getTags() != null) {
            giftCertificate.setTags(tagMapper.mapListDtoToListEntity(dto.getTags()));
        }
        return giftCertificate;
    }
}
