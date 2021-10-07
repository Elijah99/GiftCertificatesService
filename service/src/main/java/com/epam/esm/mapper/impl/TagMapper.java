package com.epam.esm.mapper.impl;

import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import com.epam.esm.mapper.DtoMapper;
import org.springframework.stereotype.Component;

@Component
public class TagMapper implements DtoMapper<Tag, TagDto> {
    @Override
    public TagDto mapEntityToDto(Tag entity) {
        TagDto tagDto = new TagDto();
        tagDto.setId(entity.getId());
        tagDto.setName(entity.getName());
        return tagDto;
    }

    @Override
    public Tag mapDtoToEntity(TagDto dto) {
        Tag tag = new Tag();
        tag.setId(dto.getId());
        tag.setName(dto.getName());
        return tag;
    }
}
