package com.chaching.springService;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.chaching.model.entity.UserLoginCredentials;

public class CustomUserDetail implements UserDetails{

    private UserLoginCredentials userLoginCredentials;

    public CustomUserDetail(UserLoginCredentials userLoginCredentials) {
        this.userLoginCredentials = userLoginCredentials;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userLoginCredentials.getRole());
        return Arrays.asList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return userLoginCredentials.getPassword();
    }

    @Override
    public String getUsername() {
        return userLoginCredentials.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return userLoginCredentials.getStatus();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



    
    
}
