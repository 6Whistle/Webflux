package com.whistle6.webfluxdemo.security.service;

import com.whistle6.webfluxdemo.security.domain.TokenModel;
import com.whistle6.webfluxdemo.user.domain.UserModel;

import reactor.core.publisher.Mono;

public interface TokenService {
    String generateToken(UserModel user, Boolean isRefreshToken);
    String createRefreshToken(UserModel user);
}
