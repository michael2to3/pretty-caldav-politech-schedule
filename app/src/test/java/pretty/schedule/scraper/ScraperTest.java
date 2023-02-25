package pretty.schedule.scraper;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;

import pretty.schedule.scheme.Faculty;
import pretty.schedule.scheme.Group;
import pretty.schedule.scheme.ScheduleOfWeek;

public class ScraperTest {
  private final String URL = "https://ruz.spbstu.ru/";
  private final Scraper scraper = new Scraper(URL);

  @Test
  void testGetFaculties() {
    assertDoesNotThrow(() -> {
      List<Faculty> faculties = scraper.getFaculties();
      assertNotNull(faculties);
      assertFalse(faculties.isEmpty());
    });
  }

  @Test
  void testGetGroups() {
    assertDoesNotThrow(() -> {
      List<Group> groups = scraper.getGroups("100");
      assertNotNull(groups);
    });
  }

  @Test
  void testGetGroupByName() {
    assertDoesNotThrow(() -> {
      Group group = scraper.getGroupByName("3530904/10006");
      assertNotNull(group);
      assertEquals("3530904/10006", group.getName());
    });
  }

  @Test
  void testGetRangeScheduleById() {
    assertDoesNotThrow(() -> {
      Instant start = Instant.now();
      Instant end = start.plusSeconds(86400);
      List<ScheduleOfWeek> schedules = scraper.getRangeScheduleById("100", start, end);
      assertNotNull(schedules);
      assertFalse(schedules.isEmpty());
    });
  }

  @Test
  void testGetRangeScheduleByName() {
    assertDoesNotThrow(() -> {
      Instant start = Instant.now();
      Instant end = start.plusSeconds(86400);
      List<ScheduleOfWeek> schedules = scraper.getRangeScheduleByName("3530904/10006", start, end);
      assertNotNull(schedules);
      assertFalse(schedules.isEmpty());
    });
  }
}
