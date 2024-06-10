// package com.whistle6.webfluxdemo.security.filter;

// import java.io.IOException;

// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import com.whistle6.webfluxdemo.security.domain.UserDetailsModel;
// import com.whistle6.webfluxdemo.security.service.TokenProviderImpl;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @RequiredArgsConstructor
// public class CustomRequestFilter extends OncePerRequestFilter {
//     private  final TokenProviderImpl tokenProvider ;
//   private final UserDetailsService userDetailsService ;
//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain)
//                                     throws ServletException, IOException {
//         String token = tokenProvider.getToken(request) ;
//         if (token!=null && tokenProvider.validateToken(token))
//         {
//             String email = tokenProvider.extractUsername(token);
//             UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//             if (userDetails != null) {
//             UsernamePasswordAuthenticationToken authentication =
//                     new UsernamePasswordAuthenticationToken(userDetails.getUsername() ,
//                     null , userDetails.getAuthorities());
//                 log.info("authenticated user with email :{}", email);
//             SecurityContextHolder.getContext().setAuthentication(authentication);
//         }
//         }
//             filterChain.doFilter(request,response);
//     }
// }
