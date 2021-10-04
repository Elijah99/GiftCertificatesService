package com.epam.esm.service;

import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.QueryParameters;
import com.epam.esm.entity.Tag;
import com.epam.esm.dto.RequestParameters;
import com.epam.esm.exception.TagNotFoundException;
import com.epam.esm.mapper.impl.TagMapper;
import com.epam.esm.service.impl.TagServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TagServiceImplTest {

    private static final List<Tag> TAG_LIST = Arrays.asList(new Tag(new BigInteger("1"), "1st name"), new Tag(new BigInteger("2"), "2st name"));
    private static final List<TagDto> TAG_DTO_LIST = Arrays.asList(new TagDto(new BigInteger("1"), "1st name"), new TagDto(new BigInteger("2"), "2st name"));
    private static final Tag TAG = new Tag("3rd name");
    private static final TagDto TAG_DTO = new TagDto("3rd name");
    private static final Tag TAG_WITH_ID = new Tag(new BigInteger("3"), "3rd name");
    private static final TagDto TAG_DTO_WITH_ID = new TagDto(new BigInteger("3"), "3rd name");
    private static final Optional<Tag> TAG_OPTIONAL = Optional.of(TAG);
    private static final BigInteger TAG_ID = new BigInteger("1");

    private static final QueryParameters QUERY_PARAMETERS = new QueryParameters(1,10,null,null,null,null);
    private static final RequestParameters REQUEST_PARAMETERS = new RequestParameters(1,10,null,null,null,null);


    @Mock
    private TagDaoImpl tagDaoMock;
    @Mock
    private TagMapper tagMapperMock;
    @InjectMocks
    private TagServiceImpl tagService;

    @Test
    public void testFindAllShouldReturnListTags() {
        when(tagDaoMock.findByParameters(QUERY_PARAMETERS)).thenReturn(TAG_LIST);
        when(tagMapperMock.mapListEntityToListDto(TAG_LIST)).thenReturn(TAG_DTO_LIST);

        assertEquals(tagService.findAll(REQUEST_PARAMETERS), TAG_DTO_LIST);

        verify(tagDaoMock).findByParameters(QUERY_PARAMETERS);
        verifyNoMoreInteractions(tagDaoMock);
    }

    @Test
    public void testFindByIdShouldReturnTag() {
        when(tagDaoMock.findById(any())).thenReturn(TAG_OPTIONAL);
        when(tagMapperMock.mapEntityToDto(TAG)).thenReturn(TAG_DTO);

        assertEquals(tagService.findById(any()), TAG_DTO);

        verify(tagDaoMock).findById(any());
        verifyNoMoreInteractions(tagDaoMock);
    }

    @Test
    public void testSaveShouldReturnSavedTagWithGeneratedId() {
        when(tagDaoMock.save(TAG)).thenReturn(TAG_WITH_ID);
        when(tagMapperMock.mapDtoToEntity(TAG_DTO)).thenReturn(TAG);
        when(tagMapperMock.mapEntityToDto(TAG_WITH_ID)).thenReturn(TAG_DTO_WITH_ID);

        assertEquals(tagService.save(TAG_DTO), TAG_DTO_WITH_ID);

        verify(tagDaoMock).save(TAG);
        verifyNoMoreInteractions(tagDaoMock);
    }

    @Test
    public void testDeleteByIdShouldReturnIdDeletedTag() {
        when(tagDaoMock.findById(TAG_ID)).thenReturn(TAG_OPTIONAL);

        tagService.deleteById(TAG_ID);

        verify(tagDaoMock).deleteById(TAG_ID);
        verifyNoMoreInteractions(tagDaoMock);
    }

    @Test
    public void testDeleteByIdShouldThrowNotFoundException() {
        when(tagDaoMock.findById(TAG_ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(TagNotFoundException.class, () -> {
            assertEquals(TAG_ID, tagService.deleteById(TAG_ID));
        });

        verify(tagDaoMock).findById(TAG_ID);
        verifyNoMoreInteractions(tagDaoMock);
    }
}
