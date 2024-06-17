package com.whistle6.webfluxdemo.chat.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.whistle6.webfluxdemo.chat.domain.dto.ChatDTO;
import com.whistle6.webfluxdemo.chat.domain.model.ChatModel;
import com.whistle6.webfluxdemo.chat.repository.ChatRepository;
import com.whistle6.webfluxdemo.common.domain.Messenger;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
    private final ChatRepository chatRepository;

    @Override
    public Flux<ChatDTO> receiveByRoomId(String id) {
        return chatRepository.findByRoomId(id).map(i -> 
            ChatDTO.builder()
            .id(i.getId())
            .roomId(i.getRoomId())
            .msg(i.getMsg())
            .sender(i.getSender())
            .createdAt(LocalDateTime.now())
            .build());
    }

    @Override
    public Mono<ChatDTO> save(ChatModel entity) {
        return chatRepository.save(entity)
        .flatMap(i -> Mono.just(
            ChatDTO.builder()
            .id(i.getId())
            .roomId(i.getRoomId())
            .msg(i.getMsg())
            .sender(i.getSender())
            .createdAt(LocalDateTime.now())
            .build()));
    }
    
}
