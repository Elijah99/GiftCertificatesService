package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.GiftCertificateTagDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.GiftCertificateTag;
import com.epam.esm.entity.Tag;
import com.epam.esm.enums.SearchParameter;
import com.epam.esm.enums.SortParameter;
import com.epam.esm.enums.SortType;
import com.epam.esm.exception.GiftCertificateNotFoundException;
import com.epam.esm.exception.InvalidSearchParametersException;
import com.epam.esm.mapper.impl.GiftCertificateMapper;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private GiftCertificateMapper giftCertificateMapper;
    private GiftCertificateDao giftCertificateDao;
    private TagDao tagDao;

    public GiftCertificateServiceImpl() {
    }

    @Override
    public List<GiftCertificateDto> findAll() {
        List<GiftCertificate> giftCertificates = giftCertificateDao.findAll();
        return giftCertificateMapper.mapListEntityToListDto(giftCertificates);
    }

    private List<GiftCertificate> bindCertificatesWithTags(List<GiftCertificate> giftCertificates,
                                                           List<GiftCertificateTag> giftCertificateTags,
                                                           List<Tag> tags) {
        giftCertificates.forEach(giftCertificate -> {
            BigInteger idGiftCertificate = giftCertificate.getId();
            List<Tag> foundTags = new ArrayList<>();
            giftCertificateTags.forEach(giftCertificateTag -> {
                if (idGiftCertificate.equals(giftCertificateTag.getIdGiftCertificate())) {
                    tags.forEach(tag -> {
                        if (tag.getId().equals(giftCertificateTag.getIdTag())) {
                            foundTags.add(tag);
                        }
                    });
                    giftCertificate.setTags(foundTags);
                }
            });
        });
        return giftCertificates;
    }

    @Override
    public GiftCertificateDto findById(BigInteger id) {
        Optional<GiftCertificate> giftCertificateOptional = giftCertificateDao.findById(id);
        if (giftCertificateOptional.isPresent()) {
            return giftCertificateMapper.mapEntityToDto(giftCertificateOptional.get());
        } else {
            throw new GiftCertificateNotFoundException();
        }
    }

    @Override
    public GiftCertificateDto update(GiftCertificateDto giftCertificateDto, BigInteger id) {
        Optional<GiftCertificate> giftCertificateOptional = giftCertificateDao.findById(id);
        if (giftCertificateOptional.isPresent()) {
            GiftCertificate giftCertificate = giftCertificateOptional.get();
            GiftCertificate updatedGiftCertificate = giftCertificateMapper.mapDtoToEntity(giftCertificateDto);
            giftCertificate = updateGiftCertificateFields(giftCertificate, updatedGiftCertificate);
            giftCertificateDao.update(giftCertificate);
            return giftCertificateMapper.mapEntityToDto(giftCertificate);
        } else {
            throw new GiftCertificateNotFoundException();
        }
    }

    private GiftCertificate updateGiftCertificateFields(GiftCertificate giftCertificate, GiftCertificate updatedGiftCertificate){
        if(updatedGiftCertificate.getName() != null){
            giftCertificate.setName(updatedGiftCertificate.getName());
        }
        if(updatedGiftCertificate.getDescription() != null){
            giftCertificate.setDescription(updatedGiftCertificate.getDescription());
        }
        if(updatedGiftCertificate.getCreateDate() != null){
            giftCertificate.setCreateDate(updatedGiftCertificate.getCreateDate());
        }
        if(updatedGiftCertificate.getLastUpdateDate() != null){
            giftCertificate.setLastUpdateDate(updatedGiftCertificate.getLastUpdateDate());
        }
        if(updatedGiftCertificate.getPrice() != null){
            giftCertificate.setPrice(updatedGiftCertificate.getPrice());
        }
        if(updatedGiftCertificate.getDuration() != 0){
            giftCertificate.setDuration(updatedGiftCertificate.getDuration());
        }
        if(updatedGiftCertificate.getTags() != null){
            giftCertificate.setTags(updatedGiftCertificate.getTags());
        }
        return giftCertificate;
    }

    @Override
    public BigInteger deleteById(BigInteger id) {
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
        GiftCertificate savedGiftCertificate = giftCertificateDao.save(giftCertificate);
        return giftCertificateMapper.mapEntityToDto(savedGiftCertificate);
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



    @Override
    public List<GiftCertificateDto> searchByValue(SearchParameter searchParameter, String value) {
        switch (searchParameter) {
            case id:
            case name:
            case description: {
            }
            case tag: {
            }
            default: {
                throw new InvalidSearchParametersException();
            }
        }
    }

    @Override
    public List<GiftCertificateDto> sortByParameter(SortParameter sortParameter, SortType sortType) {
        return null;
    }

    private GiftCertificate setTags(GiftCertificate giftCertificate) {
        List<Tag> tags = tagDao.findByGiftCertificateId(giftCertificate.getId());
        giftCertificate.setTags(tags);
        return giftCertificate;
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

}
