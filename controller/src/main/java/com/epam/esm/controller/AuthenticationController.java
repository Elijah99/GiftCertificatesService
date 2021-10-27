package com.epam.esm.controller;

import com.epam.esm.dto.UserDto;
import com.epam.esm.hateoas.representation.UserRepresentation;
import com.epam.esm.jwt.JwtTokenUtil;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private UserDetailsService userDetailsService;
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/signUp")
    public ResponseEntity<UserRepresentation> signUp(@RequestBody UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserDto user = userService.registerUser(userDto);
        UserRepresentation createdUser = new UserRepresentation(user);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
                .body(createdUser);
    }

    @PostMapping("login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto) {
        authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userDto.getLogin(), userDto.getPassword()
                        )
                );

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.AUTHORIZATION,
                        jwtTokenUtil.generateAccessToken(userDto)
                )
                .body(userDto);
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }
}
