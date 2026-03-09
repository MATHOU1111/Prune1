package com.example.prune1;

import com.example.prune1.bookmark.infra.BookmarkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Prune1ApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @BeforeEach
    void cleanDb() {
        bookmarkRepository.deleteAll();
    }

    @Test
    void createAndDeleteBookmark() {
        Map<String, String> payload = Map.of(
                "title", "JetBrains Blog",
                "url", "https://blog.jetbrains.com"
        );

        ResponseEntity<Map> createResponse = restTemplate.postForEntity("/api/bookmarks", payload, Map.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createResponse.getBody()).containsEntry("title", "JetBrains Blog");

        Number id = (Number) createResponse.getBody().get("id");
        ResponseEntity<Void> deleteResponse = restTemplate.exchange(
                "/api/bookmarks/" + id.longValue(),
                HttpMethod.DELETE,
                null,
                Void.class
        );

        assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void rejectInvalidPayload() {
        String payload = """
                {
                  "title": "",
                  "url": "not-a-url"
                }
                """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(payload, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity("/api/bookmarks", request, Map.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).containsKeys("title", "url");
    }
}
