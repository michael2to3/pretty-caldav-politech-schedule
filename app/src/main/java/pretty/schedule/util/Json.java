package pretty.schedule.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {
  private static final Logger LOGGER = LoggerFactory.getLogger(Json.class);

  public static String convertString(Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      LOGGER.error(e.toString());
      return null;
    }
  }
}
