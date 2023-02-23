package pretty.schedule.scheme;

import java.util.List;

/** Represents a schedule for a singly day. */
public class ScheduleOfDay {
  /** The weekday of the schedule, where 1 is Monday and 7 is Sunday. */
  private int weekday;
  /** The date of the schedule. in the format yyyy-mm-dd. */
  private String date;
  /** The list of lessons scheduled for this day. */
  private List<Lesson> lessons;

  public int getWeekday() {
    return weekday;
  }

  public void setWeekday(int weekday) {
    this.weekday = weekday;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public List<Lesson> getLessons() {
    return lessons;
  }

  public void setLessons(List<Lesson> lessons) {
    this.lessons = lessons;
  }
}
