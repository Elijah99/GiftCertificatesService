package com.epam.esm.service.impl;

import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import com.epam.esm.dto.RequestParameters;
import com.epam.esm.exception.TagNotFoundException;
import com.epam.esm.mapper.impl.RequestParametersMapper;
import com.epam.esm.mapper.impl.TagMapper;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TagServiceImpl implements TagService {

    private TagDaoImpl dao;
    private TagMapper tagMapper;
    private RequestParametersMapper requestParametersMapper;

    public TagServiceImpl() {
    }

    @Override
    public List<TagDto> findAll(RequestParameters parameters) {
        List<Tag> tags = dao.findByParameters(requestParametersMapper.mapDtoToEntity(parameters));
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

    @Override
    public TagDto getMostWidelyUsedTagOfAUserWithTheHighestCostOfAllOrders() {
        Tag tag = dao.findMostUsedTag();
        return tagMapper.mapEntityToDto(tag);
    }

    @Autowired
    public void setTagMapper(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Autowired
    public void setDao(TagDaoImpl dao) {
        this.dao = dao;
    }

    @Autowired
    public void setRequestParametersMapper(RequestParametersMapper requestParametersMapper) {
        this.requestParametersMapper = requestParametersMapper;
    }
}
