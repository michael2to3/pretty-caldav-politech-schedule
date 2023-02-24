package pretty.schedule.scheme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ScheduleOfWeekTest {
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

  private TypeObj getTypeObj() {
    TypeObj obj = new TypeObj();
    obj.setId(0);
    obj.setAbbr("abbr");
    obj.setName("name");
    return obj;
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

  private List<ScheduleOfDay> getDays() {
    List<ScheduleOfDay> days = new ArrayList<>();
    days.add(new ScheduleOfDay(1, "Monday", getLessons()));
    days.add(new ScheduleOfDay(2, "Tuesday", getLessons()));
    days.add(new ScheduleOfDay(3, "Wednesday", getLessons()));
    return days;
  }

  private Group getGroup() {
    int id = 0;
    String name = "Name";
    int level = 0;
    String type = "type";
    int kind = 0;
    String spec = "Spec";
    int year = 2020;
    String abbr = "abbr";
    Faculty faculty = new Faculty(id, name, abbr);
    return new Group(id, name, level, type, kind, spec, year, faculty);
  }

  private Week getWeek() {
    var dateStart = "2020-01-01";
    var dateEnd = "2020-01-07";
    var isOdd = true;
    var week = new Week(dateStart, dateEnd, isOdd);
    return week;
  }

  @Test
  void testCompare() {
    var week = getWeek();
    var days = getDays();
    var group = getGroup();

    var oweek = new Week("2020-01-01", "2020-01-07", false);
    var odays = new ArrayList<ScheduleOfDay>();
    odays.add(new ScheduleOfDay(1, "Monday", getLessons()));
    var ogroup = new Group(0, "Name", 0, "type", 0, "Spec", 2020, new Faculty(0, "Name", "abbr"));
    ScheduleOfWeek lhs = new ScheduleOfWeek(week, days, group);
    ScheduleOfWeek rhs = new ScheduleOfWeek(week, days, group);
    ScheduleOfWeek other = new ScheduleOfWeek(oweek, odays, ogroup);

    assertEquals(lhs, rhs);
    assertNotEquals(lhs, other);
    assertNotEquals(rhs, other);
    assertNotEquals(lhs, null);
    assertEquals(lhs.hashCode(), rhs.hashCode());
    assertNotEquals(lhs.hashCode(), other.hashCode());
  }

  @Test
  void testSettersWithoutArgsConstructor() {
    var week = getWeek();
    var days = getDays();
    var group = getGroup();
    ScheduleOfWeek lhs = new ScheduleOfWeek();
    lhs.setWeek(week);
    lhs.setDays(days);
    lhs.setGroup(group);
    assertEquals(lhs.getWeek(), week);
    assertEquals(lhs.getDays(), days);
    assertEquals(lhs.getGroup(), group);
  }
}
