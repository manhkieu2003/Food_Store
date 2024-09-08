package com.example.food.config;

import com.example.food.entity.User;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CustomUser implements UserDetails {
    User user;

    public CustomUser(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());

        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        return user.getUpassword();
    }

    @Override
    public String getUsername() {
        return user.getUemail();
    }

    @Override
    public boolean isAccountNonExpired() {
       return  true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return  true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return  true;
    }

    @Override
    public boolean isEnabled() {
        return  true;
    }
}
