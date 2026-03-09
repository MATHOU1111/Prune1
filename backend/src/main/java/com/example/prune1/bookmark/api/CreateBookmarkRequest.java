// Role: DTO d'entree pour creer un bookmark via l'API.
package com.example.prune1.bookmark.api;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record CreateBookmarkRequest(
        @NotBlank(message = "Le titre est obligatoire")
        String title,
        @NotBlank(message = "L'URL est obligatoire")
        @URL(message = "L'URL doit etre valide")
        String url
) {
}
