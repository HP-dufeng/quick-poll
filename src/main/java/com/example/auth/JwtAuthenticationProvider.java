package com.example.auth;

import com.example.domain.User;
import com.example.exception.JwtAuthenticationException;
import com.example.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtService jwtService;

    @SuppressWarnings("unused")
    public JwtAuthenticationProvider() {
        this(null);
    }

    @Autowired
    public JwtAuthenticationProvider(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            User possibleProfile = jwtService.verify((String)authentication.getCredentials());
            return new JwtAuthenticatedProfile(possibleProfile);
        } catch (Exception e) {
//            throw new JwtAuthenticationException("Failed to verify token", e);
        }

        return new JwtAuthenticatedProfile(null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthToken.class.equals(authentication);
    }
}
