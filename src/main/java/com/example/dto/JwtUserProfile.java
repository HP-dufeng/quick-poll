package com.example.dto;

import com.example.auth.dto.UserProfile;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class JwtUserProfile implements UserProfile{

    private Collection<String> role;

    @Override
    public Collection<String> getRole() {
        return null;
    }

    public void setRole(Collection<String> role){
        this.role = role;
    }
}
