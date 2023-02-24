package pretty.schedule.cache;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.HashMap;
import java.util.Map;

public class Cache {
  private int delayAsSeconds;
  private Map<String, ResponseCache> map;

  public Cache() {
    this.map = new HashMap<String, ResponseCache>();
    this.delayAsSeconds = 10 * 60;
  }

  public Map<String, ResponseCache> getMap() {
    return map;
  }

  public void setMap(Map<String, ResponseCache> map) {
    this.map = map;
  }

  public JsonNode get(final String name) {
    if (map.containsKey(name)) {
      return map.get(name).getPayload();
    }
    return null;
  }

  public void set(final String name, final JsonNode value) {
    ResponseCache cvalue = new ResponseCache(value, delayAsSeconds);
    set(name, cvalue);
  }

  private void set(final String name, final ResponseCache value) {
    if (map.containsKey(name)) {
      map.replace(name, value);
    } else {
      map.put(name, value);
    }
  }

  public int getDelayAsSeconds() {
    return delayAsSeconds;
  }

  public void setDelayAsSeconds(int delayAsSeconds) {
    this.delayAsSeconds = delayAsSeconds;
  }
}
