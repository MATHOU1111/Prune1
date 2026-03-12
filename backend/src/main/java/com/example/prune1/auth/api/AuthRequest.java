package com.example.prune1.auth.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequest(
        @NotBlank(message = "Le nom d'utilisateur est obligatoire")
        @Size(min = 3, max = 50)
        String username,

        @NotBlank(message = "Le mot de passe est obligatoire")
        @Size(min = 6, max = 100)
        String password
) {}

