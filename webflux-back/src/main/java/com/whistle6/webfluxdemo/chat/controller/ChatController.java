package com.whistle6.webfluxdemo.chat.controller;

import org.springframework.web.bind.annotation.RestController;

import com.whistle6.webfluxdemo.chat.domain.dto.ChatDTO;
import com.whistle6.webfluxdemo.chat.domain.model.ChatModel;
import com.whistle6.webfluxdemo.chat.service.ChatService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    // private Sinks.Many<ChatDTO> chatSink = Sinks.many().multicast().onBackpressureBuffer();

    @GetMapping(path = "/receive/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<ChatDTO>> receiveByRoomId(@PathVariable String id) {
        return chatService.receiveByRoomId(id).map(chat -> ServerSentEvent.builder(chat).build()).subscribeOn(Schedulers.boundedElastic());
        // return chatSink.asFlux().map(chats -> ServerSentEvent.builder(chats).build()).doOnCancel(() -> chatSink.asFlux().blockLast());
    }
    
    @PostMapping("/send")
    public Mono<ChatDTO> send(@RequestBody ChatModel entity) {
        return chatService.save(entity);
        // return chatService.save(entity).doOnNext(chatSink::tryEmitNext);
    }
    
}
