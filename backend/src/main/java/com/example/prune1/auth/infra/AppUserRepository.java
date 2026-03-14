// Role: acces base de donnees pour les comptes utilisateurs.
package com.example.prune1.auth.infra;

import com.example.prune1.auth.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    // Charge un utilisateur pour le processus de login/JWT.
    Optional<AppUser> findByUsername(String username);

    // Verifie l'unicite avant l'inscription.
    boolean existsByUsername(String username);
}
