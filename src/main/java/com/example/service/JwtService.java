package com.example.service;

import com.example.auth.TokenVerifyService;
import com.example.auth.dto.UserProfile;
import com.example.dto.JwtUserProfile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtService implements TokenVerifyService {

    public UserProfile verify(String token) throws RestClientException {

             String QUICK_POLL_URI_V1 = "http://wucc.wdqh.com:6789/identity/connect/userinfo";
             RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);

            HttpEntity<?> entity = new HttpEntity<>(headers);

//            HttpEntity<Object> response = restTemplate.exchange(
//                    QUICK_POLL_URI_V1,
//                    HttpMethod.GET,
//                    entity,
//                    Object.class);



//            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);


        JwtUserProfile user = new JwtUserProfile();

        String initList[] = { "ROLE_ADMIN", "Two", "Four", "One",};
        List list = new ArrayList(Arrays.asList(initList));
        user.setRole(list);

        return user;


    }




}
