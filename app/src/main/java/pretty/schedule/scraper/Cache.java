package pretty.schedule.scraper;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public class Cache {
    private Map<String, JsonNode> map;

    public Cache() {
        this.map = new HashMap<String, JsonNode>();
    }

    public void set(final String name, final JsonNode value) {
        if (map.containsKey(name)) {
            map.replace(name, value);
        } else {
            map.put(name, value);
        }
    }

    public JsonNode get(final String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }
        return null;
    }
}
