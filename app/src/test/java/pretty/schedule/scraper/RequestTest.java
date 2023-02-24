package pretty.schedule.scraper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;

public class RequestTest {
    private static Request request;

    @BeforeAll
    static void setUp() {
        request = new Request();
    }

    @Test
    @DisplayName("Request valid URL returns JSON")
    void testRequestValidURL() throws IOException {
        String url = "https://jsonplaceholder.typicode.com/todos/1";
        String expectedTitle = "delectus aut autem";

        JsonNode result = request.get(url);

        assertNotNull(result);
        assertEquals(expectedTitle, result.get("title").asText());
    }

    @Test
    @DisplayName("Request invalid URL throws IOException")
    void testRequestInvalidURL() {
        String url = "https://jsonplaceholder.typicode.com/invalid";

        assertThrows(IOException.class, () -> {
            request.get(url);
        });
    }

    @Test
    @DisplayName("Cache hit returns cached value")
    void testCacheHit() throws IOException {
        String url = "https://jsonplaceholder.typicode.com/todos/1";
        JsonNode expected = request.get(url);

        JsonNode result = request.get(url);

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Cache miss makes a request and caches the result")
    void testCacheMiss() throws IOException {
        String url = "https://jsonplaceholder.typicode.com/todos/2";
        JsonNode result = request.get(url);
        assertNotNull(result);
    }
}
