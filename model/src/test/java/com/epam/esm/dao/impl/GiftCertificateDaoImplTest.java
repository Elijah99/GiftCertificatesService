package com.epam.esm.dao.impl;

import com.epam.esm.config.TestDataSourceConfiguration;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.QueryParameters;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataSourceConfiguration.class})
@PropertySource("classpath:db_hsqldb.properties")
public class GiftCertificateDaoImplTest {

    public static final LocalDateTime DATE = Timestamp.valueOf("2020-12-12 21:34:10.769000").toLocalDateTime();
    private static final BigInteger GIFT_CERTIFICATE_ID = new BigInteger("2");
    private static final BigInteger GIFT_CERTIFICATE_ID_INVALID = new BigInteger("-1");
    private static final BigInteger ID_TO_DELETE = new BigInteger("5");
    private static final GiftCertificate FIRST_GIFT_CERTIFICATE = new GiftCertificate(new BigInteger("1"), "name",
            "description", new BigDecimal("11"), DATE, DATE, 360);
    private static final GiftCertificate SECOND_GIFT_CERTIFICATE = new GiftCertificate(new BigInteger("2"), "2nd name",
            "2nd description", new BigDecimal("22"), DATE, DATE, 120);
    public static final List<GiftCertificate> findAllExpected = Arrays.asList(
            FIRST_GIFT_CERTIFICATE,
            SECOND_GIFT_CERTIFICATE,
            new GiftCertificate(new BigInteger("3"), "zzzzzz",
                    "desc", new BigDecimal("22"),
                    DATE, DATE, 250),
            new GiftCertificate(new BigInteger("4"), "aaaa",
                    "asc", new BigDecimal("22"),
                    DATE, DATE, 20));
    public static final List<GiftCertificate> searchByColumnExpected = Arrays.asList(
            FIRST_GIFT_CERTIFICATE,
            SECOND_GIFT_CERTIFICATE,
            new GiftCertificate(new BigInteger("3"), "zzzzzz",
                    "desc", new BigDecimal("22"),
                    DATE, DATE, 250));
    public static final List<GiftCertificate> sortedByName = Arrays.asList(
            SECOND_GIFT_CERTIFICATE,
            new GiftCertificate(new BigInteger("4"), "aaaa",
                    "asc", new BigDecimal("22"),
                    DATE, DATE, 20),
            FIRST_GIFT_CERTIFICATE,
            new GiftCertificate(new BigInteger("3"), "zzzzzz",
                    "desc", new BigDecimal("22"),
                    DATE, DATE, 250));
    private static final GiftCertificate FIRST_GIFT_CERTIFICATE_UPDATE_FIELDS = new GiftCertificate(new BigInteger("1"), "updated name",
            "updated description", DATE);
    private static final GiftCertificate FIRST_GIFT_CERTIFICATE_UPDATED = new GiftCertificate(new BigInteger("1"), "updated name",
            "updated description", new BigDecimal("11"), DATE, DATE, 360);
    private static final GiftCertificate GIFT_CERTIFICATE_TO_SAVE = new GiftCertificate("save",
            "saving description", new BigDecimal("22"), DATE, DATE, 120);
    private static final GiftCertificate GIFT_CERTIFICATE_SAVED = new GiftCertificate(new BigInteger("5"), "save",
            "saving description", new BigDecimal("22"), DATE, DATE, 120);
    private static final Optional<GiftCertificate> FIND_BY_ID_EXPECTED = Optional.of(SECOND_GIFT_CERTIFICATE);

    private static final String SEARCH_COLUMN_NAME = "description";
    private static final String SEARCH_VALUE = "desc";
    private static final String SORT_COLUMN_NAME = "name";
    private static final String SORT_TYPE = "asc";

    private static final QueryParameters QUERY_PARAMETERS = new QueryParameters(1,10,null,null,null,null);

    @Value("${dataSource.schemaLocation}")
    private String SCHEMA_LOCATION;
    @Value("${dataSource.initScriptLocation}")
    private String INIT_SCRIPT_LOCATION;
    @Value("${dataSource.dbDropScript}")
    private String DROP_DB_SCRIPT;
    @Autowired
    private GiftCertificateDaoImpl dao;

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
        List<GiftCertificate> actual = dao.findByParameters(QUERY_PARAMETERS);
        assertEquals(findAllExpected, actual);
    }

    @Test
    public void testFindByIdShouldReturnGiftCertificate() {
        Optional<GiftCertificate> actual = dao.findById(GIFT_CERTIFICATE_ID);
        assertEquals(FIND_BY_ID_EXPECTED, actual);
    }

    @Test
    public void testFindByIdShouldReturnEmptyOptional() {
        Optional<GiftCertificate> actual = dao.findById(GIFT_CERTIFICATE_ID_INVALID);
        assertEquals(Optional.empty(), actual);
    }

    @Test
    public void testUpdate() {
        try (MockedStatic<LocalDateTime> mock = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
            when(LocalDateTime.now()).thenReturn(DATE);
            GiftCertificate actual = dao.update(FIRST_GIFT_CERTIFICATE_UPDATE_FIELDS);
            assertEquals(FIRST_GIFT_CERTIFICATE_UPDATED, actual);
        }
    }


    @Test
    public void testSave() {
        GiftCertificate actual = dao.save(GIFT_CERTIFICATE_TO_SAVE);
        assertEquals(GIFT_CERTIFICATE_SAVED, actual);
    }

    @Test
    public void testDeleteById() {
        BigInteger actual = dao.deleteById(ID_TO_DELETE);
        assertEquals(ID_TO_DELETE, actual);
    }

}
