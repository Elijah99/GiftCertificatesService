package com.epam.esm.dto;

import com.epam.esm.entity.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Objects;

public class AuthenticationDetails implements Serializable {

    private String name;
    private String login;
    private Role role;
    private String token;

    public AuthenticationDetails(Authentication authentication, String token) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        this.name = authentication.getName();
        this.login = userDetails.getUsername();
        if (userDetails.getAuthorities().stream().findFirst().isPresent()) {
            GrantedAuthority authority = userDetails.getAuthorities().stream().findFirst().get();
            this.role = Role.getRole(authority.getAuthority().toString());
        }
        this.token = token;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationDetails that = (AuthenticationDetails) o;
        return Objects.equals(name, that.name) && Objects.equals(login, that.login) && role == that.role && Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, role, token);
    }
}
