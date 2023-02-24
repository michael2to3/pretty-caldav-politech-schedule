package pretty.schedule.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class ResponseCacheTest {

    private ResponseCache responseCache;
    private JsonNode payload;

    @BeforeEach
    void setUp() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"key\": \"value\"}";
        payload = objectMapper.readTree(json);
        responseCache = new ResponseCache(payload, 5);
    }

    @Test
    void testGetPayload() {
        assertEquals(payload, responseCache.getPayload());

        responseCache = new ResponseCache(payload, 0);
        assertNull(responseCache.getPayload());
    }

    @Test
    void testToString() {
        assertEquals(payload.toString(), responseCache.toString());
    }

    @Test
    void testEquals() {
        ResponseCache otherResponseCache = new ResponseCache(payload, 5);
        assertEquals(responseCache, otherResponseCache);
    }

    @Test
    void testHashCode() {
        ResponseCache otherResponseCache = new ResponseCache(payload, 5);
        assertEquals(responseCache.hashCode(), otherResponseCache.hashCode());
    }

}
