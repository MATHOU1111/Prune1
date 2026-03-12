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
                    create("Inception", "https://www.imdb.com/title/tt1375666/",
                            "Un voleur qui s'infiltre dans les rêves des autres pour leur dérober des secrets.",
                            "Science-Fiction", "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SX300.jpg",
                            2010, 8.8, now),
                    create("The Dark Knight", "https://www.imdb.com/title/tt0468569/",
                            "Batman affronte le Joker, un criminel anarchiste qui sème le chaos à Gotham City.",
                            "Action", "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_SX300.jpg",
                            2008, 9.0, now),
                    create("Interstellar", "https://www.imdb.com/title/tt0816692/",
                            "Des explorateurs utilisent une faille dans l'espace-temps pour assurer la survie de l'humanité.",
                            "Science-Fiction", "https://m.media-amazon.com/images/M/MV5BZjdkOTU3MDktN2IxOS00OGEyLWFmMjktY2FiMmZkNWIyODZiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg",
                            2014, 8.7, now),
                    create("Pulp Fiction", "https://www.imdb.com/title/tt0110912/",
                            "Les histoires entrelacées de criminels, de gangsters et d'une strip-teaseuse à Los Angeles.",
                            "Crime", "https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg",
                            1994, 8.9, now),
                    create("The Shawshank Redemption", "https://www.imdb.com/title/tt0111161/",
                            "Un banquier innocent trouve espoir et rédemption dans la prison de Shawshank.",
                            "Drame", "https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NiYyLTg3MzMtYTJmNjg3Nzk5NzRhXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg",
                            1994, 9.3, now),
                    create("Fight Club", "https://www.imdb.com/title/tt0137523/",
                            "Un employé insomniaque crée un club de combat clandestin avec un vendeur de savon charismatique.",
                            "Thriller", "https://m.media-amazon.com/images/M/MV5BOTgyOGQ1NDItNGU3Ny00MjU3LTg2YWEtNmEyYjBiMjI1Y2M5XkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_SX300.jpg",
                            1999, 8.8, now),
                    create("The Matrix", "https://www.imdb.com/title/tt0133093/",
                            "Un programmeur découvre que la réalité est une simulation contrôlée par des machines.",
                            "Science-Fiction", "https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVlLTM5YTUtZWU2M2Y5MTY3ZTVlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg",
                            1999, 8.7, now),
                    create("Parasite", "https://www.imdb.com/title/tt6751668/",
                            "Une famille pauvre infiltre la vie d'une famille riche avec des conséquences inattendues.",
                            "Thriller", "https://m.media-amazon.com/images/M/MV5BYWZjMjk3ZTItODQ2ZC00NTY5LWE0ZDYtZTI3MjcwN2Q5NTVkXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_SX300.jpg",
                            2019, 8.5, now),
                    create("Forrest Gump", "https://www.imdb.com/title/tt0109830/",
                            "La vie extraordinaire d'un homme simple de l'Alabama à travers les événements historiques américains.",
                            "Drame", "https://m.media-amazon.com/images/M/MV5BNWIwODRlZTUtY2U3ZS00Yzg1LWJhNzYtMmZiYmEyNmU1NjMzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg",
                            1994, 8.8, now),
                    create("Goodfellas", "https://www.imdb.com/title/tt0099685/",
                            "L'ascension et la chute d'un gangster new-yorkais sur trois décennies.",
                            "Crime", "https://m.media-amazon.com/images/M/MV5BY2NkZjEzMDItZTVmMi00YzE3LWIwNjQtOWRmNzE0ZjVlNTI5L2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg",
                            1990, 8.7, now)
            ));
        };
    }

    private Bookmark create(String title, String url, String description, String genre,
                             String posterUrl, int year, double rating, Instant now) {
        Bookmark b = new Bookmark();
        b.setTitle(title);
        b.setUrl(url);
        b.setDescription(description);
        b.setGenre(genre);
        b.setPosterUrl(posterUrl);
        b.setYear(year);
        b.setRating(rating);
        b.setCreatedAt(now);
        b.setUpdatedAt(now);
        return b;
    }
}
