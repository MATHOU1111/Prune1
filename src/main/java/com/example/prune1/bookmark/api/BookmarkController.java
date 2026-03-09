// Role: expose les endpoints REST CRUD pour les bookmarks.
package com.example.prune1.bookmark.api;

import com.example.prune1.bookmark.service.BookmarkService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @GetMapping
    public List<BookmarkResponse> findAll() {
        return bookmarkService.findAll();
    }

    @GetMapping("/{id}")
    public BookmarkResponse findById(@PathVariable Long id) {
        return bookmarkService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookmarkResponse create(@Valid @RequestBody CreateBookmarkRequest request) {
        return bookmarkService.create(request);
    }

    @PutMapping("/{id}")
    public BookmarkResponse update(@PathVariable Long id, @Valid @RequestBody UpdateBookmarkRequest request) {
        return bookmarkService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookmarkService.delete(id);
    }
}
