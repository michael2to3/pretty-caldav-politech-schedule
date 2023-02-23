package pretty.schedule.ical;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FormatTime {
  final static private String FORMAT = "HH:mm";
  final static private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
  final int hour;
  final int minute;

  public FormatTime(final String time) {
    LocalTime ltime = LocalTime.parse(time, formatter);
    hour = ltime.getHour();
    minute = ltime.getMinute();
  }

  public int getHour() {
    return hour;
  }

  public int getMinute() {
    return minute;
  }
}
