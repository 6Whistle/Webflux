package com.whistle6.webfluxdemo.security.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.whistle6.webfluxdemo.security.domain.TokenModel;

import reactor.core.publisher.Mono;

@Repository
public interface TokenRepository extends ReactiveMongoRepository<TokenModel, String>{

    Mono<TokenModel> findByRefreshToken(String jwt);
    
}
