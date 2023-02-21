package pretty.schedule.scraper;

import java.io.IOException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Request {
    private static final Logger LOGGER = LoggerFactory.getLogger(Request.class);
    private static Cache cache;

    public Request() {
        Request.cache = new Cache();
    }

    public <T> T get(final String url, TypeReference<T> typeref) throws IllegalArgumentException, IOException {
        final var mapper = new ObjectMapper();
        return mapper.convertValue(get(url), typeref);
    }

    public JsonNode get(final String url) throws IOException {
        JsonNode cval = cache.get(url);
        if (cval == null) {
            cval = request(url);
            cache.set(url, cval);
        }
        return cval;
    }

    private static JsonNode request(final String url) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final URL apiUrl = new URL(url);

        LOGGER.info("Request from url: " + apiUrl);

        return mapper.readTree(apiUrl);
    }
}
