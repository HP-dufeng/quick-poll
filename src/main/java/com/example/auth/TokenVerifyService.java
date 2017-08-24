package com.example.auth;


import com.example.auth.dto.UserProfile;

public interface TokenVerifyService {

    UserProfile verify(String token);
}
