package com.whistle6.webfluxdemo.user.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "password")
@Builder
@Document
public class UserModel {
    @Id
    private String id;
    
    @Indexed(unique = true)
    private String email;

    private String password;

    private String firstName;
    private String lastName;

    private List<RoleName> roles;
}