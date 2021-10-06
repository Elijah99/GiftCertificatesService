package com.epam.esm.dao.impl;

import com.epam.esm.DaoTestData;
import com.epam.esm.TestApplication;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.specification.SpecificationBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TestApplication.class)
@ComponentScan("com.epam.esm")
@EntityScan({"com.epam.esm.entity", "com.epam.esm.entity.audit"})
@ContextConfiguration(classes = {SpecificationBuilder.class})
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class GiftCertificateDaoImplTest {

    private static final BigInteger GIFT_CERTIFICATE_ID_INVALID = new BigInteger("-1");
    private static final BigInteger ID_TO_DELETE = new BigInteger("4");

    private static final LocalDateTime DEFAULT_DATE = DaoTestData.DEFAULT_DATE;

    private static final GiftCertificate FIRST_GIFT_CERTIFICATE_UPDATE_FIELDS = new GiftCertificate(new BigInteger("1"), "updated name",
            "updated description", new BigDecimal("11"), DEFAULT_DATE, DEFAULT_DATE, 360);
    private static final GiftCertificate FIRST_GIFT_CERTIFICATE_UPDATED = new GiftCertificate(new BigInteger("1"), "updated name",
            "updated description", new BigDecimal("11"), DEFAULT_DATE, DEFAULT_DATE, 360);
    private static final GiftCertificate GIFT_CERTIFICATE_TO_SAVE = new GiftCertificate("save",
            "saving description", new BigDecimal("22"), DEFAULT_DATE, DEFAULT_DATE, 120);
    private static final GiftCertificate GIFT_CERTIFICATE_SAVED = new GiftCertificate(new BigInteger("5"), "save",
            "saving description", new BigDecimal("22"), DEFAULT_DATE, DEFAULT_DATE, 120);

    private static final String SEARCH_COLUMN_NAME = "description";
    private static final String SEARCH_VALUE = "desc";
    private static final String SORT_COLUMN_NAME = "name";
    private static final String SORT_TYPE = "asc";

    private static final long COUNT_EXPECTED = 4;


    @Autowired
    private GiftCertificateDao dao;

    @Test
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testFindAll() {
        List<GiftCertificate> actual = dao.findByParameters(DaoTestData.DEFAULT_QUERY_PARAMETERS);
        assertEquals(DaoTestData.ALL_GIFT_CERTIFICATES, actual);
    }

    @Test
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testFindByIdShouldReturnGiftCertificate() {
        Optional<GiftCertificate> actual = dao.findById(DaoTestData.THIRD_GIFT_CERTIFICATE.getId());
        assertEquals(DaoTestData.THIRD_GIFT_CERTIFICATE_OPTIONAL, actual);
    }

    @Test
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testFindByIdShouldReturnEmptyOptional() {
        Optional<GiftCertificate> actual = dao.findById(GIFT_CERTIFICATE_ID_INVALID);
        assertEquals(Optional.empty(), actual);
    }

    @Test
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testUpdate() {
        try (MockedStatic<LocalDateTime> mock = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
            when(LocalDateTime.now()).thenReturn(DEFAULT_DATE);
            GiftCertificate actual = dao.update(FIRST_GIFT_CERTIFICATE_UPDATE_FIELDS);
            assertEquals(FIRST_GIFT_CERTIFICATE_UPDATED, actual);
        }
    }


    @Test
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testSave() {
        GiftCertificate actual = dao.save(GIFT_CERTIFICATE_TO_SAVE);
        assertEquals(GIFT_CERTIFICATE_SAVED, actual);
    }

    @Test
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testDeleteById() {
        BigInteger actual = dao.deleteById(ID_TO_DELETE);
        assertEquals(ID_TO_DELETE, actual);
    }

    @Test
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testCountShouldReturnNumberOfRows() {
        long actual = dao.count();
        assertEquals(COUNT_EXPECTED, actual);
    }

}
