package com.example.schoolproject.utils;

import com.example.schoolproject.Entities.SchoolUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Component
@RequiredArgsConstructor
public class JWTUtil {

    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";
    //    @Value("${SECRET_KEY}")
    private final String secret = "mySuperSecretKeyWithAtLeast32BytesLength";
    private long accessTokenValidity = 60 * 60 * 1000;
    private JwtParser jwtParser;

    public String createToken(SchoolUser schoolUser) {
        Claims claims = Jwts.claims().setSubject(schoolUser.getUserName());
        Date tokenCreateTime = new Date();

        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .setIssuedAt(tokenCreateTime)
                .signWith(key, SignatureAlgorithm.HS256)
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

    @SuppressWarnings("deprecation")
    public JwtParser jwtParser() {
        return Jwts.parser().setSigningKey(secret);
    }
}
