package com.whistle6.webfluxdemo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

import org.springframework.security.config.web.server.ServerHttpSecurity;

import org.springframework.security.web.server.SecurityWebFilterChain;


/**
 * Minimal method security configuration.
 *
 * @author Rob Winch
 * @since 5.0
 */
@Configuration
@EnableWebFluxSecurity
public class FluxSecurityConfig {
	@Bean
	SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
		// @formatter:off
		return http
			// Demonstrate that method security works
			// Best practice to use both for defense in depth
			.authorizeExchange((authorize) -> authorize
				.anyExchange().permitAll()	
			)
			.httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
			.formLogin(ServerHttpSecurity.FormLoginSpec::disable)
			.csrf(ServerHttpSecurity.CsrfSpec::disable)
			.cors(ServerHttpSecurity.CorsSpec::disable)
			.build();
		// @formatter:on
	}
}
