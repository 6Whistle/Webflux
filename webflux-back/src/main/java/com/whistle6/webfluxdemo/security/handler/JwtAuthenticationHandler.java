package com.whistle6.webfluxdemo.security.handler;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.whistle6.webfluxdemo.security.domain.BearerToken;
import com.whistle6.webfluxdemo.security.exception.JwtAuthenticationException;
import com.whistle6.webfluxdemo.security.service.TokenProvider;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
class JwtAuthenticationHandler implements ReactiveAuthenticationManager {

    private final TokenProvider tokenProvider;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
                .cast(BearerToken.class)
                .filter(jwtToken -> tokenProvider.isTokenValid(jwtToken.getToken()))
                .map(jwtToken -> jwtToken.withAuthenticated(true))
                .switchIfEmpty(Mono.error(new JwtAuthenticationException("Invalid token.")));
    }
}