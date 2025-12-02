package com.simap.simap_backend.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(
            "CHAVE_SECRETA_SUPER_FORTE_256_BITS_AQUI".getBytes()
    );

    public String generateToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setIssuer("SIMAP_BACKEND")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24))) // 24h
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
