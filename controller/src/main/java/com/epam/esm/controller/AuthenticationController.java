package com.epam.esm.controller;

import com.epam.esm.dto.AuthenticationDetails;
import com.epam.esm.dto.UserDto;
import com.epam.esm.hateoas.representation.UserRepresentation;
import com.epam.esm.jwt.JwtTokenUtil;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Provides a signUp and login
 * to GiftCertificatesService application
 *
 * @author Ilya Ramanouski
 */
@RestController
public class AuthenticationController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private JwtTokenUtil jwtTokenUtil;

    /**
     * Provides signUp to GiftCertificatesService
     *
     * @param userDto user to signup details
     * @return ResponseEntity with jwt token in 'AUTHORIZATION' header
     */
    @PostMapping("/signUp")
    public ResponseEntity<AuthenticationDetails> signUp(@RequestBody UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserDto user = userService.registerUser(userDto);

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDto.getLogin(), userDto.getPassword()));
        AuthenticationDetails authenticationDetails =
                new AuthenticationDetails(authentication, jwtTokenUtil.generateAccessToken(userDto));

        return ResponseEntity.ok()
                .body(authenticationDetails);
    }

    /**
     * Provides login to GiftCertificatesService
     *
     * @param userDto user to login details
     * @return ResponseEntity with jwt token in 'AUTHORIZATION' header
     */
    @PostMapping("login")
    public ResponseEntity<AuthenticationDetails> login(@RequestBody UserDto userDto) {
       Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDto.getLogin(), userDto.getPassword()));
        AuthenticationDetails authenticationDetails =
                new AuthenticationDetails(authentication, jwtTokenUtil.generateAccessToken(userDto));

        return ResponseEntity.ok()
                .body(authenticationDetails);
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
