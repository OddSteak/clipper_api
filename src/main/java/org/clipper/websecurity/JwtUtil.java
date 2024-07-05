package org.clipper.websecurity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtUtil {
    SecretKey key = Jwts.SIG.HS256.key().build();

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDeets) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDeets.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().claims(claims).subject(subject).issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
            .compact();
    }

    public boolean validateToken(String token, UserDetails userDeets) {
        final String username = extractUsername(token);
        return (username.equals(userDeets.getUsername())) && !isTokenExpired(token);
    }
}
