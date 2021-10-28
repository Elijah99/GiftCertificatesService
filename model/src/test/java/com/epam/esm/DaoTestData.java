package com.epam.esm;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.QueryParameters;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.User;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DaoTestData {
    // QueryParameters
    public static final QueryParameters DEFAULT_QUERY_PARAMETERS = new QueryParameters(1, 10, null, null, null, null);

    // Tags
    public static final Tag FIRST_TAG = new Tag(1L, "1 name");
    public static final Tag SECOND_TAG = new Tag(2L, "2 name");
    public static final Tag THIRD_TAG = new Tag(3L, "3 name");
    public static final Tag FOURTH_TAG = new Tag(4L, "4 name");

    public static final Tag TAG_FOR_SAVE = new Tag("for save");
    public static final Tag TAG_SAVED = new Tag(5L, "for save");

    public static final List<Tag> ALL_TAGS = Arrays.asList(FIRST_TAG,
            SECOND_TAG,
            THIRD_TAG,
            FOURTH_TAG);

    // Optional Tag
    public static final Optional<Tag> FIRST_TAG_OPTIONAL = Optional.of(FIRST_TAG);
    public static final Optional<Tag> SECOND_TAG_OPTIONAL = Optional.of(SECOND_TAG);
    public static final Optional<Tag> THIRD_TAG_OPTIONAL = Optional.of(THIRD_TAG);
    public static final Optional<Tag> FOURTH_TAG_OPTIONAL = Optional.of(FOURTH_TAG);

    // LocalDateTime dates
    public static final LocalDateTime DEFAULT_DATE = Timestamp.valueOf("2020-12-12 21:34:10.769000").toLocalDateTime();

    // GiftCertificates
    public static final GiftCertificate FIRST_GIFT_CERTIFICATE = new GiftCertificate(1L, "name",
            "description", new BigDecimal("11"), DEFAULT_DATE, DEFAULT_DATE, 360,
            Arrays.asList(FIRST_TAG, SECOND_TAG));
    public static final GiftCertificate SECOND_GIFT_CERTIFICATE = new GiftCertificate(2L, "2nd name",
            "2nd description", new BigDecimal("22"), DEFAULT_DATE, DEFAULT_DATE, 120,
            Arrays.asList(FOURTH_TAG, THIRD_TAG));
    public static final GiftCertificate THIRD_GIFT_CERTIFICATE = new GiftCertificate(3L, "3rd name",
            "desc", new BigDecimal("22"), DEFAULT_DATE, DEFAULT_DATE, 250,
            Arrays.asList(SECOND_TAG));
    public static final GiftCertificate FOURTH_GIFT_CERTIFICATE = new GiftCertificate(4L, "4th name",
            "asc", new BigDecimal("22"), DEFAULT_DATE, DEFAULT_DATE, 20,
            Arrays.asList(FIRST_TAG));

    public static final GiftCertificate GIFT_CERTIFICATE_FOR_SAVE = new GiftCertificate(null, "for save",
            "desc for save", new BigDecimal("22"), DEFAULT_DATE, DEFAULT_DATE, 250, null);
    public static final GiftCertificate GIFT_CERTIFICATE_FOR_UPDATE = new GiftCertificate(null, "updated",
            "updated", new BigDecimal("1111"), DEFAULT_DATE, DEFAULT_DATE, 111,
            Arrays.asList(FIRST_TAG));
    public static final GiftCertificate GIFT_CERTIFICATE_SAVED = new GiftCertificate(5L, "for save",
            "desc for save", new BigDecimal("22"), DEFAULT_DATE, DEFAULT_DATE, 250, null);

    public static final List<GiftCertificate> ALL_GIFT_CERTIFICATES = Arrays.asList(FIRST_GIFT_CERTIFICATE,
            SECOND_GIFT_CERTIFICATE,
            THIRD_GIFT_CERTIFICATE,
            FOURTH_GIFT_CERTIFICATE);

    // Optional GiftCertificates
    public static final Optional<GiftCertificate> FIRST_GIFT_CERTIFICATE_OPTIONAL = Optional.of(FIRST_GIFT_CERTIFICATE);
    public static final Optional<GiftCertificate> SECOND_GIFT_CERTIFICATE_OPTIONAL = Optional.of(SECOND_GIFT_CERTIFICATE);
    public static final Optional<GiftCertificate> THIRD_GIFT_CERTIFICATE_OPTIONAL = Optional.of(THIRD_GIFT_CERTIFICATE);
    public static final Optional<GiftCertificate> FOURTH_GIFT_CERTIFICATE_OPTIONAL = Optional.of(FOURTH_GIFT_CERTIFICATE);

    // Orders
    public static final Order FIRST_ORDER = new Order(1L, new BigDecimal(33),
            DEFAULT_DATE, new User(1L, "1st name", null),
            Arrays.asList(FIRST_GIFT_CERTIFICATE, SECOND_GIFT_CERTIFICATE));
    public static final Order SECOND_ORDER = new Order(2L, new BigDecimal(44),
            DEFAULT_DATE, new User(1L, "1st name", null),
            Arrays.asList(THIRD_GIFT_CERTIFICATE, FOURTH_GIFT_CERTIFICATE));
    public static final Order THIRD_ORDER = new Order(3L, new BigDecimal(77),
            DEFAULT_DATE, new User(2L, "2nd name", null),
            Arrays.asList(FIRST_GIFT_CERTIFICATE, SECOND_GIFT_CERTIFICATE,
                    THIRD_GIFT_CERTIFICATE, FOURTH_GIFT_CERTIFICATE));
    public static final Order FOURTH_ORDER = new Order(4L, new BigDecimal(22),
            DEFAULT_DATE, new User(3L, "3rd name", null),
            Arrays.asList(THIRD_GIFT_CERTIFICATE));
    public static final Order FIFTH_ORDER = new Order(5L, new BigDecimal(44),
            DEFAULT_DATE, new User(3L, "3rd name", null),
            Arrays.asList(SECOND_GIFT_CERTIFICATE, THIRD_GIFT_CERTIFICATE));

    public static final List<Order> ALL_ORDERS = Arrays.asList(FIRST_ORDER,
            SECOND_ORDER,
            THIRD_ORDER,
            FOURTH_ORDER,
            FIFTH_ORDER);


    // Optional Orders
    public static final Optional<Order> FIRST_ORDER_OPTIONAL = Optional.of(FIRST_ORDER);
    public static final Optional<Order> SECOND_ORDER_OPTIONAL = Optional.of(SECOND_ORDER);
    public static final Optional<Order> THIRD_ORDER_OPTIONAL = Optional.of(THIRD_ORDER);
    public static final Optional<Order> FOURTH_ORDER_OPTIONAL = Optional.of(FOURTH_ORDER);
    public static final Optional<Order> FIFTH_ORDER_OPTIONAL = Optional.of(FIFTH_ORDER);


    // Users
    public static final User FIRST_USER = new User(1L, "1st name", Arrays.asList(FIRST_ORDER, SECOND_ORDER));
    public static final User SECOND_USER = new User(2L, "2nd name", Arrays.asList(THIRD_ORDER));
    public static final User THIRD_USER = new User(3L, "3rd name", Arrays.asList(FOURTH_ORDER, FIFTH_ORDER));
    public static final User FOURTH_USER = new User(4L, "4th name", new ArrayList<>());

    public static final List<User> ALL_USERS = Arrays.asList(FIRST_USER,
            SECOND_USER,
            THIRD_USER,
            FOURTH_USER);

    // Optional Users
    public static final Optional<User> FIRST_USER_OPTIONAL = Optional.of(FIRST_USER);
    public static final Optional<User> SECOND_USER_OPTIONAL = Optional.of(SECOND_USER);
    public static final Optional<User> THIRD_USER_OPTIONAL = Optional.of(THIRD_USER);
    public static final Optional<User> FOURTH_USER_OPTIONAL = Optional.of(FOURTH_USER);

    // Order for save tests
    public static final Order ORDER_FOR_SAVE = new Order(null, new BigDecimal(33), DEFAULT_DATE, FOURTH_USER, Arrays.asList(FIRST_GIFT_CERTIFICATE, SECOND_GIFT_CERTIFICATE));
    public static final Order ORDER_SAVED = new Order(6L, ORDER_FOR_SAVE.getCost(), ORDER_FOR_SAVE.getPurchaseDate(),
            ORDER_FOR_SAVE.getUser(), ORDER_FOR_SAVE.getGiftCertificates());


}
