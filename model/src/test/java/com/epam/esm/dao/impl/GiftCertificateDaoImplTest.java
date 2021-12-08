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
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.epam.esm.DaoTestData.FIRST_GIFT_CERTIFICATE;
import static com.epam.esm.DaoTestData.FIRST_TAG;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TestApplication.class)
@ComponentScan("com.epam.esm")
@EntityScan({"com.epam.esm.entity", "com.epam.esm.entity.audit"})
@ContextConfiguration(classes = {SpecificationBuilder.class})
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class GiftCertificateDaoImplTest {

    private static final Long GIFT_CERTIFICATE_ID_INVALID = new Long("-1");
    private static final Long ID_TO_DELETE = new Long("4");

    private static final LocalDateTime DEFAULT_DATE = DaoTestData.DEFAULT_DATE;

    private static final GiftCertificate GIFT_CERTIFICATE_TO_SAVE = new GiftCertificate("save",
            "saving description", new BigDecimal("22"), DEFAULT_DATE, DEFAULT_DATE, 120);
    private static final GiftCertificate GIFT_CERTIFICATE_SAVED = new GiftCertificate(new Long("5"), "save",
            "saving description", new BigDecimal("22"), DEFAULT_DATE, DEFAULT_DATE, 120, null);

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
    @Transactional
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testUpdate() {
        try (MockedStatic<LocalDateTime> mock = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
            when(LocalDateTime.now()).thenReturn(DEFAULT_DATE);
            GiftCertificate updated = FIRST_GIFT_CERTIFICATE;
            updated.setDescription("updated description");
            updated.setName("updated name");
            updated.addTag(FIRST_TAG);

            dao.update(updated);

            GiftCertificate actual = dao.findById(updated.getId()).get();
            assertEquals(updated, actual);
        }
    }


    @Test
    @Transactional
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testSave() {
        GiftCertificate actual = dao.save(GIFT_CERTIFICATE_TO_SAVE);
        assertEquals(GIFT_CERTIFICATE_SAVED, actual);
    }

    @Test
    @Transactional
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testDeleteById() {
        Long actual = dao.deleteById(ID_TO_DELETE);
        assertEquals(ID_TO_DELETE, actual);
    }

    @Test
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testCountShouldReturnNumberOfRows() {
        long actual = dao.count();
        assertEquals(COUNT_EXPECTED, actual);
    }

}
