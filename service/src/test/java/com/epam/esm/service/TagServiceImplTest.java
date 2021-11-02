package com.epam.esm.service;

import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.exception.TagNotFoundException;
import com.epam.esm.mapper.impl.RequestParametersMapper;
import com.epam.esm.mapper.impl.TagMapper;
import com.epam.esm.service.impl.TagServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static com.epam.esm.ServiceTestData.ALL_TAGS;
import static com.epam.esm.ServiceTestData.ALL_TAGS_DTO;
import static com.epam.esm.ServiceTestData.DEFAULT_QUERY_PARAMETERS;
import static com.epam.esm.ServiceTestData.DEFAULT_REQUEST_PARAMETERS;
import static com.epam.esm.ServiceTestData.FIRST_TAG;
import static com.epam.esm.ServiceTestData.FIRST_TAG_DTO;
import static com.epam.esm.ServiceTestData.FIRST_TAG_OPTIONAL;
import static com.epam.esm.ServiceTestData.REQUEST_PARAMETERS_WITH_PAGE_SIZE_1;
import static com.epam.esm.ServiceTestData.REQUEST_PARAMETERS_WITH_PAGE_SIZE_100;
import static com.epam.esm.ServiceTestData.REQUEST_PARAMETERS_WITH_PAGE_SIZE_9;
import static com.epam.esm.ServiceTestData.TAG_FOR_SAVE;
import static com.epam.esm.ServiceTestData.TAG_FOR_SAVE_DTO;
import static com.epam.esm.ServiceTestData.TAG_SAVED;
import static com.epam.esm.ServiceTestData.TAG_SAVED_DTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
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

    @BeforeEach
    public void setUpMocks() {
        MockitoAnnotations.openMocks(this);
    }

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

    @Test
    public void testCountPagesWhenPageSizeIsMultipleOfNumberRecords() {
        when(tagDaoMock.count()).thenReturn(10L);
        long expected = 10;

        assertEquals(expected, tagService.countPages(REQUEST_PARAMETERS_WITH_PAGE_SIZE_1));
    }

    @Test
    public void testCountPagesWhenPageSizeNotMultipleOfNumberRecords() {
        when(tagDaoMock.count()).thenReturn(10L);
        long expected = 2;

        assertEquals(expected, tagService.countPages(REQUEST_PARAMETERS_WITH_PAGE_SIZE_9));
    }

    @Test
    public void testCountPagesWhenPageSizeMoreThanNumberRecords() {
        when(tagDaoMock.count()).thenReturn(10L);
        long expected = 1;

        assertEquals(expected, tagService.countPages(REQUEST_PARAMETERS_WITH_PAGE_SIZE_100));
    }
}
