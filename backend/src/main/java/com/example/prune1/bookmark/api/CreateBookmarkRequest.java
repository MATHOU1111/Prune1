// Role: DTO d'entree pour creer un bookmark via l'API.
package com.example.prune1.bookmark.api;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record CreateBookmarkRequest(
        @NotBlank(message = "Le titre est obligatoire")
        String title,
        String url,
        String description,
        String genre,
        String posterUrl,
        Integer year,
        Double rating
) {
}
