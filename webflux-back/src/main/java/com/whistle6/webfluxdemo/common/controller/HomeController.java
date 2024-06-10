package com.whistle6.webfluxdemo.common.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " Welcome to WebFlux Demo!";
    }
}
