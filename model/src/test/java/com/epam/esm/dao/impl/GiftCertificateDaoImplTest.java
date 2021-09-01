package com.epam.esm.dao.impl;

import com.epam.esm.config.TestDataSourceConfiguration;
import com.epam.esm.entity.GiftCertificate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataSourceConfiguration.class})
public class GiftCertificateDaoImplTest {

    public static final LocalDateTime DATE = Timestamp.valueOf("2020-12-12 21:34:10.769000").toLocalDateTime();
    private static final BigInteger GIFT_CERTIFICATE_ID = new BigInteger("2");
    private static final BigInteger GIFT_CERTIFICATE_ID_INVALID = new BigInteger("-1");
    private static final BigInteger ID_TO_DELETE = new BigInteger("5");

    private static final GiftCertificate FIRST_GIFT_CERTIFICATE = new GiftCertificate(new BigInteger("1"), "name",
            "description", new BigDecimal("11"), DATE, DATE, 360);
    private static final GiftCertificate SECOND_GIFT_CERTIFICATE = new GiftCertificate(new BigInteger("2"), "2nd name",
            "2nd description", new BigDecimal("22"), DATE, DATE, 120);

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

    @Autowired
    private GiftCertificateDaoImpl dao;

    @Test
    public void testFindAll() {
        List<GiftCertificate> actual = dao.findAll();
        assertEquals(findAllExpected, actual);
    }

    @Test
    public void testFindByIdShouldReturnGiftCertificate() {
        Optional<GiftCertificate> actual = dao.findById(GIFT_CERTIFICATE_ID);
        assertEquals(FIND_BY_ID_EXPECTED, actual);
    }

    @Test
    public void testFindAllShouldReturnEmptyOptional() {
        Optional<GiftCertificate> actual = dao.findById(GIFT_CERTIFICATE_ID_INVALID);
        assertEquals(Optional.empty(), actual);
    }

    @Test
    public void testUpdate() {
        GiftCertificate actual = dao.update(FIRST_GIFT_CERTIFICATE_UPDATE_FIELDS);
        assertEquals(FIRST_GIFT_CERTIFICATE_UPDATED, actual);
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

    @Test
    public void testSearchByColumn() {
        List<GiftCertificate> actual = dao.searchByColumn(SEARCH_COLUMN_NAME, SEARCH_VALUE);
        assertEquals(searchByColumnExpected, actual);
    }

    @Test
    public void testFindAllWithOrder() {
        List<GiftCertificate> actual = dao.findAllWithOrder(SORT_COLUMN_NAME,SORT_TYPE);
        assertEquals(sortedByName, actual);
    }

}
