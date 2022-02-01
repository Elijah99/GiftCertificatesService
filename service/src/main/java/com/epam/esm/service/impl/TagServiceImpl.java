package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.RequestParameters;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.TagNotFoundException;
import com.epam.esm.mapper.impl.RequestParametersMapper;
import com.epam.esm.mapper.impl.TagMapper;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TagServiceImpl implements TagService {

    private TagDao dao;
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
    public TagDto findById(Long id) {
        Optional<Tag> tagOptional = dao.findById(id);
        if (tagOptional.isPresent()) {
            Tag tag = tagOptional.get();
            return tagMapper.mapEntityToDto(tag);
        } else {
            throw new TagNotFoundException();
        }
    }

    @Override
    public Long deleteById(Long id) {
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
    public TagDto getMostWidelyUsedTagOfAUserWithTheHighestCostOfAllOrders(Long idUser) {
        Tag tag = dao.findMostUsedTag(idUser);
        return tagMapper.mapEntityToDto(tag);
    }

    @Override
    public long countPages(RequestParameters requestParameters) {
        long pageSize = requestParameters.getPageSize();
        long elementsAmount = dao.count();
        return elementsAmount % pageSize == 0
                ? elementsAmount / pageSize
                : elementsAmount / pageSize + 1;
    }

    @Override
    public long count() {
       return dao.count();
    }

    @Autowired
    public void setTagMapper(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Autowired
    public void setDao(TagDao dao) {
        this.dao = dao;
    }

    @Autowired
    public void setRequestParametersMapper(RequestParametersMapper requestParametersMapper) {
        this.requestParametersMapper = requestParametersMapper;
    }
}
