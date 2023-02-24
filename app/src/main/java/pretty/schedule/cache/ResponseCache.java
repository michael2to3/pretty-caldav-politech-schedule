package pretty.schedule.cache;

import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.databind.JsonNode;

class ResponseCache {
  private final JsonNode payload;
  private final int delayAsSeconds;
  private final Instant timestamp;

  ResponseCache(JsonNode payload, int delayAsSeconds) {
    this.payload = payload;
    this.delayAsSeconds = delayAsSeconds;
    this.timestamp = Instant.now();
  }

  JsonNode getPayload() {
    if (Instant.now().minusSeconds(delayAsSeconds).isBefore(timestamp)) {
      return payload;
    }
    return null;
  }

  @Override
  public String toString() {
    return payload.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof ResponseCache))
      return false;
    ResponseCache that = (ResponseCache) o;
    return payload.equals(that.payload);
  }

  @Override
  public int hashCode() {
    return Objects.hash(payload);
  }
}
