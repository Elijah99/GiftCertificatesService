package com.epam.esm.jwt;

import com.epam.esm.dto.UserDto;
import com.epam.esm.exception.jwt.CustomExpiredJwtException;
import com.epam.esm.exception.jwt.CustomMalformedJwtException;
import com.epam.esm.exception.jwt.CustomSignatureException;
import com.epam.esm.exception.jwt.CustomUnsupportedJwtException;
import com.epam.esm.exception.jwt.IllegalJwtTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@PropertySource("classpath:jwt.properties")
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String JWT_SECRET;
    @Value("${jwt.duration}")
    private int duration;

    public String generateAccessToken(UserDto user) {
        Date expirationDate = new Date(System.currentTimeMillis() + duration);
        return Jwts.builder()
                .setSubject(user.getLogin())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            throw new CustomSignatureException();
        } catch (MalformedJwtException ex) {
            throw new CustomMalformedJwtException();
        } catch (ExpiredJwtException ex) {
            throw new CustomExpiredJwtException();
        } catch (UnsupportedJwtException ex) {
            throw new CustomUnsupportedJwtException();
        } catch (IllegalArgumentException ex) {
            throw new IllegalJwtTokenException();
        }
    }
}
