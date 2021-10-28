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
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    private final static String JWT_SECRET = "zdtlD3JK56m6wTTgsNFhqzjqP";
    private final static Date EXPIRATION_DATE = new Date(System.currentTimeMillis() + 60 * 60 * 1000);

    public String generateAccessToken(UserDto user) {
        return Jwts.builder()
                .setSubject(user.getLogin())
                .setExpiration(EXPIRATION_DATE)
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
