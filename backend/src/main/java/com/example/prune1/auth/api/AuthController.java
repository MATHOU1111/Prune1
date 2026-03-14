// Role: expose les endpoints publics d'authentification (inscription et connexion).
package com.example.prune1.auth.api;

import com.example.prune1.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // Cree un utilisateur puis renvoie un JWT pour eviter une connexion immediate supplementaire.
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    // Verifie les identifiants et renvoie un JWT a utiliser dans Authorization: Bearer <token>.
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
