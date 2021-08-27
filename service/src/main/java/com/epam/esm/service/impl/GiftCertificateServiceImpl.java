package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.GiftCertificateTagDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.GiftCertificateTag;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.utils.SearchParameter;
import com.epam.esm.service.utils.SortParameter;
import com.epam.esm.service.utils.SortType;
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
    public Optional<GiftCertificate> findById(BigInteger id) {
        return giftCertificateDao.findById(id);
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
        if (CollectionUtils.isNotEmpty(giftCertificate.getTags())) {
            saveNewTags(giftCertificate);
        }
        GiftCertificate savedGiftCertificate = giftCertificateDao.save(giftCertificate);
        if (CollectionUtils.isNotEmpty(savedGiftCertificate.getTags())) {
            bindCertificateWithTags(savedGiftCertificate);
        }
    }

    public void saveNewTags(GiftCertificate giftCertificate) {
        giftCertificate.getTags().forEach(tag -> {
            String tagName = tag.getName();
            if (tagDao.findByName(tagName).isPresent()) {
                tagDao.save(tag);
            }
        });
    }

    public void bindCertificateWithTags(GiftCertificate giftCertificate) {
        giftCertificate.getTags().forEach(tag -> {
            Optional<Tag> tagOptional = tagDao.findByName(tag.getName());
            if (tagOptional.isPresent()) {
                GiftCertificateTag giftCertificateTag = new GiftCertificateTag(giftCertificate.getId(), tag.getId());
                giftCertificateTagDao.save(giftCertificateTag);
            }
        });
    }

    @Override
    public List<GiftCertificate> searchByValue(SearchParameter searchParameter, String value){
        switch (searchParameter){
            case id: case name: case description: {
                return giftCertificateDao.searchByColumn(searchParameter.value,value);
            }
            case tag:{
                return giftCertificateDao.searchByTagName(value);
            }
        }
        return null;
    }

    @Override
    public List<GiftCertificate> sortByParameter(SortParameter sortParameter, SortType sortType) {
        return giftCertificateDao.findAllWithOrder(sortParameter.value,sortType.value);
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
