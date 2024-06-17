package com.whistle6.webfluxdemo.chat.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;

import com.whistle6.webfluxdemo.chat.domain.model.ChatModel;

import reactor.core.publisher.Flux;

@Repository
public interface ChatRepository extends ReactiveMongoRepository<ChatModel, String>{
    // @Tailable
    Flux<ChatModel> findByRoomId(String roomId);
}
