package com.whistle6.webfluxdemo.security.domain;

import java.util.Collection;
import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.whistle6.webfluxdemo.user.domain.UserModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDetailsModel implements UserDetails{
    private final UserModel user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new GrantedAuthority(){
            @Override
            public String getAuthority(){
                // return user.getRole();
                return "";
            }
        });
        return authorities;
    }
    
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 1년 이상 로그인이 없으면 비활성화
        // 현 시간 - 마지막 로그인 시간 > 1년
        return true;
    }
}
