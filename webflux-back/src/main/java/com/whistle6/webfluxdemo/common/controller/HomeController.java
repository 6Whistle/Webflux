package com.whistle6.webfluxdemo.common.controller;

import java.time.Duration;
import java.time.LocalDateTime;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
public class HomeController {
    @GetMapping(path = "/", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> home() {
        log.info("Home page accessed");
        return Flux.<String>generate((sink) -> sink.next("Server Time : " + LocalDateTime.now()))
        .delayElements(Duration.ofSeconds(1));
    }
}
