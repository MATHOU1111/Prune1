// Role: initialise quelques bookmarks de demo au demarrage.
package com.example.prune1.bookmark.infra;

import com.example.prune1.bookmark.domain.Bookmark;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.List;

@Configuration
public class BookmarkDataInitializer {

    @Bean
    CommandLineRunner seedBookmarks(BookmarkRepository repository) {
        return args -> {
            if (repository.count() > 0) {
                return;
            }

            Instant now = Instant.now();
            repository.saveAll(List.of(
                    create("film 1", "https://blog.jetbrains.com", now),
                    create("film 2", "https://start.spring.io", now),
                    create("film 3", "https://docs.spring.io", now)
            ));
        };
    }

    private Bookmark create(String title, String url, Instant now) {
        Bookmark bookmark = new Bookmark();
        bookmark.setTitle(title);
        bookmark.setUrl(url);
        bookmark.setCreatedAt(now);
        bookmark.setUpdatedAt(now);
        return bookmark;
    }
}
