package com.epam.esm.dao.impl;

import com.epam.esm.config.TestDataSourceConfiguration;
import com.epam.esm.entity.QueryParameters;
import com.epam.esm.entity.Tag;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
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
@PropertySource("classpath:db_hsqldb.properties")
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

    private static final QueryParameters QUERY_PARAMETERS = new QueryParameters(1,10,null,null,null,null);

    @Value("${dataSource.schemaLocation}")
    private String SCHEMA_LOCATION;
    @Value("${dataSource.initScriptLocation}")
    private String INIT_SCRIPT_LOCATION;
    @Value("${dataSource.dbDropScript}")
    private String DROP_DB_SCRIPT;
    @Autowired
    private TagDaoImpl dao;

    @Autowired
    private BasicDataSource dataSource;

    @Before
    public void createDb() {
        Resource schemaResource = new ClassPathResource(SCHEMA_LOCATION);
        Resource initScriptResource = new ClassPathResource(INIT_SCRIPT_LOCATION);
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(schemaResource, initScriptResource);
        databasePopulator.execute(dataSource);
    }

    @After
    public void dropDb() {
        Resource schemaResource = new ClassPathResource(DROP_DB_SCRIPT);
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(schemaResource);
        databasePopulator.execute(dataSource);
    }

    @Test
    public void testFindAll() {
        List<Tag> actual = dao.findByParameters(QUERY_PARAMETERS);
        assertEquals(findAllExpected, actual);
    }

    @Test
    public void testFindById() {
        Optional<Tag> actual = dao.findById(ID_FIRST_TAG);
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
