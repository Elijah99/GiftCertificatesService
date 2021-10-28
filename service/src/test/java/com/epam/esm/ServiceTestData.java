package com.epam.esm;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.RequestParameters;
import com.epam.esm.dto.TagDto;
import com.epam.esm.dto.UserDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceTestData extends DaoTestData {

    // RequestParameters
    public static final RequestParameters DEFAULT_REQUEST_PARAMETERS = new RequestParameters(1, 10, null, null, null, null);
    public static final RequestParameters REQUEST_PARAMETERS_WITH_PAGE_SIZE_1 = new RequestParameters(1, 1, null, null, null, null);
    public static final RequestParameters REQUEST_PARAMETERS_WITH_PAGE_SIZE_9 = new RequestParameters(1, 9, null, null, null, null);
    public static final RequestParameters REQUEST_PARAMETERS_WITH_PAGE_SIZE_100 = new RequestParameters(1, 100, null, null, null, null);

    // Dtos
    // Tag dtos
    public static final TagDto FIRST_TAG_DTO = new TagDto(FIRST_TAG.getId(), FIRST_TAG.getName());
    public static final TagDto SECOND_TAG_DTO = new TagDto(SECOND_TAG.getId(), SECOND_TAG.getName());
    public static final TagDto THIRD_TAG_DTO = new TagDto(THIRD_TAG.getId(), THIRD_TAG.getName());
    public static final TagDto FOURTH_TAG_DTO = new TagDto(FOURTH_TAG.getId(), FOURTH_TAG.getName());

    public static final TagDto TAG_FOR_SAVE_DTO = new TagDto(TAG_FOR_SAVE.getId(), TAG_FOR_SAVE.getName());
    public static final TagDto TAG_SAVED_DTO = new TagDto(TAG_SAVED.getId(), TAG_SAVED.getName());

    public static final List<TagDto> ALL_TAGS_DTO = Arrays.asList(FIRST_TAG_DTO,
            SECOND_TAG_DTO,
            THIRD_TAG_DTO,
            FOURTH_TAG_DTO);

    // GiftCertificates dtos
    public static final GiftCertificateDto FIRST_GIFT_CERTIFICATE_DTO = new GiftCertificateDto(FIRST_GIFT_CERTIFICATE.getId(),
            FIRST_GIFT_CERTIFICATE.getName(), FIRST_GIFT_CERTIFICATE.getDescription(), FIRST_GIFT_CERTIFICATE.getPrice(),
            FIRST_GIFT_CERTIFICATE.getCreateDate(), FIRST_GIFT_CERTIFICATE.getLastUpdateDate(),
            FIRST_GIFT_CERTIFICATE.getDuration(), Arrays.asList(FIRST_TAG_DTO, SECOND_TAG_DTO));
    public static final GiftCertificateDto SECOND_GIFT_CERTIFICATE_DTO = new GiftCertificateDto(SECOND_GIFT_CERTIFICATE.getId(),
            SECOND_GIFT_CERTIFICATE.getName(), SECOND_GIFT_CERTIFICATE.getDescription(), SECOND_GIFT_CERTIFICATE.getPrice(),
            SECOND_GIFT_CERTIFICATE.getCreateDate(), SECOND_GIFT_CERTIFICATE.getLastUpdateDate(),
            SECOND_GIFT_CERTIFICATE.getDuration(), Arrays.asList(FOURTH_TAG_DTO, THIRD_TAG_DTO));
    public static final GiftCertificateDto THIRD_GIFT_CERTIFICATE_DTO = new GiftCertificateDto(THIRD_GIFT_CERTIFICATE.getId(),
            THIRD_GIFT_CERTIFICATE.getName(), THIRD_GIFT_CERTIFICATE.getDescription(), THIRD_GIFT_CERTIFICATE.getPrice(),
            THIRD_GIFT_CERTIFICATE.getCreateDate(), THIRD_GIFT_CERTIFICATE.getLastUpdateDate(),
            THIRD_GIFT_CERTIFICATE.getDuration(), Arrays.asList(SECOND_TAG_DTO));
    public static final GiftCertificateDto FOURTH_GIFT_CERTIFICATE_DTO = new GiftCertificateDto(FOURTH_GIFT_CERTIFICATE.getId(),
            FOURTH_GIFT_CERTIFICATE.getName(), FOURTH_GIFT_CERTIFICATE.getDescription(), FOURTH_GIFT_CERTIFICATE.getPrice(),
            FOURTH_GIFT_CERTIFICATE.getCreateDate(), FOURTH_GIFT_CERTIFICATE.getLastUpdateDate(),
            FOURTH_GIFT_CERTIFICATE.getDuration(), Arrays.asList(FIRST_TAG_DTO));

    public static final GiftCertificateDto GIFT_CERTIFICATE_FOR_SAVE_DTO = new GiftCertificateDto(null, GIFT_CERTIFICATE_FOR_SAVE.getName(),
            GIFT_CERTIFICATE_FOR_SAVE.getDescription(), GIFT_CERTIFICATE_FOR_SAVE.getPrice(), null, null,
            GIFT_CERTIFICATE_FOR_SAVE.getDuration());
    public static final GiftCertificateDto GIFT_CERTIFICATE_SAVED_DTO = new GiftCertificateDto(GIFT_CERTIFICATE_SAVED.getId(),
            GIFT_CERTIFICATE_SAVED.getName(), GIFT_CERTIFICATE_SAVED.getDescription(),
            GIFT_CERTIFICATE_SAVED.getPrice(), GIFT_CERTIFICATE_SAVED.getCreateDate(),
            GIFT_CERTIFICATE_SAVED.getLastUpdateDate(), GIFT_CERTIFICATE_SAVED.getDuration());
    public static final GiftCertificateDto GIFT_CERTIFICATE_FOR_UPDATE_DTO = new GiftCertificateDto(GIFT_CERTIFICATE_FOR_UPDATE.getId(),
            GIFT_CERTIFICATE_FOR_UPDATE.getName(), GIFT_CERTIFICATE_FOR_UPDATE.getDescription(),
            GIFT_CERTIFICATE_FOR_UPDATE.getPrice(), GIFT_CERTIFICATE_FOR_UPDATE.getCreateDate(),
            GIFT_CERTIFICATE_FOR_UPDATE.getLastUpdateDate(), GIFT_CERTIFICATE_FOR_UPDATE.getDuration());
    public static final GiftCertificateDto GIFT_CERTIFICATE_DTO_WITH_NULL_FIELDS = new GiftCertificateDto();

    public static final List<GiftCertificateDto> ALL_GIFT_CERTIFICATES_DTO = Arrays.asList(FIRST_GIFT_CERTIFICATE_DTO,
            SECOND_GIFT_CERTIFICATE_DTO,
            THIRD_GIFT_CERTIFICATE_DTO,
            FOURTH_GIFT_CERTIFICATE_DTO);

    // Orders dtos
    public static final OrderDto FIRST_ORDER_DTO = new OrderDto(FIRST_ORDER.getId(), FIRST_ORDER.getCost(),
            FIRST_ORDER.getPurchaseDate(),
            Arrays.asList(FIRST_GIFT_CERTIFICATE_DTO, SECOND_GIFT_CERTIFICATE_DTO),
            FIRST_USER.getId());
    public static final OrderDto SECOND_ORDER_DTO = new OrderDto(SECOND_ORDER.getId(), SECOND_ORDER.getCost(),
            SECOND_ORDER.getPurchaseDate(),
            Arrays.asList(THIRD_GIFT_CERTIFICATE_DTO, FOURTH_GIFT_CERTIFICATE_DTO),
            FIRST_USER.getId());
    public static final OrderDto THIRD_ORDER_DTO = new OrderDto(THIRD_ORDER.getId(), THIRD_ORDER.getCost(),
            THIRD_ORDER.getPurchaseDate(),
            Arrays.asList(FIRST_GIFT_CERTIFICATE_DTO, SECOND_GIFT_CERTIFICATE_DTO, THIRD_GIFT_CERTIFICATE_DTO, FOURTH_GIFT_CERTIFICATE_DTO),
            SECOND_USER.getId());
    public static final OrderDto FOURTH_ORDER_DTO = new OrderDto(FOURTH_ORDER.getId(), FOURTH_ORDER.getCost(),
            FOURTH_ORDER.getPurchaseDate(),
            Arrays.asList(THIRD_GIFT_CERTIFICATE_DTO),
            THIRD_USER.getId());
    public static final OrderDto FIFTH_ORDER_DTO = new OrderDto(FIFTH_ORDER.getId(), FIFTH_ORDER.getCost(),
            FIFTH_ORDER.getPurchaseDate(),
            Arrays.asList(SECOND_GIFT_CERTIFICATE_DTO, THIRD_GIFT_CERTIFICATE_DTO),
            THIRD_USER.getId());

    public static final OrderDto ORDER_FOR_SAVE_DTO = new OrderDto(ORDER_FOR_SAVE.getId(), ORDER_FOR_SAVE.getCost(),
            ORDER_FOR_SAVE.getPurchaseDate(),
            Arrays.asList(SECOND_GIFT_CERTIFICATE_DTO, THIRD_GIFT_CERTIFICATE_DTO),
            THIRD_USER.getId());

    public static final OrderDto ORDER_SAVED_DTO = new OrderDto(ORDER_SAVED.getId(), ORDER_SAVED.getCost(),
            ORDER_SAVED.getPurchaseDate(),
            Arrays.asList(SECOND_GIFT_CERTIFICATE_DTO, THIRD_GIFT_CERTIFICATE_DTO),
            THIRD_USER.getId());

    public static final List<OrderDto> ALL_ORDERS_DTO = Arrays.asList(FIRST_ORDER_DTO,
            SECOND_ORDER_DTO,
            THIRD_ORDER_DTO,
            FOURTH_ORDER_DTO,
            FIFTH_ORDER_DTO);

    // User dtos
    public static final UserDto FIRST_USER_DTO = new UserDto(FIRST_USER.getId(), FIRST_USER.getName(),
            FIRST_USER.getLogin(), FIRST_USER.getPassword(), FIRST_USER.getRole(), Arrays.asList(FIRST_ORDER_DTO, SECOND_ORDER_DTO));
    public static final UserDto SECOND_USER_DTO = new UserDto(SECOND_USER.getId(), SECOND_USER.getName(),
            SECOND_USER.getLogin(), SECOND_USER.getPassword(), SECOND_USER.getRole(), Arrays.asList(THIRD_ORDER_DTO));
    public static final UserDto THIRD_USER_DTO = new UserDto(THIRD_USER.getId(), THIRD_USER.getName(),
            THIRD_USER.getLogin(), THIRD_USER.getPassword(), THIRD_USER.getRole(), Arrays.asList(FOURTH_ORDER_DTO, FIFTH_ORDER_DTO));
    public static final UserDto FOURTH_USER_DTO = new UserDto(FOURTH_USER.getId(), FOURTH_USER.getName(),
            FOURTH_USER.getLogin(), FOURTH_USER.getPassword(), FOURTH_USER.getRole(), new ArrayList<>());

    public static final List<UserDto> ALL_USERS_DTO = Arrays.asList(FIRST_USER_DTO,
            SECOND_USER_DTO,
            THIRD_USER_DTO,
            FOURTH_USER_DTO);
}
