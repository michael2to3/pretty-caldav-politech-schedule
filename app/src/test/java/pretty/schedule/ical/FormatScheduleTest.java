package pretty.schedule.ical;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import net.fortuna.ical4j.model.component.VEvent;
import pretty.schedule.scheme.Auditorie;
import pretty.schedule.scheme.Lesson;
import pretty.schedule.scheme.ScheduleOfDay;
import pretty.schedule.scheme.TypeObj;

@DisplayName("FormatSchedule")
class FormatScheduleTest {
  private ScheduleOfDay schedule;
  private Instant date;
  private FormatSchedule formatSchedule;

  private TypeObj getTypeObj() {
    TypeObj obj = new TypeObj();
    obj.setId(0);
    obj.setAbbr("abbr");
    obj.setName("name");
    return obj;
  }

  private Auditorie getAuditorie() {
    Auditorie auditorie = new Auditorie();
    auditorie.setId(0);
    auditorie.setName("name");
    return auditorie;
  }

  private List<Auditorie> getAuditories() {
    List<Auditorie> auditories = new ArrayList<>();
    auditories.add(getAuditorie());
    auditories.add(getAuditorie());
    return auditories;
  }

  private Lesson getLesson(final String name) {
    Lesson lesson = new Lesson();
    lesson.setSubject(name);
    lesson.setType(0);
    lesson.setParity(0);
    lesson.setTimeStart("09:00");
    lesson.setTimeEnd("10:00");
    lesson.setType(0);
    lesson.setTypeObj(getTypeObj());
    lesson.setAuditories(getAuditories());
    return lesson;
  }

  private List<Lesson> getLessons(final String... names) {
    List<Lesson> lessons = new ArrayList<>();
    for (String name : names) {
      lessons.add(getLesson(name));
    }
    return lessons;
  }

  private ScheduleOfDay getScheduleOfDay(final String... names) {
    ScheduleOfDay schedule = new ScheduleOfDay();
    schedule.setDate("2022-01-01");
    schedule.setWeekday(1);
    schedule.setLessons(getLessons(names));
    return schedule;
  }

  @BeforeEach
  void setUp() {
    schedule = getScheduleOfDay("Lesson 1", "Lesson 2");
    date = Instant.parse("2022-02-14T00:00:00Z");
    formatSchedule = new FormatSchedule(schedule);
    formatSchedule.setDate(date);
  }

  @Nested
  @DisplayName("getMonth()")
  class GetMonth {
    @Test
    @DisplayName("returns the correct month")
    void returnsCorrectMonth() {
      assertEquals(2, formatSchedule.getMonth());
    }
  }

  @Nested
  @DisplayName("getDayOfMonth()")
  class GetDayOfMonth {
    @Test
    @DisplayName("returns the correct day of month")
    void returnsCorrectDayOfMonth() {
      assertEquals(14, formatSchedule.getDayOfMonth());
    }
  }

  @Nested
  @DisplayName("getYear()")
  class GetYear {
    @Test
    @DisplayName("returns the correct year")
    void returnsCorrectYear() {
      assertEquals(2022, formatSchedule.getYear());
    }
  }

  @Nested
  @DisplayName("getHourOfDay()")
  class GetHourOfDay {
    @Test
    @DisplayName("returns the correct hour of day")
    void returnsCorrectHourOfDay() {
      assertEquals(0, formatSchedule.getHourOfDay());
    }
  }

  @Nested
  @DisplayName("getEvent()")
  class GetEvent {
    @ParameterizedTest(name = "lesson {index}: {0}")
    @CsvSource({ "Lesson 1, 10:00, 11:30", "Lesson 2, 13:00, 14:30" })
    @DisplayName("returns a VEvent for each lesson")
    void returnsVEventForEachLesson(String name, String start, String end) {
      List<VEvent> events = formatSchedule.getEvent();
      assertEquals(2, events.size());

      VEvent event1 = events.get(0);
      assertEquals("Lesson 1", event1.getSummary().getValue());
      assertEquals(date.plusSeconds(6 * 60 * 60), event1.getStartDate().getDate().toInstant());
      assertEquals(date.plusSeconds(7 * 60 * 60), event1.getEndDate().getDate().toInstant());

      VEvent event2 = events.get(1);
      assertEquals("Lesson 2", event2.getSummary().getValue());
      assertEquals(date.plusSeconds(6 * 60 * 60), event2.getStartDate().getDate().toInstant());
      assertEquals(date.plusSeconds(7 * 60 * 60), event2.getEndDate().getDate().toInstant());
    }
  }
}
