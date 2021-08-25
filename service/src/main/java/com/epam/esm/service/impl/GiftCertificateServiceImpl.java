package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.GiftCertificateTagDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.GiftCertificateTag;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    GiftCertificateDao giftCertificateDao;
    TagDao tagDao;
    GiftCertificateTagDao giftCertificateTagDao;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDao, TagDao tagDao, GiftCertificateTagDao giftCertificateTagDao) {
        this.giftCertificateDao = giftCertificateDao;
        this.tagDao = tagDao;
        this.giftCertificateTagDao = giftCertificateTagDao;
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
    public void update(GiftCertificate giftCertificate) {

    }

    @Override
    public void deleteById(BigInteger id) {
        giftCertificateDao.deleteById(id);
    }

    @Override
    public void save(GiftCertificate giftCertificate) {
        if (CollectionUtils.isNotEmpty(giftCertificate.getTags())) {
            saveNewTags(giftCertificate);
        }
        GiftCertificate savedGiftCertificate = giftCertificateDao.save(giftCertificate);
        if (CollectionUtils.isNotEmpty(giftCertificate.getTags())) {
            bindCertificateWithTags(giftCertificate);
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

}
