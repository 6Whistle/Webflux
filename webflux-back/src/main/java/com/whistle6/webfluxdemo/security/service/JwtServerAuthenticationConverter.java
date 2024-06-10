// package com.whistle6.webfluxdemo.security.service;

// import java.util.List;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
// import org.springframework.stereotype.Component;
// import org.springframework.web.server.ServerWebExchange;

// import lombok.RequiredArgsConstructor;
// import reactor.core.publisher.Mono;

// @Component
// @RequiredArgsConstructor
// class JwtServerAuthenticationConverter implements ServerAuthenticationConverter {

//     private final TokenProviderImpl tokenProvider;
//     private static final String BEARER = "Bearer ";

//     @Override
//     public Mono<Authentication> convert(ServerWebExchange exchange) {
//         return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
//                 .filter(header -> header.startsWith(BEARER))
//                 .map(header -> header.substring(BEARER.length()))
//                 .map(token -> new JwtToken(token, createUserDetails(token)));
//     }

//     private UserDetails createUserDetails(String token) {
//         String username = tokenProvider.extractUsername(token);
//         return User.builder()
//                 .username(username)
//                 .authorities(createAuthorities(token))
//                 .password("")
//                 .build();
//     }

//     private List<SimpleGrantedAuthority> createAuthorities(String token) {
//         return tokenProvider.extractRoles(token).stream()
//                 .map(role -> "ROLE_" + role)
//                 .map(SimpleGrantedAuthority::new)
//                 .toList();
//     }
// }