package pretty.schedule.scheme;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** Represents a weekly schedule for a particular group of students. */
public class ScheduleOfWeek {
  /** The week number for this schedule. */
  private Week week;
  /** The list of schedules for each day of the week. */
  private List<ScheduleOfDay> days;
  /** The group for which this schedule is generated. */
  private Group group;
  /** Value indicating whether there was an error generating this schedule. */
  private boolean error;
  /** The error message if there was an error generating this schedule. */
  @JsonProperty("text")
  private String textOfError;

  public boolean isError() {
    return error;
  }

  public void setError(boolean error) {
    this.error = error;
  }

  public String getTextOfError() {
    return textOfError;
  }

  public void setTextOfError(String textOfError) {
    this.textOfError = textOfError;
  }

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

  public Week getWeek() {
    return week;
  }

  public void setWeek(Week week) {
    this.week = week;
  }

  public List<ScheduleOfDay> getDays() {
    return days;
  }

  public void setDays(List<ScheduleOfDay> days) {
    this.days = days;
  }
}
