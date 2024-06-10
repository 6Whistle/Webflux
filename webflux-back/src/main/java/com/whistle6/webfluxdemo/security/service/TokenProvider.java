package com.whistle6.webfluxdemo.security.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenProvider {
    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token);
}
