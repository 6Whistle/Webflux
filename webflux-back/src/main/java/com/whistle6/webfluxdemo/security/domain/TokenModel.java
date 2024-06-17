package com.whistle6.webfluxdemo.security.domain;

import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("tokens")
public class TokenModel {
    private String id;
    private String email;
    private String refreshToken;
    private Instant expiredAt;
}
