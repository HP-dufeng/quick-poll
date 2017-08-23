//package com.example.auth;
//
//import java.io.IOException;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//public class JwtAuthFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest,
//                                    HttpServletResponse httpServletResponse,
//                                    FilterChain filterChain) throws ServletException, IOException {
//
//        HttpServletRequest servletRequest = (HttpServletRequest) request;
//        String authorization = servletRequest.getHeader("Authorization");
//        if (authorization != null) {
//            JwtAuthToken token = new JwtAuthToken(authorization.replaceAll("Bearer ", ""));
//            SecurityContextHolder.getContext().setAuthentication(token);
//        }
//        filterChain.doFilter(httpServletRequest, httpServletResponse);
//    }
//}
