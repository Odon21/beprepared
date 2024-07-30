package com.odon.beprepared.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {
    private static final String SECRET_KEY = "RGBaBH05vrFOgOFwv7AZ4MO3qGAAB044";
    private static final long JWT_EXPIRATION = 86400000;

    public String extractUserFormToken(String token){
        return extractClaim(token, Claims::getSubject);
    }

     public <T>T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }



    public  boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extractClaims,
                                UserDetails userDetails){
        return buildToken(extractClaims, userDetails, JWT_EXPIRATION);

    }

    private String buildToken(Map<String, Object> extractClaims,
                              UserDetails userDetails,
                              long expiration){
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUserFormToken(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaimsFromToken(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }

    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
