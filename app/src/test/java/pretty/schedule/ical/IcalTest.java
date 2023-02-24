package pretty.schedule.ical;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Summary;
import net.fortuna.ical4j.model.property.Version;
import pretty.schedule.scheme.Auditorie;
import pretty.schedule.scheme.Group;
import pretty.schedule.scheme.Lesson;
import pretty.schedule.scheme.ScheduleOfDay;
import pretty.schedule.scheme.ScheduleOfWeek;
import pretty.schedule.scheme.TypeObj;
import pretty.schedule.scheme.Week;

public class IcalTest {

  private VEvent getEvent() {
    VEvent event = new VEvent();
    event.getProperties().add(new Description("description"));
    event.getProperties().add(new Summary("summary"));
    event.getProperties().add(new ProdId(String.format("-//{0}//RU", "Schedule")));
    event.getProperties().add(Version.VERSION_2_0);
    event.getProperties().add(CalScale.GREGORIAN);
    return event;
  }

  private List<VEvent> getEvents() {
    List<VEvent> events = new ArrayList<>();
    events.add(getEvent());
    events.add(getEvent());
    return events;
  }

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
    ScheduleOfDay schedule = new ScheduleOfDay();
    schedule.setDate("2022-01-01");
    schedule.setWeekday(1);
    for (int i = 0; i < countLessons; ++i) {
      schedule.setLessons(getLessons());
    }
    return schedule;
  }

  private Week getWeek() {
    Week week = new Week();
    week.setDateStart("2022-01-01");
    week.setDateEnd("2022-01-07");
    week.setOdd(true);
    return week;
  }

  private Group getGroup() {
    Group group = new Group();
    group.setName("Group");
    return group;
  }

  private ScheduleOfWeek getScheduleOfWeek(int countSchedules, int countLessons) {
    ScheduleOfWeek schedule = new ScheduleOfWeek();
    schedule.setWeek(getWeek());
    schedule.setGroup(getGroup());
    List<ScheduleOfDay> days = new ArrayList<>();
    for (int i = 0; i < countSchedules; ++i) {
      days.add(getScheduleOfDay(countLessons));
    }
    schedule.setDays(days);
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
