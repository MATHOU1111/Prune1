// Role: porte la logique metier d'authentification (register/login).
package com.example.prune1.auth.service;

import com.example.prune1.auth.api.AuthRequest;
import com.example.prune1.auth.api.AuthResponse;
import com.example.prune1.auth.domain.AppUser;
import com.example.prune1.auth.infra.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // Inscrit un nouvel utilisateur, hash son mot de passe, puis renvoie directement un JWT.
    public AuthResponse register(AuthRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new IllegalArgumentException("Ce nom d'utilisateur est deja pris.");
        }
        AppUser user = new AppUser();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);

        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token, user.getUsername());
    }

    // Verifie les identifiants via Spring Security, puis genere le JWT si tout est valide.
    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        String token = jwtService.generateToken(request.username());
        return new AuthResponse(token, request.username());
    }
}
