package com.whistle6.webfluxdemo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserDetailsConfig {
    // @Bean
    // PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

    //  @Bean
    //  public MapReactiveUserDetailsService userDetailsService() {
    //      UserDetails user = User.builder()
    //          .username("user")
    //          .password("password")
    //          .roles("USER")
    //          .build();
    //      return new MapReactiveUserDetailsService(user);
    //  }
}
