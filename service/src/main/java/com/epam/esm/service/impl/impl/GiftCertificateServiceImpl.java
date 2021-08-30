package com.epam.esm.service.impl.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.GiftCertificateTagDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.GiftCertificateTag;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.impl.GiftCertificateService;
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

    private GiftCertificateDao giftCertificateDao;
    private TagDao tagDao;
    private GiftCertificateTagDao giftCertificateTagDao;

    public GiftCertificateServiceImpl() {
    }

    @Override
    public List<GiftCertificate> findAll() {
        return giftCertificateDao.findAll();
    }

    @Override
    public GiftCertificate findById(BigInteger id) {
        Optional<GiftCertificate> giftCertificateOptional = giftCertificateDao.findById(id);
        if (giftCertificateOptional.isPresent()) {
            return giftCertificateOptional.get();
        } else {
            throw new GiftCertificateNotFoundException();
        }
    }

    @Override
    public void update(GiftCertificate giftCertificate, BigInteger id) {
        giftCertificate.setId(id);
        giftCertificateDao.update(giftCertificate);
    }

    @Override
    public void deleteById(BigInteger id) {
        giftCertificateDao.deleteById(id);
    }

    @Transactional
    @Override
    public void save(GiftCertificate giftCertificate) {
        if (!giftCertificate.getTags().isEmpty()) {
            saveNewTags(giftCertificate);
        }
        GiftCertificate savedGiftCertificate = giftCertificateDao.save(giftCertificate);
        if (CollectionUtils.isNotEmpty(savedGiftCertificate.getTags())) {
            bindCertificateWithTags(savedGiftCertificate);
        }
    }

    public void saveNewTags(GiftCertificate giftCertificate) {
        List<Tag> allTags = tagDao.findAll();
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
    public List<GiftCertificate> searchByValue(SearchParameter searchParameter, String value) {
        switch (searchParameter) {
            case id:
            case name:
            case description: {
                return giftCertificateDao.searchByColumn(searchParameter.value, value);
            }
            case tag: {
                return giftCertificateDao.searchByTagName(value);
            }
            default: {
                throw  new InvalidSearchParametersException();
            }
        }
    }

    @Override
    public List<GiftCertificate> sortByParameter(SortParameter sortParameter, SortType sortType) {
        return giftCertificateDao.findAllWithOrder(sortParameter.value, sortType.value);
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
