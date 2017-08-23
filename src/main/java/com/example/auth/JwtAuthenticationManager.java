package com.example.auth;

import javax.inject.Inject;

import com.example.exception.JwtAuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationManager implements AuthenticationManager {

    @Inject
    private JwtService jwtService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String accessToken = (String)authentication.getPrincipal();

        try {
            jwtService.verify(accessToken);

            //OK, we can trust this JWT

        } catch (Exception e) {
            //don't trust the JWT!
            throw new JwtAuthenticationException("Invalid token", null);
        }

        return authentication;
    }
}
