package com.example.phoneshop.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

@RequiredArgsConstructor
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    /*
        JWT has three parts: header (algorithm type), claim (body) and signature
     */

    private final AuthenticationManager authenticationManager;

    // step 1: check if login username and password correct
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            LoginRequest loginRequest = mapper.readValue(request.getInputStream(), LoginRequest.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
            Authentication authenticate = authenticationManager.authenticate(authentication);
            return authenticate;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // step 2: if above correct -> generate token for user
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String secretKey = "dfbdjdlw6372dhjsbfsnj5678bd63783bfdveywetp539";

        String token = Jwts.builder()
                .setSubject(authResult.getName()) // who send
                .setIssuedAt(new Date())
                .claim("authorities", authResult.getAuthorities()) // claim
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(7)))
                .setIssuer("phoneshop.com")
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))  // signature
                .compact();

        response.setHeader("Authorization", "Bearer " + token);
    }
}
