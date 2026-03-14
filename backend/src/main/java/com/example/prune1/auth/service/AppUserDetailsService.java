package com.example.prune1.auth.service;

import com.example.prune1.auth.domain.AppUser;
import com.example.prune1.auth.infra.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Role: adapte les utilisateurs de la base au format UserDetails de Spring Security.
@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final AppUserRepository userRepository;

    // Charge l'utilisateur depuis la base et lui assigne un role applicatif standard.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable : " + username));

        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
