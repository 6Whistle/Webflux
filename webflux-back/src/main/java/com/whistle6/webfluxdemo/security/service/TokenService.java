package com.whistle6.webfluxdemo.security.service;

import com.whistle6.webfluxdemo.user.domain.UserModel;

import reactor.core.publisher.Mono;

public interface TokenService {
    String generateToken(UserModel user, Boolean isRefreshToken);
    Mono<String> createRefreshToken(UserModel user);
    Long getAccessTokenExpired();
    Long getRefreshTokenExpired();

    Mono<UserModel> isRefreshTokenValid(String token);
}
