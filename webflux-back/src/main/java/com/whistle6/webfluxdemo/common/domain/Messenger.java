package com.whistle6.webfluxdemo.common.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Messenger {
    private Object data;
    private String accessToken;
    private String refreshToken; 
}
