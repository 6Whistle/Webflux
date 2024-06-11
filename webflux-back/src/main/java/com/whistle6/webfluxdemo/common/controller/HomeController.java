package com.whistle6.webfluxdemo.common.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class HomeController {
    @GetMapping("/api/home")
    public Mono<String> home() {
        log.info("Home page accessed");
        return Mono.just(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " Welcome to WebFlux Demo!");
    }
}
