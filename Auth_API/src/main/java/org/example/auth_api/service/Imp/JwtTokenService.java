package org.example.auth_api.service.Imp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.example.auth_api.model.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;

@Service
public class JwtTokenService {
    String secretkey="ThisIsAVeryLongSecretKeyForJWTToken12345";
    public String generateToken(Users users) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roles=new ArrayList<>();

        roles.add(users.getRole().getRoleType());
        claims.put("roles", roles);
        String token= Jwts.builder()

                .claims(claims)

                .subject(users.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*60*1000))

                .signWith(createKey())
                .compact();
        return token;
    }

    private Key createKey(){
        byte[] keyBytes = secretkey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsername(String token){
        return Jwts.parser().verifyWith((SecretKey) createKey()).build().parseSignedClaims(token).getPayload().getSubject();
    }
    public boolean validateToken(String token, UserDetails userDetails){
        String username = getUsername(token);
        return username.equals(userDetails.getUsername());
    }
}
