package com.example.configuration;

import javax.inject.Inject;

import com.example.auth.JwtAuthFilter;
import com.example.auth.JwtAuthenticationEntryPoint;
import com.example.auth.JwtAuthenticationManager;
import com.example.auth.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthEndPoint;

    @Override
    public void configure(AuthenticationManagerBuilder auth)  throws Exception {
        auth.authenticationProvider(jwtAuthenticationProvider);
    }



    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] patterns = new String[] {
                "/swagger-ui/**",
                "/api-docs/**"
        };

        http.authorizeRequests()
                .antMatchers(patterns)
                .permitAll()
                .antMatchers("/**/*")
                .authenticated()
                .and()
                .addFilterBefore(jwtAuthFilter, BasicAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthEndPoint);
    }

}
