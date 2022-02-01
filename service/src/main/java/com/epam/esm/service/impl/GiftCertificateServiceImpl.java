package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.RequestParameters;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.GiftCertificateNotFoundException;
import com.epam.esm.mapper.impl.GiftCertificateMapper;
import com.epam.esm.mapper.impl.RequestParametersMapper;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private GiftCertificateMapper giftCertificateMapper;
    private RequestParametersMapper requestParametersMapper;
    private GiftCertificateDao giftCertificateDao;

    public GiftCertificateServiceImpl() {
    }

    @Override
    public List<GiftCertificateDto> findAll(RequestParameters parameters) {
        List<GiftCertificate> giftCertificates = giftCertificateDao.findByParameters(requestParametersMapper.mapDtoToEntity(parameters));
        return giftCertificateMapper.mapListEntityToListDto(giftCertificates);
    }

    @Override
    public GiftCertificateDto findById(Long id) {
        Optional<GiftCertificate> giftCertificateOptional = giftCertificateDao.findById(id);
        if (giftCertificateOptional.isPresent()) {
            return giftCertificateMapper.mapEntityToDto(giftCertificateOptional.get());
        } else {
            throw new GiftCertificateNotFoundException();
        }
    }

    @Override
    public GiftCertificateDto update(GiftCertificateDto updatedGiftCertificate, Long id) {
        Optional<GiftCertificate> giftCertificateOptional = giftCertificateDao.findById(id);
        if (giftCertificateOptional.isPresent()) {
            GiftCertificate giftCertificate = giftCertificateOptional.get();
            updateGiftCertificateFields(giftCertificate, updatedGiftCertificate);
            giftCertificate.setLastUpdateDate(LocalDateTime.now());
            giftCertificateDao.update(giftCertificate);
            return giftCertificateMapper.mapEntityToDto(giftCertificate);
        } else {
            throw new GiftCertificateNotFoundException();
        }
    }

    public GiftCertificate updateGiftCertificateFields(GiftCertificate giftCertificate, GiftCertificateDto updatedGiftCertificate) {
        if (updatedGiftCertificate.getName() != null) {
            giftCertificate.setName(updatedGiftCertificate.getName());
        }
        if (updatedGiftCertificate.getDescription() != null) {
            giftCertificate.setDescription(updatedGiftCertificate.getDescription());
        }
        if (updatedGiftCertificate.getPrice() != null) {
            giftCertificate.setPrice(updatedGiftCertificate.getPrice());
        }
        if (updatedGiftCertificate.getDuration() != 0) {
            giftCertificate.setDuration(updatedGiftCertificate.getDuration());
        }
        if (updatedGiftCertificate.getTags() != null) {
            updateTags(giftCertificate, updatedGiftCertificate.getTags());
        }
        return giftCertificate;
    }

    public GiftCertificate updateTags(GiftCertificate giftCertificate, List<TagDto> tagDtos) {
        List<Tag> tags = giftCertificate.getTags();
        if (tags == null) {
            tags = new ArrayList<>();
        }
        if (tagDtos == null) {
            tagDtos = new ArrayList<>();
        }
        List<String> updatedTagNames = new ArrayList<>();
        List<String> storedTagNames = new ArrayList<>();
        if (!tags.isEmpty()) {
            tags.forEach(tag -> storedTagNames.add(tag.getName()));
        }

        if (!tagDtos.isEmpty()) {
            tagDtos.forEach(tagDto -> updatedTagNames.add(tagDto.getName()));
        }

        //removes tag from gift certificate if it doesn't present in updated array of gift certificate tags
        tags.forEach(tag -> {
            if (!updatedTagNames.contains(tag.getName())) {
                giftCertificate.removeTag(tag);
            }
        });

        //added new tag to gift certificate if it doesn't present now
        updatedTagNames.forEach(tagName -> {
            if (!storedTagNames.contains(tagName)) {
                giftCertificate.addTag(new Tag(tagName));

            }
        });

        return giftCertificate;
    }

    @Override
    public Long deleteById(Long id) {
        Optional<GiftCertificate> giftCertificateOptional = giftCertificateDao.findById(id);
        if (giftCertificateOptional.isPresent()) {
            return giftCertificateDao.deleteById(id);
        } else {
            throw new GiftCertificateNotFoundException();
        }
    }

    @Override
    public GiftCertificateDto save(GiftCertificateDto giftCertificateDto) {
        GiftCertificate giftCertificate = giftCertificateMapper.mapDtoToEntity(giftCertificateDto);

        giftCertificate.setCreateDate(LocalDateTime.now());
        giftCertificate.setLastUpdateDate(LocalDateTime.now());

        GiftCertificate savedGiftCertificate = giftCertificateDao.save(giftCertificate);
        return giftCertificateMapper.mapEntityToDto(savedGiftCertificate);
    }

    @Override
    public long countPages(RequestParameters requestParameters) {
        int pageSize = requestParameters.getPageSize();
        long elementsAmount = giftCertificateDao.count(
                requestParametersMapper.mapDtoToEntity(requestParameters));
        return elementsAmount % pageSize == 0
                ? elementsAmount / pageSize
                : elementsAmount / pageSize + 1;
    }

    @Override
    public long count(RequestParameters requestParameters){
        return giftCertificateDao.count(
                requestParametersMapper.mapDtoToEntity(requestParameters));
    }

    @Autowired
    public void setGiftCertificateMapper(GiftCertificateMapper giftCertificateMapper) {
        this.giftCertificateMapper = giftCertificateMapper;
    }

    @Autowired
    public void setRequestParametersMapper(RequestParametersMapper requestParametersMapper) {
        this.requestParametersMapper = requestParametersMapper;
    }

    @Autowired
    public void setGiftCertificateDao(GiftCertificateDao giftCertificateDao) {
        this.giftCertificateDao = giftCertificateDao;
    }
}
