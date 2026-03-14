// Role: expose les endpoints REST pour la gestion des bookmarks.
package com.example.prune1.bookmark.api;

import com.example.prune1.bookmark.service.BookmarkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping
    public ResponseEntity<List<BookmarkResponse>> getAllBookmarks() {
        return ResponseEntity.ok(bookmarkService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookmarkResponse> getBookmarkById(@PathVariable Long id) {
        return ResponseEntity.ok(bookmarkService.findById(id));
    }

    @PostMapping
    public ResponseEntity<BookmarkResponse> createBookmark(@Valid @RequestBody CreateBookmarkRequest request) {
        BookmarkResponse created = bookmarkService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookmarkResponse> updateBookmark(
            @PathVariable Long id,
            @Valid @RequestBody UpdateBookmarkRequest request) {
        return ResponseEntity.ok(bookmarkService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookmark(@PathVariable Long id) {
        bookmarkService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
