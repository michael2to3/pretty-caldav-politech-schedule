package pretty.schedule.scraper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pretty.schedule.scheme.Faculty;
import pretty.schedule.scheme.Group;
import pretty.schedule.scheme.ScheduleOfWeek;

public class Scraper {
  private final String url;
  private static final Request request = new Request();
  private static final int STEP_OF_RANGE = 7;
  private static final Logger LOGGER = LoggerFactory.getLogger(Scraper.class);

  public Scraper(final String url) {
    this.url = url;
  }

  private List<Instant> getListDateOfRange(final Instant startDate, final Instant endDate) {
    final List<Instant> list = new ArrayList<>();
    Instant current = startDate;
    while (!current.isAfter(endDate)) {
      list.add(current);
      current = current.plus(Duration.ofDays(STEP_OF_RANGE));
    }
    return list;
  }

  public List<ScheduleOfWeek> getRangeScheduleOfWeek(
      final String numGroup, final Instant startDate, final Instant endDate) throws IOException {
    final ZonedDateTime zone = startDate.atZone(ZoneId.systemDefault());
    final ZonedDateTime prevMonday = zone.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
    final List<Instant> dates = getListDateOfRange(prevMonday.toInstant(), endDate);
    final List<ScheduleOfWeek> schedules = new ArrayList<>();
    for (final Instant date : dates) {
      schedules.add(getScheduleOfWeek(numGroup, date));
    }
    return schedules;
  }

  public ScheduleOfWeek getScheduleOfWeek(final String numGroup, final Instant date)
      throws IOException {
    final String nurl = FormatUrl.getSchedule(url, numGroup, date);
    return request.get(nurl, new TypeReference<ScheduleOfWeek>() {
    });
  }

  public List<Faculty> getFaculties() throws IOException {
    final String nurl = FormatUrl.getFaculties(url);
    TypeReference<Map<String, List<Faculty>>> typeRef = new TypeReference<Map<String, List<Faculty>>>() {
    };
    final var faculties = request.get(nurl, typeRef);
    List<Faculty> f = faculties.values().stream().flatMap(List::stream).collect(Collectors.toList());
    return f;
  }

  public List<Group> getGroups(final String num) throws IOException {
    final String nurl = FormatUrl.getGroups(url, num);
    TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {
    };
    final ObjectMapper mapper = new ObjectMapper();
    final var response = request.get(nurl, typeRef);
    final var f = ((List<Map<String, Object>>) response.get("groups"))
        .stream()
        .map(group -> mapper.convertValue(group, Group.class))
        .collect(Collectors.toList());
    return f;
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

  public List<ScheduleOfWeek> getRangeScheduleOfName(
      final String name, final Instant start, final Instant end) throws IOException {
    final Group group = getGroupOfName(name);
    List<ScheduleOfWeek> list = getRangeScheduleOfWeek(Integer.toString(group.getId()), start, end);
    return list;
  }
}
