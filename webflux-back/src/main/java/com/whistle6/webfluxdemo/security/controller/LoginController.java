package com.whistle6.webfluxdemo.security.controller;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.whistle6.webfluxdemo.security.service.TokenProvider;

import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
public class LoginController {
    // private final PasswordEncoder passwordEncoder;
    // private final ReactiveUserDetailsService userDetailsService;
    // private final TokenProvider tokenProvider;

    // @PostMapping("/login")
    // Mono<ResponseEntity<String>> login(@RequestBody LoginDTO loginRequest) {
    //     return userDetailsService.findByUsername(loginRequest.getEmail())
    //             .filter(u -> passwordEncoder.matches(loginRequest.getPassword(), u.getPassword()))
    //             .map(tokenProvider::generateToken)
    //             .map(ResponseEntity::ok)
    //             .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));
    // }
    
}
