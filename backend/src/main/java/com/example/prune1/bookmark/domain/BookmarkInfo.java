// Role: projection legere pour lire un sous-ensemble de champs d'un bookmark.
package com.example.prune1.bookmark.domain;

import java.time.Instant;

public interface BookmarkInfo {
    Long getId();
    String getTitle();
    String getUrl();
    Instant getCreatedAt();
}
