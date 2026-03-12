// Role: DTO de sortie renvoye par l'API pour un bookmark.
package com.example.prune1.bookmark.api;

import com.example.prune1.bookmark.domain.Bookmark;

import java.time.Instant;

public record BookmarkResponse(
        Long id,
        String title,
        String url,
        String description,
        String genre,
        String posterUrl,
        Integer year,
        Double rating,
        Instant createdAt,
        Instant updatedAt
) {
    public static BookmarkResponse from(Bookmark bookmark) {
        return new BookmarkResponse(
                bookmark.getId(),
                bookmark.getTitle(),
                bookmark.getUrl(),
                bookmark.getDescription(),
                bookmark.getGenre(),
                bookmark.getPosterUrl(),
                bookmark.getYear(),
                bookmark.getRating(),
                bookmark.getCreatedAt(),
                bookmark.getUpdatedAt()
        );
    }
}
