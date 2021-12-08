package com.epam.esm.dao.impl;

import com.epam.esm.DaoTestData;
import com.epam.esm.TestApplication;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = TestApplication.class)
@ComponentScan("com.epam.esm")
@EntityScan({"com.epam.esm.entity", "com.epam.esm.entity.audit"})
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class TagDaoImplTest {

    public static final Long ID_TAG_INVALID = 999L;
    public static final Tag TAG_TO_SAVE = new Tag("saved name");
    public static final Tag TAG_SAVED = new Tag(5L, "saved name");
    public static final Long ID_TO_DELETE = 1L;

    private static final long COUNT_EXPECTED = 4;

    @Autowired
    private TagDao dao;

    @Test
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testFindAll() {
        List<Tag> actual = dao.findByParameters(DaoTestData.DEFAULT_QUERY_PARAMETERS);
        assertEquals(DaoTestData.ALL_TAGS, actual);
    }

    @Test
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testFindByIdShouldReturnTag() {
        Optional<Tag> actual = dao.findById(DaoTestData.FIRST_TAG.getId());
        assertEquals(DaoTestData.FIRST_TAG_OPTIONAL, actual);
    }

    @Test
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testFindByIdShouldReturnOptionalEmptyWhenTagNotFound() {
        Optional<Tag> actual = dao.findById(ID_TAG_INVALID);
        assertEquals(Optional.empty(), actual);
    }

    @Test
    @Transactional
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testSave() {
        Tag actual = dao.save(TAG_TO_SAVE);
        assertEquals(TAG_SAVED, actual);
    }

    @Test
    @Transactional
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testDeleteById() {
        Long actual = dao.deleteById(ID_TO_DELETE);

        List<Tag> expected = new ArrayList<>(DaoTestData.ALL_TAGS);
        expected.remove(DaoTestData.FIRST_TAG);
        assertEquals(expected, dao.findByParameters(DaoTestData.DEFAULT_QUERY_PARAMETERS));
    }

    @Test
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testCountShouldReturnNumberOfRows() {
        long actual = dao.count();
        assertEquals(COUNT_EXPECTED, actual);
    }
}
