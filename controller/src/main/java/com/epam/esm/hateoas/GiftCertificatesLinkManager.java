package com.epam.esm.hateoas;

import com.epam.esm.controller.GiftCertificatesController;
import com.epam.esm.dto.RequestParameters;
import com.epam.esm.hateoas.representation.GiftCertificateRepresentation;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class GiftCertificatesLinkManager implements HateoasLinkManager<GiftCertificateRepresentation> {

    private static final int FIRST_PAGE = 1;

    private final GiftCertificatesController controller = methodOn(GiftCertificatesController.class);
    private TagLinkManager tagLinkModifier;
    private GiftCertificateService giftCertificateService;

    @Override
    public CollectionModel<GiftCertificateRepresentation> createLinks(List<GiftCertificateRepresentation> list, RequestParameters requestParameters) {
        CollectionModel<GiftCertificateRepresentation> model = CollectionModel.of(list);
        if (list.isEmpty()) {
            return model;
        }
        int page = requestParameters.getCurrentPage();
        int pageAmount = (int) giftCertificateService.countPages(requestParameters);
        if (pageAmount != 0) {
            Link firstPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftCertificatesController.class).
                    getAllGiftCertificates(FIRST_PAGE,
                            requestParameters.getPageSize(),
                            requestParameters.getSortType(),
                            requestParameters.getSortValue(),
                            requestParameters.getSearchParameter(),
                            requestParameters.getSearchValue())).withRel("first");
            model.add(firstPageLink.expand());

            if (requestParameters.getCurrentPage() != 1) {
                int prevPage = requestParameters.getCurrentPage() - 1;
                Link prevPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftCertificatesController.class).
                        getAllGiftCertificates(
                                prevPage,
                                requestParameters.getPageSize(),
                                requestParameters.getSortType(),
                                requestParameters.getSortValue(),
                                requestParameters.getSearchParameter(),
                                requestParameters.getSearchValue())).withRel("prev");
                model.add(prevPageLink.expand());
            }

            Link selfRelLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftCertificatesController.class).
                    getAllGiftCertificates(requestParameters.getCurrentPage(),
                            requestParameters.getPageSize(),
                            requestParameters.getSortType(),
                            requestParameters.getSortValue(),
                            requestParameters.getSearchParameter(),
                            requestParameters.getSearchValue())).withSelfRel();
            model.add(selfRelLink.expand());


            if (page != pageAmount) {
                int nextPage = requestParameters.getCurrentPage() + 1;
                Link nextPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftCertificatesController.class).
                        getAllGiftCertificates(nextPage,
                                requestParameters.getPageSize(),
                                requestParameters.getSortType(),
                                requestParameters.getSortValue(),
                                requestParameters.getSearchParameter(),
                                requestParameters.getSearchValue())).withRel("next");
                model.add(nextPageLink.expand());
            }

            Link lastPageLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftCertificatesController.class).
                    getAllGiftCertificates(pageAmount,
                            requestParameters.getPageSize(),
                            requestParameters.getSortType(),
                            requestParameters.getSortValue(),
                            requestParameters.getSearchParameter(),
                            requestParameters.getSearchValue())).withRel("last");
            model.add(lastPageLink.expand());


        }
        return model;
    }

    @Autowired
    public void setTagLinkModifier(TagLinkManager tagLinkModifier) {
        this.tagLinkModifier = tagLinkModifier;
    }

    @Autowired
    public void setGiftCertificateService(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }
}
