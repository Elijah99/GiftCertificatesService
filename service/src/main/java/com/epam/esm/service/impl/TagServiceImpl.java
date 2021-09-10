package com.epam.esm.service.impl;

import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.GiftCertificateNotFoundException;
import com.epam.esm.exception.TagNotFoundException;
import com.epam.esm.mapper.impl.TagMapper;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private TagDaoImpl dao;
    private TagMapper tagMapper;

    public TagServiceImpl() {
    }

    @Override
    public List<TagDto> findAll() {
        List<Tag> tags = dao.findAll();
        return tagMapper.mapListEntityToListDto(tags);
    }

    @Override
    public TagDto findById(BigInteger id) {
        Optional<Tag> tagOptional = dao.findById(id);
        if (tagOptional.isPresent()) {
            Tag tag = tagOptional.get();
            return tagMapper.mapEntityToDto(tag);
        } else {
            throw new TagNotFoundException();
        }
    }

    @Override
    public BigInteger deleteById(BigInteger id) {
        Optional<Tag> tagOptional = dao.findById(id);
        if (tagOptional.isPresent()) {
            return dao.deleteById(id);
        } else {
            throw new TagNotFoundException();
        }

    }

    @Override
    public TagDto save(TagDto tagDto) {
        Tag tag = tagMapper.mapDtoToEntity(tagDto);
        Tag savedTag = dao.save(tag);
        return tagMapper.mapEntityToDto(savedTag);
    }

    @Autowired
    public void setTagMapper(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Autowired
    public void setDao(TagDaoImpl dao) {
        this.dao = dao;
    }
}
