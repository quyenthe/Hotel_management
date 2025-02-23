package org.example.auth_api.service.Imp;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtTokenService {
    String secretkey="";
    public String generateToken(String username) {
        String token= Jwts.builder()
                .claims()
                .add(new HashMap<>())
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*60*1000))
                .and()
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
