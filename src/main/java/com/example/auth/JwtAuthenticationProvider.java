package com.example.auth;

import com.example.auth.dto.UserProfile;
import com.example.exception.JwtAuthenticationException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Inject
    private TokenVerifyService jwtService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String accessToken = (String)authentication.getPrincipal();
            UserProfile possibleProfile = jwtService.verify(accessToken);
            return new JwtAuthenticatedProfile(possibleProfile);
        } catch (Exception e) {
            throw new JwtAuthenticationException("Failed to verify token", e);
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PreAuthenticatedAuthenticationToken.class.equals(authentication);
    }
}
