package pretty.schedule.scheme;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Week {
  @JsonProperty("date_start") private String dateStart;
  @JsonProperty("date_end") private String dateEnd;
  @JsonProperty("is_odd") private boolean isOdd;

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
