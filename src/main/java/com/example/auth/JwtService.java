package com.example.auth;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.inject.Inject;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtService {
    @Inject
    private SecretKeyProvider secretKeyProvider;

    public boolean verify(String token) throws IOException, URISyntaxException {
        byte[] secretKey = secretKeyProvider.getKey();

        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return true;
    }
}
