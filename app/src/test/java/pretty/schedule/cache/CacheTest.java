package pretty.schedule.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CacheTest {
  @Test
  public void testCacheSetAndGet() throws Exception {
    Cache cache = new Cache();
    cache.setDelayAsSeconds(1);
    ObjectMapper mapper = new ObjectMapper();
    JsonNode value = mapper.readTree("{\"key\": \"value\"}");

    cache.set("test", value);
    assertEquals(value, cache.get("test"));

    assertNotNull(cache.get("test"));
    Thread.sleep(cache.getDelayAsSeconds() * 1000);
    assertNull(cache.get("test"));
  }
}
