package com.epam.esm.hateoas;

import com.epam.esm.controller.UserController;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.enums.RequestParameters;
import com.epam.esm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class OrderLinkManager implements HateoasLinkManager<OrderDto> {

    private static final int FIRST_PAGE = 1;
    private UserController controller = methodOn(UserController.class);
    private GiftCertificatesLinkManager giftCertificatesLinkManager;
    private OrderService service;

    @Override
    public CollectionModel<OrderDto> createLinks(List<OrderDto> list, RequestParameters requestParameters) {
        CollectionModel<OrderDto> model = CollectionModel.of(list);
        if(list.isEmpty()){
            return model;
        }
        BigInteger userId = list.get(0).getIdUser();
        int page = requestParameters.getCurrentPage();
        int pageAmount = Math.toIntExact(service.count(userId, requestParameters));
        if (pageAmount != 0) {
            requestParameters.setCurrentPage(FIRST_PAGE);
            Link firstPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
                    getOrdersByUserId(userId, requestParameters.getCurrentPage(), requestParameters.getPageSize(),null,null,null,null)).withRel("first");
            model.add(firstPage.expand());

            requestParameters.setCurrentPage(pageAmount);
            Link lastPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
                    getOrdersByUserId(userId, requestParameters.getCurrentPage(), requestParameters.getPageSize(),null,null,null,null)).withRel("last");
            model.add(lastPage.expand());

            if (requestParameters.getCurrentPage() != 1) {
                requestParameters.setCurrentPage(requestParameters.getCurrentPage() - 1);
                Link prevPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
                        getOrdersByUserId(userId, requestParameters.getCurrentPage(), requestParameters.getPageSize(),null,null,null,null)).withRel("prev");
                model.add(prevPage.expand());
            }

            if (page != pageAmount) {
                requestParameters.setCurrentPage(page + 1);
                Link nextPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
                        getOrdersByUserId(userId, requestParameters.getCurrentPage(), requestParameters.getPageSize(),null,null,null,null)).withRel("next");
                model.add(nextPage.expand());
            }
        }
        model.forEach(this::createLinks);
        return model;
    }

    @Override
    public OrderDto createLinks(OrderDto dto) {
        BigInteger dtoId = dto.getId();
        BigInteger userId = dto.getIdUser();
        Link dtoLink = WebMvcLinkBuilder.linkTo(controller.getOrderById(userId, dtoId)).withSelfRel();
        Link ordersLink =
                WebMvcLinkBuilder.linkTo(controller.getOrdersByUserId(userId, null, null, null, null, null, null))
                        .withRel("orders");
        // !!! AopConfigException
        Link addLink = WebMvcLinkBuilder.linkTo(controller.createOrder(userId,dto)).withRel("create");
        dto.add(dtoLink, addLink, ordersLink);
        dto.getGiftCertificates().forEach(giftCertificatesLinkManager::createLinks);
        return dto;
    }

    @Autowired
    public void setGiftCertificatesLinkManager(GiftCertificatesLinkManager giftCertificatesLinkManager) {
        this.giftCertificatesLinkManager = giftCertificatesLinkManager;
    }

    @Autowired
    public void setService(OrderService service) {
        this.service = service;
    }
}
