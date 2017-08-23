//package com.example.security;
//
//import java.io.IOException;
//import java.util.Enumeration;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//public class TokenAuthenticationFilter extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//
//        String xAuth = request.getHeader("X-Authorization");
//
//        // validate the value in xAuth
//        if(isValid(xAuth) == false){
//            throw new SecurityException();
//        }
//
//        // The token is 'valid' so magically get a user id from it
//        Long id = getUserIdFromToken(xAuth);
//
//        // Create our Authentication and let Spring know about it
//        Authentication auth = new DemoAuthenticationToken(id);
//        SecurityContextHolder.getContext().setAuthentication(auth);
//
//        filterChain.doFilter(request, response);
//    }
//
//
//
//
//    private String extractToken(HttpServletRequest request) {
//        // first check the header...
//        String token = extractHeaderToken(request);
//
//        // bearer type allows a request parameter as well
//        if (token == null) {
////            logger.debug("Token not found in headers. Trying request parameters.");
//            token = request.getParameter(OAuth2AccessToken.ACCESS_TOKEN);
//            if (token == null) {
////                logger.debug("Token not found in request parameters.  Not an OAuth2 request.");
//            }
//            else {
//                request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE, OAuth2AccessToken.BEARER_TYPE);
//            }
//        }
//
//        return token;
//    }
//
//
//
//    /**
//     * Extract the OAuth bearer token from a header.
//     *
//     * @param request The request.
//     * @return The token, or null if no OAuth authorization header was supplied.
//     */
//    private String extractHeaderToken(HttpServletRequest request) {
//        Enumeration<String> headers = request.getHeaders("Authorization");
//        while (headers.hasMoreElements()) { // typically there is only one (most servers enforce that)
//            String value = headers.nextElement();
//            if ((value.toLowerCase().startsWith(OAuth2AccessToken.BEARER_TYPE.toLowerCase()))) {
//                String authHeaderValue = value.substring(OAuth2AccessToken.BEARER_TYPE.length()).trim();
//                // Add this here for the auth details later. Would be better to change the signature of this method.
//                request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE,
//                                     value.substring(0, OAuth2AccessToken.BEARER_TYPE.length()).trim());
//                int commaIndex = authHeaderValue.indexOf(',');
//                if (commaIndex > 0) {
//                    authHeaderValue = authHeaderValue.substring(0, commaIndex);
//                }
//                return authHeaderValue;
//            }
//        }
//
//        return null;
//    }
//
//}
