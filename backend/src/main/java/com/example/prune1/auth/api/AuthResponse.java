package com.example.prune1.auth.api;

/// DTO de sortie d'authentification.
public record AuthResponse(
        // JWT signe a envoyer dans le header Authorization.
        String token,
        // Username renvoye pour afficher l'utilisateur connecte cote front.
        String username
) {}
