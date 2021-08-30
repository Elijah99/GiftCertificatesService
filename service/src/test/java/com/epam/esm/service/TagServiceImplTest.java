package com.epam.esm.service;

import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.impl.TagServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TagServiceImplTest {

    private static final List<Tag> TAG_LIST = Arrays.asList(new Tag(new BigInteger("1"), "1st name"), new Tag(new BigInteger("2"), "2st name"));
    private static final Tag TAG = new Tag( "3rd name");
    private static final Tag TAG_WITH_ID = new Tag(new BigInteger("3"), "3rd name");
    private static final Optional<Tag> TAG_OPTIONAL = Optional.of(TAG);
    private static final BigInteger TAG_ID = new BigInteger("1");

    @Mock
    private TagDaoImpl tagDao;

    @InjectMocks
    private TagServiceImpl tagService;

    @Test
    public void testFindAllShouldReturnListTags() {
        when(tagDao.findAll()).thenReturn(TAG_LIST);
        assertEquals(tagService.findAll(), TAG_LIST);
        verify(tagDao).findAll();
        verifyNoMoreInteractions(tagDao);
    }

    @Test
    public void testFindByIdShouldReturnTag() {
        when(tagDao.findById(any())).thenReturn(TAG_OPTIONAL);
        assertEquals(tagService.findById(any()), TAG);
        verify(tagDao).findById(any());
        verifyNoMoreInteractions(tagDao);
    }

    @Test
    public void testSaveShouldReturnSavedTagWithGeneratedId() {
        when(tagDao.save(TAG)).thenReturn(TAG_WITH_ID);
        assertEquals(tagService.save(TAG), TAG_WITH_ID);
        verify(tagDao).save(TAG);
        verifyNoMoreInteractions(tagDao);
    }

    @Test
    public void testDeleteShouldReturnIdDeletedTag() {
        tagService.deleteById(TAG_ID);
        verify(tagDao).deleteById(TAG_ID);
        verifyNoMoreInteractions(tagDao);
    }
}
