package com.epam.esm.dao.impl;

import com.epam.esm.config.TestDataSourceConfiguration;
import com.epam.esm.entity.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataSourceConfiguration.class})
public class TagDaoImplTest {

    public static final Optional<Tag> FIRST_TAG_OPTIONAL = Optional.of(new Tag(new BigInteger("1"), "1 name"));
    public static final BigInteger ID_FIRST_TAG = new BigInteger("1");
    public static final String FIRST_TAG_NAME = "1 name";

    public static final Tag TAG_TO_SAVE = new Tag("saved name");
    public static final Tag TAG_SAVED = new Tag(new BigInteger("5"), "saved name");
    public static final BigInteger ID_TO_DELETE = new BigInteger("5");


    public static final List<Tag> findAllExpected = Arrays.asList(
            new Tag(new BigInteger("1"), "1 name"),
            new Tag(new BigInteger("2"), "2 name"),
            new Tag(new BigInteger("3"), "3 name"),
            new Tag(new BigInteger("4"), "4 name"));

    @Autowired
    private TagDaoImpl dao;

    @Test
    public void testFindAll() {
        List<Tag> actual = dao.findAll();
        assertEquals(findAllExpected, actual);
    }

    @Test
    public void testFindById() {
        Optional<Tag> actual = dao.findById(ID_FIRST_TAG);
        assertEquals(FIRST_TAG_OPTIONAL, actual);
    }

    @Test
    public void testFindByName() {
        Optional<Tag> actual = dao.findByName(FIRST_TAG_NAME);
        assertEquals(FIRST_TAG_OPTIONAL, actual);
    }

    @Test
    public void testSave() {
        Tag actual = dao.save(TAG_TO_SAVE);
        assertEquals(TAG_SAVED, actual);
    }

    @Test
    public void testDeleteById() {
        BigInteger actual = dao.deleteById(ID_TO_DELETE);
        assertEquals(ID_TO_DELETE, actual);
    }

}
