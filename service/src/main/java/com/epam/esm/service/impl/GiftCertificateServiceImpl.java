package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.enums.RequestParameters;
import com.epam.esm.exception.GiftCertificateNotFoundException;
import com.epam.esm.mapper.impl.GiftCertificateMapper;
import com.epam.esm.mapper.impl.RequestParametersMapper;
import com.epam.esm.mapper.impl.TagMapper;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private GiftCertificateMapper giftCertificateMapper;
    private TagMapper tagMapper;
    private RequestParametersMapper requestParametersMapper;
    private GiftCertificateDao giftCertificateDao;
    private TagDao tagDao;

    public GiftCertificateServiceImpl() {
    }

    @Override
    public List<GiftCertificateDto> findAll(RequestParameters parameters) {
        List<GiftCertificate> giftCertificates = giftCertificateDao.findByParameters(requestParametersMapper.mapDtoToEntity(parameters));
        return giftCertificateMapper.mapListEntityToListDto(giftCertificates);
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
            giftCertificate.setLastUpdateDate(LocalDateTime.now());
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
        if(updatedGiftCertificate.getGiftCertificateTags() != null){
            giftCertificate.setGiftCertificateTags(updatedGiftCertificate.getGiftCertificateTags());
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

        giftCertificate.setCreateDate(LocalDateTime.now());
        giftCertificate.setLastUpdateDate(LocalDateTime.now());

        GiftCertificate savedGiftCertificate = giftCertificateDao.save(giftCertificate);
        return giftCertificateMapper.mapEntityToDto(savedGiftCertificate);
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

    @Autowired
    public void setTagDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }

}
