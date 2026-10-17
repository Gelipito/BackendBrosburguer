package com.example.demo.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value; // <-- Importación correcta de Spring
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final SecretKey key;

    // Al usar el constructor, Spring inyecta el secreto aquí antes de que la clase termine de armarse
    public JwtService(@Value("${jwt.secret}") String secretKey) {
        // Validamos que el secreto sea lo suficientemente largo para el algoritmo HMAC (mínimo 256 bits / 32 caracteres)
        if (secretKey == null || secretKey.getBytes(StandardCharsets.UTF_8).length < 32) {
            throw new IllegalArgumentException("La clave secreta de JWT debe tener al menos 32 caracteres (256 bits)");
        }
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000)) // 24 horas
                .signWith(key) // Firma moderna compatible con jjwt 0.12.x
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, String username) {
        String tokenUsername = extractUsername(token);
        return tokenUsername.equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token)
                .getExpiration()
                .before(new Date());
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(key) // Verificación moderna compatible con jjwt 0.12.x
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
