package com.whistle6.webfluxdemo.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.whistle6.webfluxdemo.common.domain.Messenger;
import com.whistle6.webfluxdemo.security.domain.LoginDTO;
import com.whistle6.webfluxdemo.security.service.TokenService;
import com.whistle6.webfluxdemo.user.domain.UserDTO;
import com.whistle6.webfluxdemo.user.domain.UserModel;
import com.whistle6.webfluxdemo.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    public Flux<UserModel> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Mono<UserModel> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Mono<UserModel> findUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<UserModel> saveUser(UserModel user) {
        return userRepository.save(user);
    }

    @Override
    public Mono<UserModel> updateUser(UserModel user) {
        return userRepository.findById(user.getId()).flatMap(i -> {
                i.setEmail(user.getEmail());
                i.setFirstName(user.getFirstName());
                i.setLastName(user.getLastName());
                i.setPassword(user.getPassword());
                i.setRoles(user.getRoles());
                return userRepository.save(i);
            }
        );
    }

    @Override
    public Mono<Boolean> deleteUser(String id) {
        return userRepository.findById(id).flatMap(i -> userRepository.delete(i).then(Mono.just(Boolean.TRUE)))
                .defaultIfEmpty(Boolean.FALSE);
    }

    @Override
    public Mono<Void> deleteAllUsers() {
        return userRepository.deleteAll();
    }

    @Override
    public Flux<UserModel> findUsersByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    @Override
    public Mono<Messenger> login(LoginDTO loginDTO) {
        return userRepository.findByEmail(loginDTO.getEmail())
            .filter(i -> i.getPassword().equals(loginDTO.getPassword()))
            .flatMap(i -> tokenService.createRefreshToken(i).flatMap(j -> Mono.just(List.of(i, j))))
            .flatMap(i -> Mono.just(Messenger.builder()
                .data(
                    UserDTO.builder()
                    .id(((UserModel)i.get(0)).getId())
                    .email(((UserModel)i.get(0)).getEmail())
                    .firstName(((UserModel)i.get(0)).getFirstName())
                    .lastName(((UserModel)i.get(0)).getLastName())
                    .build()
                )
                .message("Login success")
                .accessToken(tokenService.generateToken(((UserModel)i.get(0)), false))
                .accessExpired(tokenService.getAccessTokenExpired())
                .refreshToken((String)i.get(1))
                .refreshExpired(tokenService.getRefreshTokenExpired())
                .build())
                .log()
            );
    }
    
}
