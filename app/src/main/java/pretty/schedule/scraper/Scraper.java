package pretty.schedule.scraper;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pretty.schedule.scheme.Faculty;
import pretty.schedule.scheme.Group;
import pretty.schedule.scheme.ScheduleOfWeek;

public class Scraper {
  private final String url;
  private final Request request;

  public Scraper(final String url) {
    this.url = url;
    this.request = new Request();
  }

  public List<ScheduleOfWeek> getRangeScheduleById(
      final String groupId,
      final Instant start,
      final Instant end) throws IOException {

    final Instant monday = RangeOfDate.getPreviousDate(start, DayOfWeek.MONDAY);
    final List<Instant> dates = RangeOfDate.getListDateOfRange(monday, end);

    final List<ScheduleOfWeek> schedules = new ArrayList<>();
    for (final Instant date : dates) {
      schedules.add(getScheduleOfWeek(groupId, date));
    }
    return schedules;
  }

  public List<Faculty> getFaculties() throws IOException {
    final String formatUrl = FormatUrl.getFaculties(url);
    TypeReference<Map<String, List<Faculty>>> typeRef = new TypeReference<Map<String, List<Faculty>>>() {
    };
    final var faculties = request.get(formatUrl, typeRef);
    List<Faculty> f = faculties.values().stream().flatMap(List::stream).collect(Collectors.toList());
    return f;
  }

  public List<Group> getGroups(final String facultId) throws IOException {
    final String formatUrl = FormatUrl.getGroups(url, facultId);
    TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {
    };
    final var response = request.get(formatUrl, typeRef);
    final ObjectMapper mapper = new ObjectMapper();
    List<Group> groupList = new ArrayList<>();
    if (response.containsKey("groups")) {
      TypeReference<List<Map<String, Object>>> tr = new TypeReference<List<Map<String, Object>>>() {
      };
      var groupMaps = mapper.convertValue(response.get("groups"), tr);
      for (var group : groupMaps) {
        var groupObj = mapper.convertValue(group, Group.class);
        groupList.add(groupObj);
      }
    }
    return groupList;
  }

  public Group getGroupByName(final String name) throws IOException {
    final List<Faculty> faculties = getFaculties();
    for (final var faculty : faculties) {
      final Group group = getGroup(Integer.toString(faculty.getId()), name);
      if (group != null) {
        return group;
      }
    }
    throw new IllegalArgumentException("Group not found");
  }

  public List<ScheduleOfWeek> getRangeScheduleByName(
      final String name,
      final Instant start,
      final Instant end) throws IOException {

    final Group group = getGroupByName(name);
    final String id = Integer.toString(group.getId());

    return getRangeScheduleById(id, start, end);
  }

  private ScheduleOfWeek getScheduleOfWeek(
      final String groupId,
      final Instant date) throws IOException {

    final String formatUrl = FormatUrl.getSchedule(url, groupId, date);
    return request.get(formatUrl, new TypeReference<ScheduleOfWeek>() {
    });
  }

  private Group getGroup(final String facultId, final String name) throws IOException {
    final List<Group> groups = getGroups(facultId);
    for (final var group : groups) {
      if (group.getName().equals(name)) {
        return group;
      }
    }
    return null;
  }
}
