package com.whistle6.webfluxdemo.user.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.whistle6.webfluxdemo.common.domain.Messenger;
import com.whistle6.webfluxdemo.security.domain.LoginDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    
    @PostMapping("/login")
    public Mono<ResponseEntity<Messenger>> login(@RequestBody LoginDTO entity) {
        log.info("Login request received : {}", entity);
        return Mono.just(
            ResponseEntity.ok(
                Messenger.builder()
                .data(Map.of("id", 1, "firstName", "John", "lastName", "Doe", "email", "john@example.com", "phone", "1234567890", "role", "user"))
                .accessToken("temp_access_token")
                .refreshToken("temp_refresh_token")
                .build()
            )
        );
    }

    @GetMapping("/logout")
    public Mono<ResponseEntity<Messenger>> logout(@RequestHeader String accessToken) {
        log.info("Logout request received : {}", accessToken);
        return Mono.just(ResponseEntity.ok(Messenger.builder().data("LOGOUT SUCCESS").build()));
    }
    
}
