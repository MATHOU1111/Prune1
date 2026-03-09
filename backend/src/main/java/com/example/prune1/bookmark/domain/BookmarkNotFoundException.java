// Role: exception metier levee quand un bookmark n'existe pas.
package com.example.prune1.bookmark.domain;

public class BookmarkNotFoundException extends RuntimeException {
    public BookmarkNotFoundException(Long id) {
        super("Bookmark %d introuvable".formatted(id));
    }
}
