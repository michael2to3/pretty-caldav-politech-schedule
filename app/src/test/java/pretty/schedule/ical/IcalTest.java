package pretty.schedule.ical;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import net.fortuna.ical4j.model.Calendar;
import pretty.schedule.scheme.Auditorie;
import pretty.schedule.scheme.Building;
import pretty.schedule.scheme.Faculty;
import pretty.schedule.scheme.Group;
import pretty.schedule.scheme.Lesson;
import pretty.schedule.scheme.ScheduleOfDay;
import pretty.schedule.scheme.ScheduleOfWeek;
import pretty.schedule.scheme.TypeObj;
import pretty.schedule.scheme.Week;

public class IcalTest {

  private TypeObj getTypeObj() {
    TypeObj obj = new TypeObj();
    obj.setId(0);
    obj.setAbbr("abbr");
    obj.setName("name");
    return obj;
  }

  private Auditorie getAuditorie() {
    Building build = new Building(0, "name", "build", "build");
    Auditorie auditorie = new Auditorie(0, "name", build);
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

  private Lesson getLesson() {
    Lesson lesson = new Lesson();
    lesson.setType(0);
    lesson.setParity(0);
    lesson.setSubject("Math");
    lesson.setTimeStart("09:00");
    lesson.setTimeEnd("10:00");
    lesson.setType(0);
    lesson.setTypeObj(getTypeObj());
    lesson.setAuditories(getAuditories());
    return lesson;
  }

  private List<Lesson> getLessons() {
    List<Lesson> lessons = new ArrayList<>();
    lessons.add(getLesson());
    lessons.add(getLesson());
    lessons.add(getLesson());
    lessons.add(getLesson());
    return lessons;
  }

  private ScheduleOfDay getScheduleOfDay(int countLessons) {
    var weekday = 1;
    var date = "2022-01-01";
    var lessons = getLessons();
    ScheduleOfDay schedule = new ScheduleOfDay(weekday, date, lessons);
    schedule.setDate("2022-01-01");
    schedule.setWeekday(1);
    for (int i = 0; i < countLessons; ++i) {
      schedule.setLessons(getLessons());
    }
    return schedule;
  }

  private Week getWeek() {
    Week week = new Week("2022-01-01", "2022-01-07", true);
    return week;
  }

  private Group getGroup() {
    int id = 1;
    String name = "name";
    int level = 1;
    String type = "type";
    int kind = 1;
    String spec = "spec";
    int year = 2022;
    String abbr = "abbr";
    Faculty faculty = new Faculty(id, name, abbr);
    Group group = new Group(id, name, level, type, kind, spec, year, faculty);
    group.setName("Group");
    return group;
  }

  private ScheduleOfWeek getScheduleOfWeek(int countSchedules, int countLessons) {
    List<ScheduleOfDay> days = new ArrayList<>();
    for (int i = 0; i < countSchedules; ++i) {
      days.add(getScheduleOfDay(countLessons));
    }
    ScheduleOfWeek schedule = new ScheduleOfWeek(getWeek(), days, getGroup());
    return schedule;
  }

  @Test
  void testGetCalendar() {
    String name = "MyCalendar";
    ScheduleOfDay schedule = getScheduleOfDay(2);
    Ical ical = new Ical(name, schedule);

    Calendar actual = ical.getCalendar();

    assertNotNull(actual);
    assertEquals(actual.getComponents().size(), 4);
  }

  @Test
  void testMerge() {
    String name = "MyCalendar";
    List<ScheduleOfWeek> schedules = new ArrayList<>();
    schedules.add(getScheduleOfWeek(2, 2));
    schedules.add(getScheduleOfWeek(2, 2));
    Ical ical = new Ical(name, schedules);

    Calendar actual = ical.getCalendar();

    assertNotNull(actual);
    assertEquals(actual.getComponents().size(), 16);
  }
}
