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

import com.fasterxml.jackson.core.type.TypeReference;
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
        final List<Instant> list = new ArrayList<>();
        Instant current = startDate;
        while (!current.isAfter(endDate)) {
            list.add(current);
            current = current.plus(Duration.ofDays(7));
        }
        return list;
    }

    public List<ScheduleOfWeek> getRangeScheduleOfWeek(final String numGroup, final Instant startDate,
            final Instant endDate)
            throws IOException {
        final ZonedDateTime zone = startDate.atZone(ZoneId.systemDefault());
        final ZonedDateTime prevMonday = zone.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        final List<Instant> dates = getListDateOfRange(prevMonday.toInstant(), endDate);
        final List<ScheduleOfWeek> schedules = new ArrayList<>();
        for (final Instant date : dates) {
            schedules.add(getScheduleOfWeek(numGroup, date));
        }
        return schedules;
    }

    public ScheduleOfWeek getScheduleOfWeek(final String numGroup, final Instant date) throws IOException {
        final String nurl = FormatUrl.getSchedule(url, numGroup, date);
        return request(nurl, new TypeReference<ScheduleOfWeek>() {
        });
    }

    public List<Faculty> getFaculties() throws IOException {
        final String nurl = FormatUrl.getFaculties(url);
        return request(nurl, new TypeReference<List<Faculty>>() {
        });
    }

    public List<Group> getGroups(final String num) throws IOException {
        final String nurl = FormatUrl.getGroups(url, num);
        return request(nurl, new TypeReference<List<Group>>() {
        });
    }

    private <T> T request(final String url, final TypeReference<T> type) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final URL apiUrl = new URL(url);
        return mapper.readValue(apiUrl, type);
    }

    public Group getGroup(final String idFacult, final String name) throws IOException {
        final List<Group> groups = getGroups(idFacult);
        for (final var group : groups) {
            if (group.getName().equals(name)) {
                return group;
            }
        }
        return null;
    }

    public Group getGroupOfName(final String name) throws IOException {
        final List<Faculty> faculties = getFaculties();
        for (final var faculty : faculties) {
            final Group group = getGroup(Integer.toString(faculty.getId()), name);
            if (group != null) {
                return group;
            }
        }
        return null;
    }
}
