package pretty.schedule.Scraper;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pretty.schedule.Type.Faculty;
import pretty.schedule.Type.Group;
import pretty.schedule.Type.ScheduleOfWeek;

public class Scraper {
    private final String url;

    public Scraper(final String url) {
        this.url = url;
    }

    public ScheduleOfWeek getScheduleOfWeek(final String numGroup, final Instant date)
            throws JsonParseException, JsonMappingException, IOException {
        String nurl = FormatUrl.getSchedule(url, numGroup, date);
        return request(nurl, ScheduleOfWeek.class);
    }

    public List<Faculty> getFacultets() throws JsonParseException, JsonMappingException, IOException {
        String nurl = FormatUrl.getFaculties(url);
        return request(nurl, new ArrayList<Faculty>() {
        }.getClass());
    }

    public List<Group> getGroups(final String num) throws JsonParseException, JsonMappingException, IOException {
        String nurl = FormatUrl.getGroups(url, num);
        return request(nurl, new ArrayList<Group>() {
        }.getClass());
    }

    private <T> T request(final String url, Class<T> type)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        URL apiUrl = new URL(url);
        return mapper.readValue(apiUrl, type);
    }
}
