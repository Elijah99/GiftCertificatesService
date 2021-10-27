package com.epam.esm.dto;

import com.epam.esm.entity.Role;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class UserDto implements Serializable {

    private Long id;
    private String name;
    private String login;
    private String password;
    private Role role;
    private List<OrderDto> orders;

    public UserDto() {
    }

    public UserDto(Long id, String name, List<OrderDto> orders) {
        this.id = id;
        this.name = name;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(name, userDto.name) && Objects.equals(login, userDto.login) && Objects.equals(password, userDto.password) && role == userDto.role && Objects.equals(orders, userDto.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, password, role, orders);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", role=" + role +
                ", orders=" + orders +
                '}';
    }
}
