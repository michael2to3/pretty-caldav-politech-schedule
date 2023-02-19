package pretty.schedule.scraper;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pretty.schedule.scheme.Faculty;
import pretty.schedule.scheme.Group;
import pretty.schedule.scheme.ScheduleOfWeek;

public class Scraper {
    private final String url;

    public Scraper(final String url) {
        this.url = url;
    }

    private List<Instant> getListDateOfRange(final Instant startDate, final Instant endDate) {
        List<Instant> list = new ArrayList<>();
        Instant current = startDate;
        while (!current.isAfter(endDate)) {
            list.add(current);
            current = current.plus(Duration.ofDays(7));
        }
        return list;
    }

    public List<ScheduleOfWeek> getRangeScheduleOfWeek(final String numGroup, final Instant startDate,
            final Instant endDate) throws JsonParseException, JsonMappingException, IOException {
        ZonedDateTime zone = startDate.atZone(ZoneId.systemDefault());
        ZonedDateTime prevMonday = zone.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        List<Instant> dates = getListDateOfRange(prevMonday.toInstant(), endDate);
        List<ScheduleOfWeek> sch = new ArrayList<>();
        for (Instant date : dates) {
            sch.add(getScheduleOfWeek(numGroup, date));
        }
        return sch;

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

    public Group getGroup(final String idFacult, final String name)
            throws JsonParseException, JsonMappingException, IOException {
        List<Group> groups = getGroups(idFacult);
        for (var group : groups) {
            if (group.getName().equals(name)) {
                return group;
            }
        }
        return null;
    }
}
