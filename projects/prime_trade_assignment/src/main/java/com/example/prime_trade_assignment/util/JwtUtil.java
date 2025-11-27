package com.example.prime_trade_assignment.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key;                // signing key
    private final long expirationMs;      // token expiration duration


    public JwtUtil(@Value("${jwt.secret}") String secret,
                   @Value("${jwt.expiration-ms}") long expirationMs) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = expirationMs;
    }

    public String generateToken(String username) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .setSubject(username)     // store username inside token
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }


    public boolean validateToken(String token, String username) {
        try {
            Claims claims = extractClaims(token);

            // check username + expiration
            return claims.getSubject().equals(username)
                    && claims.getExpiration().after(new Date());

        } catch (JwtException | IllegalArgumentException e) {
            return false;  // token invalid/expired/bad signature
        }
    }


    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

