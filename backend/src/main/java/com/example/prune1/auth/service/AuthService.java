package com.example.prune1.auth.service;

import com.example.prune1.auth.api.AuthRequest;
import com.example.prune1.auth.api.AuthResponse;
import com.example.prune1.auth.domain.AppUser;
import com.example.prune1.auth.infra.AppUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(AppUserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(AuthRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new IllegalArgumentException("Ce nom d'utilisateur est déjà pris.");
        }
        AppUser user = new AppUser();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token, user.getUsername());
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        String token = jwtService.generateToken(request.username());
        return new AuthResponse(token, request.username());
    }
}

