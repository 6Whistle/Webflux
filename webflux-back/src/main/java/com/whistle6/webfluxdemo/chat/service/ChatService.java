package com.whistle6.webfluxdemo.chat.service;

import org.springframework.http.codec.ServerSentEvent;

import com.whistle6.webfluxdemo.chat.domain.dto.ChatDTO;
import com.whistle6.webfluxdemo.chat.domain.model.ChatModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ChatService {

    Flux<ChatDTO> receiveByRoomId(String id);

    Mono<Boolean> save(ChatModel entity);

    Flux<ServerSentEvent<ChatDTO>> connect(String roomId);
    
}
