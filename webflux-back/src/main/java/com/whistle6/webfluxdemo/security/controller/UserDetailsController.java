// package com.whistle6.webfluxdemo.security.controller;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.stereotype.Component;

// import com.whistle6.webfluxdemo.security.service.UserDetailsServiceImpl;

// import lombok.RequiredArgsConstructor;

// @Component
// @RequiredArgsConstructor
// public class UserDetailsController implements AuthenticationProvider {

//     private final UserDetailsServiceImpl userDetailsService;

//     @Value("${jwt.secret}")
//     private String secretKey;

//     @Value("${jwt.access-expired}")
//     private long accessExpire;

//     @Override
//     public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
//     }

//     @Override
//     public boolean supports(Class<?> authentication) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'supports'");
//     }
    
// }
