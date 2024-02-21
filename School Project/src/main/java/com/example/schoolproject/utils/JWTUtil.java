package com.example.schoolproject.utils;

import com.example.schoolproject.Entities.SchoolUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Component
public class JWTUtil {

    private final JwtParser jwtParser;
    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";
    private long accessTokenValidity = 60 * 60 * 1000;
    @Value("${SECRET_KEY")
    private String secret;

    public JWTUtil() {
        this.jwtParser = Jwts.parser().setSigningKey(secret);
    }

    public String createToken(SchoolUser schoolUser) {
        Claims claims = Jwts.claims().setSubject(schoolUser.getUserName());
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    private Claims parseJWTClaims(String token) {
        return jwtParser.parseClaimsJwt(token).getBody();
    }

    public Claims resolveClaims(HttpServletRequest request) {
        try {
            String token = resolveToken(request);
            if (token != null) {
                return parseJWTClaims(token);
            }
            return null;
        } catch (Exception e) {
            request.setAttribute("invalid request", e.getMessage());
            throw e;
        }
    }

    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw e;
        }
    }

    public String getUserEmail(Claims claims) {
        return claims.getSubject();
    }

    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}
