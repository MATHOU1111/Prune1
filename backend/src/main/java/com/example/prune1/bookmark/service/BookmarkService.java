// Role: centralise la logique metier CRUD des bookmarks.
package com.example.prune1.bookmark.service;

import com.example.prune1.bookmark.api.BookmarkResponse;
import com.example.prune1.bookmark.api.CreateBookmarkRequest;
import com.example.prune1.bookmark.api.UpdateBookmarkRequest;
import com.example.prune1.bookmark.domain.Bookmark;
import com.example.prune1.bookmark.domain.BookmarkNotFoundException;
import com.example.prune1.bookmark.infra.BookmarkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    @Transactional(readOnly = true)
    public List<BookmarkResponse> findAll() {
        return bookmarkRepository.findAll().stream()
                .map(BookmarkResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public BookmarkResponse findById(Long id) {
        return BookmarkResponse.from(getBookmark(id));
    }

    public BookmarkResponse create(CreateBookmarkRequest request) {
        Instant now = Instant.now();
        Bookmark bookmark = new Bookmark();
        bookmark.setTitle(request.title().trim());
        bookmark.setUrl(request.url() != null ? request.url().trim() : null);
        bookmark.setDescription(request.description());
        bookmark.setGenre(request.genre());
        bookmark.setPosterUrl(request.posterUrl());
        bookmark.setYear(request.year());
        bookmark.setRating(request.rating());
        bookmark.setCreatedAt(now);
        bookmark.setUpdatedAt(now);
        return BookmarkResponse.from(bookmarkRepository.save(bookmark));
    }

    public BookmarkResponse update(Long id, UpdateBookmarkRequest request) {
        Bookmark bookmark = getBookmark(id);
        bookmark.setTitle(request.title().trim());
        bookmark.setUrl(request.url() != null ? request.url().trim() : null);
        bookmark.setDescription(request.description());
        bookmark.setGenre(request.genre());
        bookmark.setPosterUrl(request.posterUrl());
        bookmark.setYear(request.year());
        bookmark.setRating(request.rating());
        bookmark.setUpdatedAt(Instant.now());
        return BookmarkResponse.from(bookmarkRepository.save(bookmark));
    }

    public void delete(Long id) {
        if (!bookmarkRepository.existsById(id)) {
            throw new BookmarkNotFoundException(id);
        }
        bookmarkRepository.deleteById(id);
    }

    private Bookmark getBookmark(Long id) {
        return bookmarkRepository.findById(id)
                .orElseThrow(() -> new BookmarkNotFoundException(id));
    }
}
