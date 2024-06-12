package com.whistle6.webfluxdemo.user.service;

import org.springframework.stereotype.Service;

import com.whistle6.webfluxdemo.user.domain.UserModel;
import com.whistle6.webfluxdemo.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
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
    
}
