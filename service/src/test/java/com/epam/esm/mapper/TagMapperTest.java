package com.epam.esm.mapper;

import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import com.epam.esm.mapper.impl.TagMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.epam.esm.ServiceTestData.ALL_TAGS;
import static com.epam.esm.ServiceTestData.ALL_TAGS_DTO;
import static com.epam.esm.ServiceTestData.FIRST_TAG;
import static com.epam.esm.ServiceTestData.FIRST_TAG_DTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class TagMapperTest {

    private final TagMapper tagMapper = new TagMapper();

    @Test
    public void testMapEntityToDto() {
        TagDto actual = tagMapper.mapEntityToDto(FIRST_TAG);

        assertEquals(FIRST_TAG_DTO, actual);
    }

    @Test
    public void testMapDtoToEntity() {
        Tag actual = tagMapper.mapDtoToEntity(FIRST_TAG_DTO);

        assertEquals(FIRST_TAG, actual);
    }

    @Test
    public void testMapListEntityToListDto() {
        List<TagDto> actual = tagMapper.mapListEntityToListDto(ALL_TAGS);

        assertEquals(ALL_TAGS_DTO, actual);
    }

    @Test
    public void testMapListDtoToListEntity() {
        List<Tag> actual = tagMapper.mapListDtoToListEntity(ALL_TAGS_DTO);

        assertEquals(ALL_TAGS, actual);
    }
}
