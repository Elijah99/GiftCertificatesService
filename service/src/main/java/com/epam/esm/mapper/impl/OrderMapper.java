package com.epam.esm.mapper.impl;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Order;
import com.epam.esm.mapper.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper implements DtoMapper<Order, OrderDto> {

    private GiftCertificateMapper giftCertificateMapper;

    @Override
    public OrderDto mapEntityToDto(Order entity) {
        OrderDto dto = new OrderDto();

        dto.setId(entity.getId());
        dto.setCost(entity.getCost());
        dto.setPurchaseDate(entity.getPurchaseDate());
        if (entity.getGiftCertificates() != null) {
            List<GiftCertificate> giftCertificates = entity.getGiftCertificates();
            dto.setGiftCertificates(giftCertificateMapper.mapListEntityToListDto(giftCertificates));
        }
        dto.setIdUser(entity.getUser().getId());

        return dto;
    }

    @Override
    public Order mapDtoToEntity(OrderDto dto) {
        Order order = new Order();

        order.setId(dto.getId());
        order.setCost(dto.getCost());
        order.setPurchaseDate(dto.getPurchaseDate());
        if (dto.getGiftCertificates() != null) {
            List<GiftCertificateDto> giftCertificateDtos = dto.getGiftCertificates();
            order.setGiftCertificates(giftCertificateMapper.mapListDtoToListEntity(giftCertificateDtos));
        }

        return order;
    }

    @Autowired
    public void setGiftCertificateMapper(GiftCertificateMapper giftCertificateMapper) {
        this.giftCertificateMapper = giftCertificateMapper;
    }
}
