package com.fgal.authservice.security;

import com.fgal.authservice.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class JwtTokenService {

    private final JwtProperties jwtProperties;


    private Key getKey() {
        return Keys.hmacShaKeyFor(
                jwtProperties.getSecret()
                        .getBytes(StandardCharsets.UTF_8)
        );
    }
    private String generateToken(User user ){
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("role",user.getRole())
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+jwtProperties.getExpiration()))
                .signWith(getKey())
                .compact();
    }
    public Claims parseToken(String token){
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
    }
}
