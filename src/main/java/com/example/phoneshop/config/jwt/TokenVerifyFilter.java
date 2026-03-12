package com.example.phoneshop.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TokenVerifyFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization"); // get header from postman request

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // if this condition true, let it continue next code
            return; // return: to stop this condition when true
        }

        // replace word "Bearer" to get only token, "Bearer" just a word to mark
        String token = authorizationHeader.replace("Bearer ", "");
        String secretKey = "dfbdjdlw6372dhjsbfsnj5678bd63783bfdveywetp539";

        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .parseClaimsJws(token);

        Claims body = claimsJws.getBody();
        String username = body.getSubject();
        List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");

        Set<SimpleGrantedAuthority> grantedAuthoritySet = authorities.stream()
                .map(x -> new SimpleGrantedAuthority(x.get("authority")))
                .collect(Collectors.toSet());

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, grantedAuthoritySet);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}