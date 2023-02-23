package pretty.schedule.ical;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class FormatTime {
  final static private String FORMAT = "HH:mm";
  final static private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
  final int hour;
  final int minute;

  public FormatTime(final String time) {
    LocalTime localTime = LocalTime.parse(time, formatter);
    Instant instant = LocalDateTime.of(LocalDate.now(), localTime).atZone(ZoneId.systemDefault()).toInstant();
    Instant utcInstant = instant.atOffset(ZoneOffset.UTC).toInstant();
    LocalTime utcTime = LocalDateTime.ofInstant(utcInstant, ZoneOffset.UTC).toLocalTime();
    hour = utcTime.getHour();
    minute = utcTime.getMinute();
  }

  public int getHour() {
    return hour;
  }

  public int getMinute() {
    return minute;
  }
}
