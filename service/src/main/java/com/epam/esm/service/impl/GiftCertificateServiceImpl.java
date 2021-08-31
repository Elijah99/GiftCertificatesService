package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.GiftCertificateTagDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.GiftCertificateTag;
import com.epam.esm.entity.Tag;
import com.epam.esm.mapper.impl.GiftCertificateMapper;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.enums.SearchParameter;
import com.epam.esm.enums.SortParameter;
import com.epam.esm.enums.SortType;
import com.epam.esm.exception.GiftCertificateNotFoundException;
import com.epam.esm.exception.InvalidSearchParametersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private GiftCertificateMapper giftCertificateMapper;
    private GiftCertificateDao giftCertificateDao;
    private TagDao tagDao;
    private GiftCertificateTagDao giftCertificateTagDao;

    public GiftCertificateServiceImpl() {
    }

    @Override
    public List<GiftCertificateDto> findAll() {
        List<GiftCertificate> giftCertificates = giftCertificateDao.findAll();
        return giftCertificateMapper.mapListEntityToListDto(giftCertificates);
    }

    @Override
    public GiftCertificateDto findById(BigInteger id) {
        Optional<GiftCertificate> giftCertificateOptional = giftCertificateDao.findById(id);
        if (giftCertificateOptional.isPresent()) {
            GiftCertificate giftCertificate = giftCertificateOptional.get();
            return giftCertificateMapper.mapEntityToDto(giftCertificate);
        } else {
            throw new GiftCertificateNotFoundException();
        }
    }

    @Override
    public void update(GiftCertificateDto giftCertificateDto, BigInteger id) {
        giftCertificateDto.setId(id);
        GiftCertificate giftCertificate = giftCertificateMapper.mapDtoToEntity(giftCertificateDto);
        giftCertificateDao.update(giftCertificate);
    }

    @Override
    public void deleteById(BigInteger id) {
        giftCertificateDao.deleteById(id);
    }

    @Transactional
    @Override
    public void save(GiftCertificateDto giftCertificateDto) {
        if (!giftCertificateDto.getTags().isEmpty()) {
            saveNewTags(giftCertificateDto);
        }
        GiftCertificate giftCertificate = giftCertificateMapper.mapDtoToEntity(giftCertificateDto);
        GiftCertificate savedGiftCertificate = giftCertificateDao.save(giftCertificate);
        if (CollectionUtils.isNotEmpty(savedGiftCertificate.getTags())) {
            bindCertificateWithTags(savedGiftCertificate);
        }
    }

    public void saveNewTags(GiftCertificateDto giftCertificateDto) {
        List<Tag> allTags = tagDao.findAll();
        GiftCertificate giftCertificate = giftCertificateMapper.mapDtoToEntity(giftCertificateDto);
        giftCertificate.getTags().forEach(tag -> {
            if (!allTags.contains(tag)) {
                tagDao.save(tag);
            }
        });
    }

    public void bindCertificateWithTags(GiftCertificate giftCertificate) {
        List<Tag> allTags = tagDao.findAll();
        giftCertificate.getTags().forEach(tag -> {
            if (allTags.contains(tag)) {
                int indexOfTag = allTags.indexOf(tag);
                BigInteger tagId = allTags.get(indexOfTag).getId();
                GiftCertificateTag giftCertificateTag = new GiftCertificateTag(giftCertificate.getId(), tagId);
                giftCertificateTagDao.save(giftCertificateTag);
            }
        });
    }

    @Override
    public List<GiftCertificateDto> searchByValue(SearchParameter searchParameter, String value) {
        switch (searchParameter) {
            case id:
            case name:
            case description: {
                List<GiftCertificate> giftCertificates = giftCertificateDao.searchByColumn(searchParameter.value, value);
                return giftCertificateMapper.mapListEntityToListDto(giftCertificates);
            }
            case tag: {
                List<GiftCertificate> giftCertificates = giftCertificateDao.searchByTagName(value);
                return giftCertificateMapper.mapListEntityToListDto(giftCertificates);
            }
            default: {
                throw new InvalidSearchParametersException();
            }
        }
    }

    @Override
    public List<GiftCertificateDto> sortByParameter(SortParameter sortParameter, SortType sortType) {
        List<GiftCertificate> giftCertificates =  giftCertificateDao.findAllWithOrder(sortParameter.value, sortType.value);
        return giftCertificateMapper.mapListEntityToListDto(giftCertificates);
    }

    @Autowired
    public void setGiftCertificateMapper(GiftCertificateMapper giftCertificateMapper) {
        this.giftCertificateMapper = giftCertificateMapper;
    }

    @Autowired
    public void setGiftCertificateDao(GiftCertificateDao giftCertificateDao) {
        this.giftCertificateDao = giftCertificateDao;
    }

    @Autowired
    public void setTagDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Autowired
    public void setGiftCertificateTagDao(GiftCertificateTagDao giftCertificateTagDao) {
        this.giftCertificateTagDao = giftCertificateTagDao;
    }
}
