// Role: acces aux donnees bookmarks via Spring Data JPA.
package com.example.prune1.bookmark.infra;

import com.example.prune1.bookmark.domain.Bookmark;
import com.example.prune1.bookmark.domain.BookmarkInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<BookmarkInfo> findAllByOrderByCreatedAtDesc();
}
