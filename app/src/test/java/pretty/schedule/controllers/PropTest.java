package pretty.schedule.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PropTest {

  @LocalServerPort
  private int port;

  private final TestRestTemplate restTemplate = new TestRestTemplate();

  @Test
  public void testSource() {
    ResponseEntity<String> response = restTemplate.getForEntity(
        "http://localhost:" + port + "/env/source", String.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertTrue(response.getBody().length() > 0);
    assertNotEquals(null, response.getBody());
  }

  @Test
  public void testTarget() {
    ResponseEntity<String> response = restTemplate.getForEntity(
        "http://localhost:" + port + "/env/target", String.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertTrue(response.getBody().length() > 0);
    assertNotEquals(null, response.getBody());
  }
}
