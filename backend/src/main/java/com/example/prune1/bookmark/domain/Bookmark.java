// Role: entite JPA representant un bookmark en base de donnees.
package com.example.prune1.bookmark.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "bookmarks")
@Getter
@Setter
@NoArgsConstructor
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String title;

    @Column(nullable = false, length = 2048)
    private String url;

    @Column(length = 1000)
    private String description;

    @Column(length = 80)
    private String genre;

    @Column(length = 2048)
    private String posterUrl;

    private Integer year;

    private Double rating;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;
}
