package pretty.schedule.scheme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ScheduleOfDayTest {

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

  @Test
  void testCompare() {
    var week = 1;
    var date = "2022-01-01";
    var lessons = getLessons();
    ScheduleOfDay lhs = new ScheduleOfDay(week, date, lessons);
    ScheduleOfDay rhs = new ScheduleOfDay(week, date, lessons);
    ScheduleOfDay other = new ScheduleOfDay(week, "2999-02-03", lessons);
    assertEquals(lhs, rhs);
    assertNotEquals(lhs, other);
    assertNotEquals(rhs, other);
  }

  @Test
  void testGetter() {
    var week = 1;
    var date = "2022-01-01";
    var lessons = getLessons();
    ScheduleOfDay lhs = new ScheduleOfDay(week, date, lessons);
    assertEquals(week, lhs.getWeekday());
    assertEquals(date, lhs.getDate());
    assertEquals(lessons, lhs.getLessons());
  }

  @Test
  void testSetter() {
    var week = 1;
    var date = "2022-01-01";
    var lessons = getLessons();
    ScheduleOfDay lhs = new ScheduleOfDay(week, date, lessons);
    lhs.setWeekday(2);
    lhs.setDate("2999-02-03");
    lhs.setLessons(lessons);
    assertEquals(2, lhs.getWeekday());
    assertEquals("2999-02-03", lhs.getDate());
    assertEquals(lessons, lhs.getLessons());
  }
}
