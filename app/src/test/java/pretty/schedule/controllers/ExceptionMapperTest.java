package pretty.schedule.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import pretty.schedule.scheme.ErrorResponse;

class ExceptionMapperTest {

  private ExceptionMapper exceptionMapper;

  @BeforeEach
  public void setUp() {
    exceptionMapper = new ExceptionMapper();
  }

  @Test
  public void testHandleExceptionDebugTrue() {
    exceptionMapper.setDebug(true);
    RuntimeException ex = new RuntimeException("Test exception");
    WebRequest request = mock(WebRequest.class);

    ResponseEntity<Object> response = exceptionMapper.handleException(ex, request);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    ErrorResponse errorResponse = (ErrorResponse) response.getBody();
    assertEquals("Test exception", errorResponse.getMessage());
    assertNotNull(errorResponse.getDetails());
  }

  @Test
  public void testHandleExceptionDebugFalse() {
    exceptionMapper.setDebug(false);
    RuntimeException ex = new RuntimeException("Test exception");
    WebRequest request = mock(WebRequest.class);

    ResponseEntity<Object> response = exceptionMapper.handleException(ex, request);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    ErrorResponse errorResponse = (ErrorResponse) response.getBody();
    assertEquals("An error occurred", errorResponse.getMessage());
    assertNull(errorResponse.getDetails());
  }
}
