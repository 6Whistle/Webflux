package com.whistle6.webfluxdemo.chat.service;

import com.whistle6.webfluxdemo.chat.domain.dto.ChatDTO;
import com.whistle6.webfluxdemo.chat.domain.model.ChatModel;
import com.whistle6.webfluxdemo.common.domain.Messenger;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ChatService {

    Flux<ChatDTO> receiveByRoomId(String id);

    Mono<ChatDTO> save(ChatModel entity);
    
}
