package com.epam.esm.dto;

import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.List;

public class UserDto implements Serializable {

    private BigInteger id;
    private String name;
    private List<OrderDto> orders;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) &&
                Objects.equals(name, userDto.name) &&
                Objects.equals(orders, userDto.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
