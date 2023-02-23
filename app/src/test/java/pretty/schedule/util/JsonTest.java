package pretty.schedule.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import pretty.schedule.scheme.ErrorResponse;

class JsonTest {
  @Test
  void convertString() {
    ErrorResponse errorResponse = new ErrorResponse("error", "message");
    String json = Json.convertString(errorResponse);
    String expected = "{\"status\":\"error\",\"message\":\"message\"}";
    assertEquals(expected, json);
  }

  @Test
  void inputNull() {
    assertNull(Json.convertString(null));
  }

  @Test
  void jsonProcessingException() {
    Object invalidObject = new Object() {
      @Override
      public String toString() {
        return "invalidObject";
      }
    };

    String result = Json.convertString(invalidObject);
    assertEquals(result, null);
  }
}
