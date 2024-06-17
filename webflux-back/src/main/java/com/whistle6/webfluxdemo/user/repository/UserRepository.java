package com.whistle6.webfluxdemo.user.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;

import com.whistle6.webfluxdemo.user.domain.UserModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<UserModel, String>{
    @Tailable
    Flux<UserModel> findByLastName(String lastName);
    
    @Tailable
    Mono<UserModel> findByEmail(String email);
}
