// Role: entite JPA representant un compte applicatif.
package com.example.prune1.auth.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "app_users")
@Getter
@Setter
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Username unique utilise comme identifiant de connexion.
    @Column(nullable = false, unique = true, length = 100)
    private String username;

    // Mot de passe deja hash (BCrypt), jamais stocke en clair.
    @Column(nullable = false)
    private String password;
}
