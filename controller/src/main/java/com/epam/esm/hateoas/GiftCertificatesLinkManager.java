package com.epam.esm.hateoas;

import com.epam.esm.controller.GiftCertificatesController;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.enums.RequestParameters;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class GiftCertificatesLinkManager implements HateoasLinkManager<GiftCertificateDto> {

    private GiftCertificatesController controller =
            methodOn(GiftCertificatesController.class);
    private TagLinkManager tagLinkModifier;
    private GiftCertificateService service;

    @Override
    public CollectionModel<GiftCertificateDto> createLinks(List<GiftCertificateDto> list, RequestParameters requestParameters) {
        CollectionModel<GiftCertificateDto> model = CollectionModel.of(list);
        list.forEach(this::createLinks);
        return model;
    }

    @Override
    public GiftCertificateDto createLinks(GiftCertificateDto giftCertificateDto) {
        BigInteger dtoId = giftCertificateDto.getId();
        Link dtoLink = linkTo(controller.getGiftCertificate(dtoId)).withSelfRel();
        giftCertificateDto.add(dtoLink);
        giftCertificateDto.getTags().forEach(tagLinkModifier::createLinks);
        return giftCertificateDto;
    }

    @Autowired
    public void setTagLinkModifier(TagLinkManager tagLinkModifier) {
        this.tagLinkModifier = tagLinkModifier;
    }

    @Autowired
    public void setService(GiftCertificateService service) {
        this.service = service;
    }
}
