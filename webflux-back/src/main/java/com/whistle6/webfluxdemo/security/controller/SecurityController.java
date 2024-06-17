package com.whistle6.webfluxdemo.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whistle6.webfluxdemo.common.domain.Messenger;
import com.whistle6.webfluxdemo.security.service.TokenService;

// import com.whistle6.webfluxdemo.security.service.TokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/security")
public class SecurityController {
    private final TokenService tokenService;

    @PostMapping("/refresh")
    public Mono<ResponseEntity<Messenger>> refresh(@RequestHeader("Authorization") String refreshToken) {
        log.info("Refresh request received : {}", refreshToken);
        return tokenService.isRefreshTokenValid(refreshToken)
            .flatMap(i -> Mono.just(tokenService.generateToken(i, false)))
            .flatMap(i -> Mono.just(ResponseEntity.ok(Messenger.builder().data(i).accessToken(i).accessExpired(tokenService.getAccessTokenExpired()).build())))
            .switchIfEmpty(Mono.just(ResponseEntity.badRequest().body(Messenger.builder().message("Refresh token is invalid").build())));
    }
}
