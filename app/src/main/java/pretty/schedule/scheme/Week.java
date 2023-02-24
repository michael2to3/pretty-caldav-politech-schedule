package pretty.schedule.scheme;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Represents a week of the academic calendar. */
public class Week {
  /** The start date of the week in ISO-8601 format. */
  @JsonProperty("date_start")
  private String dateStart;
  /** The end date of the week in ISO-8601 format. */
  @JsonProperty("date_end")
  private String dateEnd;
  /** Whether the week is odd or even. */
  @JsonProperty("is_odd")
  private boolean isOdd;

  public Week() {
  }

  public Week(String dateStart, String dateEnd, boolean isOdd) {
    this.dateStart = dateStart;
    this.dateEnd = dateEnd;
    this.isOdd = isOdd;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Week))
      return false;
    Week week = (Week) o;
    return isOdd == week.isOdd &&
        Objects.equals(dateStart, week.dateStart) &&
        Objects.equals(dateEnd, week.dateEnd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dateStart, dateEnd, isOdd);
  }

  public String getDateStart() {
    return dateStart;
  }

  public void setDateStart(String dateStart) {
    this.dateStart = dateStart;
  }

  public String getDateEnd() {
    return dateEnd;
  }

  public void setDateEnd(String dateEnd) {
    this.dateEnd = dateEnd;
  }

  public boolean isOdd() {
    return isOdd;
  }

  public void setOdd(boolean isOdd) {
    this.isOdd = isOdd;
  }
}
