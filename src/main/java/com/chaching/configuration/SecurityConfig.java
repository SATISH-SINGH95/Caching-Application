package com.chaching.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.chaching.springService.CustomUserDetailService;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    
    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider provider(){
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setPasswordEncoder(passwordEncoder());
        daoProvider.setUserDetailsService(customUserDetailService);
        return daoProvider;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    public static final String[] AUTH_URLS = {
        "/api/v1/auth/**",
        "/v3/api-docs/**",
        "/v3/api-docs.yaml",
        "/swagger-ui/**",
        "/swagger-ui.html"

    };

    public static final String[] EMPLOYEE_URLS = {
        "/employees/**"
    };

    public static final String[] ATTACHMET_DOWNLOAD_URLS = {
        "/file/download/**"
    };

    public static final String[] STORED_PROCEDURE_URLS = {
        "/storedProcedure/**"
    };

    



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

         http
                 .csrf(csrf -> {
                    try {
                        csrf.disable()
                                 .authorizeHttpRequests()
                                 .antMatchers("/user/create/**", "/token").permitAll()
                                 .antMatchers(AUTH_URLS).permitAll()
                                 .antMatchers(EMPLOYEE_URLS).permitAll()
                                 .antMatchers(ATTACHMET_DOWNLOAD_URLS).permitAll()
                                 .antMatchers(STORED_PROCEDURE_URLS).permitAll()
                                 .antMatchers(HttpMethod.DELETE, "/userInfo/id/**").hasRole("ADMIN")
                                 .anyRequest()
                                 .authenticated();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                 .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        http.authenticationProvider(provider());

        return http.build();

    }


}
