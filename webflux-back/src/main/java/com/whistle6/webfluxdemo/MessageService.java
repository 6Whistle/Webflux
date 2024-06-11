// package com.whistle6.webfluxdemo;

// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.stereotype.Component;

// import reactor.core.publisher.Mono;

// @Component
// public class MessageService {

// 	/**
// 	 * Gets a message if authenticated.
// 	 * @return the message
// 	 */
// 	@PreAuthorize("authenticated")
// 	public Mono<String> findMessage() {
// 		return Mono.just("Hello User!");
// 	}

// 	/**
// 	 * Gets a message if admin.
// 	 * @return the message
// 	 */
// 	@PreAuthorize("hasRole('ADMIN')")
// 	public Mono<String> findSecretMessage() {
// 		return Mono.just("Hello Admin!");
// 	}

// }