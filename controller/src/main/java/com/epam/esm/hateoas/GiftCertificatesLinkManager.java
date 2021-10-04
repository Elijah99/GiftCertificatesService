package com.epam.esm.hateoas;

import com.epam.esm.controller.GiftCertificatesController;
import com.epam.esm.dto.RequestParameters;
import com.epam.esm.hateoas.representation.GiftCertificateRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class GiftCertificatesLinkManager implements HateoasLinkManager<GiftCertificateRepresentation> {

    private GiftCertificatesController controller = methodOn(GiftCertificatesController.class);
    private TagLinkManager tagLinkModifier;

    @Override
    public CollectionModel<GiftCertificateRepresentation> createLinks(List<GiftCertificateRepresentation> list, RequestParameters requestParameters) {
        CollectionModel<GiftCertificateRepresentation> model = CollectionModel.of(list);

        return model;
    }

    @Autowired
    public void setTagLinkModifier(TagLinkManager tagLinkModifier) {
        this.tagLinkModifier = tagLinkModifier;
    }

}
