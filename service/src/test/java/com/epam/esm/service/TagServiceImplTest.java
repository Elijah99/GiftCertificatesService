package com.epam.esm.service;

import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.exception.TagNotFoundException;
import com.epam.esm.mapper.impl.RequestParametersMapper;
import com.epam.esm.mapper.impl.TagMapper;
import com.epam.esm.service.impl.TagServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.epam.esm.ServiceTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TagServiceImplTest {

    @Mock
    private TagDaoImpl tagDaoMock;
    @Mock
    private TagMapper tagMapperMock;
    @Mock
    private RequestParametersMapper requestParametersMapper;
    @InjectMocks
    private TagServiceImpl tagService;

    @Test
    public void testFindAllShouldReturnListTags() {
        when(tagDaoMock.findByParameters(DEFAULT_QUERY_PARAMETERS)).thenReturn(ALL_TAGS);
        when(requestParametersMapper.mapDtoToEntity(DEFAULT_REQUEST_PARAMETERS)).thenReturn(DEFAULT_QUERY_PARAMETERS);
        when(tagMapperMock.mapListEntityToListDto(ALL_TAGS)).thenReturn(ALL_TAGS_DTO);

        assertEquals(tagService.findAll(DEFAULT_REQUEST_PARAMETERS), ALL_TAGS_DTO);

        verify(tagDaoMock).findByParameters(DEFAULT_QUERY_PARAMETERS);
        verify(requestParametersMapper).mapDtoToEntity(DEFAULT_REQUEST_PARAMETERS);
        verify(tagMapperMock).mapListEntityToListDto(ALL_TAGS);

        verifyNoMoreInteractions(tagDaoMock, requestParametersMapper, tagMapperMock);
    }

    @Test
    public void testFindByIdShouldReturnTag() {
        when(tagDaoMock.findById(any())).thenReturn(FIRST_TAG_OPTIONAL);
        when(tagMapperMock.mapEntityToDto(FIRST_TAG)).thenReturn(FIRST_TAG_DTO);

        assertEquals(tagService.findById(any()), FIRST_TAG_DTO);

        verify(tagDaoMock).findById(any());
        verifyNoMoreInteractions(tagDaoMock, tagMapperMock);
    }

    @Test
    public void testSaveShouldReturnSavedTagWithGeneratedId() {
        when(tagDaoMock.save(TAG_FOR_SAVE)).thenReturn(TAG_SAVED);
        when(tagMapperMock.mapDtoToEntity(TAG_FOR_SAVE_DTO)).thenReturn(TAG_FOR_SAVE);
        when(tagMapperMock.mapEntityToDto(TAG_SAVED)).thenReturn(TAG_SAVED_DTO);

        assertEquals(tagService.save(TAG_FOR_SAVE_DTO), TAG_SAVED_DTO);

        verify(tagDaoMock).save(TAG_FOR_SAVE);
        verify(tagMapperMock).mapDtoToEntity(TAG_FOR_SAVE_DTO);
        verify(tagMapperMock).mapEntityToDto(TAG_SAVED);
        verifyNoMoreInteractions(tagDaoMock, tagMapperMock);
    }

    @Test
    public void testDeleteByIdShouldReturnIdDeletedTag() {
        when(tagDaoMock.findById(FIRST_TAG.getId())).thenReturn(FIRST_TAG_OPTIONAL);

        tagService.deleteById(FIRST_TAG.getId());

        verify(tagDaoMock).findById(FIRST_TAG.getId());
        verify(tagDaoMock).deleteById(FIRST_TAG.getId());
        verifyNoMoreInteractions(tagDaoMock);
    }

    @Test
    public void testDeleteByIdShouldThrowNotFoundException() {
        when(tagDaoMock.findById(FIRST_TAG.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(TagNotFoundException.class, () -> {
            assertEquals(FIRST_TAG.getId(), tagService.deleteById(FIRST_TAG.getId()));
        });

        verify(tagDaoMock).findById(FIRST_TAG.getId());
        verifyNoMoreInteractions(tagDaoMock);
    }
}
