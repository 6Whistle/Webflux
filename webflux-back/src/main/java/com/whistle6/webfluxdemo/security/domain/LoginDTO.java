package com.whistle6.webfluxdemo.security.domain;

import lombok.Data;
@Data
public class LoginDTO {
    private String email;
    private String password;
}
