//package com.example.auth;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureException;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JwtAuthenticationProvider implements AuthenticationProvider {
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String accessToken = (String)authentication.getCredentials();
//        String secrectKey = "";
//
//        try {
//            Jwts.parser().setSigningKey(secrectKey).parseClaimsJws(accessToken);
//
//            //OK, we can trust this JWT
//
//        } catch (SignatureException e) {
//            //don't trust the JWT!
//            throw new SignatureException("无效的token");
//        }
//
//        return authentication;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return true;
//    }
//}
