package com.example.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {
        try {
            // 1. Authenticate using Spring Security
            // This automatically uses your CustomUserDetailsService and PasswordEncoder
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), 
                            request.getPassword()
                    )
            );

            // 2. Verify ADMIN role
            boolean isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

            if (!isAdmin) {
                throw new ResponseStatusException(
                        HttpStatus.FORBIDDEN, 
                        "Solo los administradores pueden acceder a este sistema"
                );
            }

            // 3. Generate token
            String token = jwtService.generateToken(request.getUsername());

            return new LoginResponse(token, "ADMIN");

        } catch (AuthenticationException e) {
            // 4. Handle wrong username or password
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, 
                    "Credenciales incorrectas"
            );
        }
    }
}