package com.whistle6.webfluxdemo.security.service;

import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.whistle6.webfluxdemo.security.domain.TokenModel;
import com.whistle6.webfluxdemo.security.repository.TokenRepository;
import com.whistle6.webfluxdemo.user.domain.UserModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expired.access}")
    private long accessTokenExpired;

    @Value("${jwt.expired.refresh}")
    private long refreshTokenExpired;

    private SecretKey getSigningKey() {
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String createRefreshToken(UserModel user){
        Mono<String> token =  tokenRepository.save(TokenModel.builder()
        .email(user.getEmail())
        .refreshToken(generateToken(user, true))
        .expiredAt(Instant.now().plusSeconds(refreshTokenExpired))
        .build())
        .map(i -> i.getRefreshToken());
        
        token.subscribe();
        return token.block();
    }

    public String generateToken(UserModel user, Boolean isRefreshToken){
        return Jwts.builder()
        .issuer("toeicdoit.site")
        .subject(isRefreshToken ? "refresh" : "access")
        .issuedAt(Date.from(Instant.now()))
        .expiration(Date.from(Instant.now().plusSeconds(isRefreshToken ? refreshTokenExpired : accessTokenExpired)))
        .claim("email", user.getEmail())
        .signWith(getSigningKey(), Jwts.SIG.HS256)
        .compact();
    }

    // String extractUsername(String jwt){
    //     return extractClaim(jwt, Claims::getSubject);
    // }



    // @SuppressWarnings("unchecked")
    // List<String> extractRoles(String jwt){
    //     return extractClaim(jwt, claims -> (List<String>) claims.get("roles"));
    // }

    // @Override
    // public String generateToken(UserDetails userDetails) {
    //     return generateToken(Map.of(), userDetails);
    // }

    // @Override
    // public boolean isTokenValid(String jwt){
    //     return !isTokenExpired(jwt);
    // }

    // private boolean isTokenExpired(String jwt){
    //     return extractClaim(jwt, Claims::getExpiration).before(new Date());
    // }

    // private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    //     long currentTimeMillis = System.currentTimeMillis();
    //     return Jwts.builder()
    //             .claims(extraClaims)
    //             .subject(userDetails.getUsername())
    //             .claim("roles", userDetails.getAuthorities().stream()
    //                     .map(GrantedAuthority::getAuthority)
    //                     .map(role -> role.substring("ROLE_".length()))
    //                     .toArray())
    //             .issuedAt(new Date(currentTimeMillis))
    //             .expiration(new Date(currentTimeMillis + tokenExpiration * 1000))
    //             .signWith(getSigningKey(), Jwts.SIG.HS256)
    //             .compact();
    // }

    // private <T> T extractClaim(String jwt, Function<Claims, T> claimResolver){
    //     Claims claims = extractAllClaims(jwt);
    //     return claimResolver.apply(claims);
    // }

    // private Claims extractAllClaims(String jwt){
    //     try {
    //         return Jwts.parser()
    //                 .verifyWith(getSigningKey())
    //                 .build()
    //                 .parseSignedClaims(jwt)
    //                 .getPayload();
    //     } catch (JwtException e) {
    //         throw new JwtAuthenticationException(e.getMessage());
    //     }
    // }




}