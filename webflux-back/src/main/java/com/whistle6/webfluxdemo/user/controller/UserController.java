package com.whistle6.webfluxdemo.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.whistle6.webfluxdemo.common.domain.Messenger;
import com.whistle6.webfluxdemo.security.domain.LoginDTO;
import com.whistle6.webfluxdemo.user.domain.UserModel;
import com.whistle6.webfluxdemo.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    @PostMapping("/login")
    public Mono<ResponseEntity<Messenger>> login(@RequestBody LoginDTO entity) {
        log.info("Login request received : {}", entity);
        return userService.login(entity)
            .map(i -> ResponseEntity.ok(Messenger.builder().data(i).accessToken("test_access_token").refreshToken("test_refresh_token").build()))
            .defaultIfEmpty(ResponseEntity.badRequest().body(Messenger.builder().message("User not found").build()));
    }

    @GetMapping("/logout")
    public Mono<ResponseEntity<Messenger>> logout(@RequestHeader("Authentication") String accessToken) {
        log.info("Logout request received : {}", accessToken);
        return Mono.just(ResponseEntity.ok(Messenger.builder().data("LOGOUT SUCCESS").build()));
    }
    
    @GetMapping("/all")
    public Mono<ResponseEntity<Messenger>> all(@RequestHeader("Authentication") String accessToken) {
        log.info("All user request received : {}", accessToken);
        return userService.findAllUsers().collectList().map(i -> ResponseEntity.ok(Messenger.builder().data(i).build()));
    }

    @GetMapping("/detail/{id}")
    public Mono<ResponseEntity<Messenger>> detail(@RequestHeader("Authentication") String accessToken, @PathVariable String id) {
        log.info("User detail request received : {}", accessToken);
        return userService.findUserById(id)
                .map(user -> ResponseEntity.ok(Messenger.builder().data(user).build()))
                .defaultIfEmpty(ResponseEntity.badRequest().body(Messenger.builder().message("User not found").build()));
    }

    @PostMapping("/save")
    public Mono<ResponseEntity<Messenger>> detail(@RequestBody UserModel user) {
        log.info("User save request received : {}", user);
        return userService.saveUser(user)
                .map(i -> ResponseEntity.ok(Messenger.builder().data(i).build()))
                .defaultIfEmpty(ResponseEntity.badRequest().body(Messenger.builder().message("User not saved").build()));
    }

    @PostMapping("/update")
    public Mono<ResponseEntity<Messenger>> update(@RequestHeader("Authentication") String accessToken, @RequestBody UserModel user) {
        log.info("User update request received : {}", accessToken);
        return userService.updateUser(user)
                .map(i -> ResponseEntity.ok(Messenger.builder().data(i).build()))
                .defaultIfEmpty(ResponseEntity.badRequest().body(Messenger.builder().message("User not updated").build()));
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Messenger>> delete(@RequestHeader("Authentication") String accessToken, @PathVariable String id) {
        log.info("User delete request received : {}", accessToken);
        return userService.deleteUser(id)
                .map(i -> i.equals(true)
                ? ResponseEntity.ok(Messenger.builder().message("User deleted").build()) 
                : ResponseEntity.badRequest().body(Messenger.builder().message("User not deleted").build()));
    }

    @DeleteMapping("/delete/all")
    public Mono<ResponseEntity<Messenger>> deleteAll(@RequestHeader("Authentication") String accessToken) {
        log.info("User delete all request received : {}", accessToken);
        return userService.deleteAllUsers().map(i -> ResponseEntity.ok(Messenger.builder().message("All users deleted").build()));
    }
}
