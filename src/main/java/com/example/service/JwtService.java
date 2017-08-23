package com.example.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.inject.Inject;

import com.example.auth.SecretKeyProvider;
import com.example.domain.User;
import com.example.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtService {
    private static final String ISSUER = "in.sdqali.jwt";
    public static final String USERNAME = "username";
    private final SecretKeyProvider secretKeyProvider;
    private final UserRepository profileService;

    @SuppressWarnings("unused")
    public JwtService() {
        this(null, null);
    }

    @Autowired
    public JwtService(SecretKeyProvider secretKeyProvider, UserRepository profileService) {
        this.secretKeyProvider = secretKeyProvider;
        this.profileService = profileService;
    }

//    public String tokenFor(MinimalProfile minimalProfile) throws IOException, URISyntaxException {
//        byte[] secretKey = secretKeyProvider.getKey();
//        Date expiration = Date.from(LocalDateTime.now(UTC).plusHours(2).toInstant(UTC));
//        return Jwts.builder()
//                .setSubject(minimalProfile.getUsername())
//                .setExpiration(expiration)
//                .setIssuer(ISSUER)
//                .signWith(SignatureAlgorithm.HS512, secretKey)
//                .compact();
//    }

    public User verify(String token) throws IOException, URISyntaxException {
        byte[] secretKey = secretKeyProvider.getKey();
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

        return profileService.findByUsername(claims.getBody().getSubject().toString());
    }
}
