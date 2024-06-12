package com.whistle6.webfluxdemo.user.service;

import com.whistle6.webfluxdemo.security.domain.LoginDTO;
import com.whistle6.webfluxdemo.user.domain.UserModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserModel> login(LoginDTO loginDTO);
    Flux<UserModel> findAllUsers();
    Mono<UserModel> findUserByEmail(String email);
    Mono<UserModel> findUserById(String id);
    Mono<UserModel> saveUser(UserModel user);
    Mono<UserModel> updateUser(UserModel user);
    Mono<Boolean> deleteUser(String id);
    Mono<Void> deleteAllUsers();
    Flux<UserModel> findUsersByLastName(String lastName);
}
