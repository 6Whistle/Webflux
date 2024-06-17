package com.whistle6.webfluxdemo.common.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.whistle6.webfluxdemo.chat.domain.model.ChatModel;
import com.whistle6.webfluxdemo.user.domain.RoleName;
import com.whistle6.webfluxdemo.user.domain.UserModel;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class ReactiveMongoConfig {
    private final ReactiveMongoTemplate mongoTemplate;

    @Bean
    public CommandLineRunner commandLineRunner() {
        mongoTemplate.getCollectionNames()
        .flatMap(collectionName -> mongoTemplate.dropCollection(collectionName))
        .log()
        .collectList()
        // .flatMap(i -> mongoTemplate.createCollection(UserModel.class, CollectionOptions.empty().capped().size(1024).maxDocuments(100)))
        .flatMap(i -> mongoTemplate.createCollection(ChatModel.class, CollectionOptions.empty().capped().size(1024).maxDocuments(100)))
        .flatMap(i -> mongoTemplate.insert(UserModel.builder()
                .email("admin@admin")
                .firstName("Admin")
                .lastName("Admin")
                .password("admin")
                .roles(List.of(RoleName.SUPERADMIN, RoleName.ADMIN, RoleName.USER))
                .build()))
        .subscribe();


        return args -> {
            System.out.println("MongoDB Initiated!");
        };
    }
}