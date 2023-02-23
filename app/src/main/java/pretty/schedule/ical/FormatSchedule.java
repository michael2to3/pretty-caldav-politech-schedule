package pretty.schedule.ical;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import net.fortuna.ical4j.model.component.VEvent;
import pretty.schedule.scheme.Lesson;
import pretty.schedule.scheme.ScheduleOfDay;

public class FormatSchedule {
  // @FIX duplicate generate timezone
  final static private String FORMAT_DATE = "yyyy-M-d";
  final static private String TIME_ZONE = "Erope/Moscow";
  private static final java.util.TimeZone TZ = TimeZone.getTimeZone(TIME_ZONE);
  private final ScheduleOfDay schedule;
  private Instant date;

  public FormatSchedule(final ScheduleOfDay schedule) {
    this.schedule = schedule;
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
    var local = LocalDate.parse(schedule.getDate(), dateFormatter);
    this.date = local.atStartOfDay().toInstant(TZ.toZoneId().getRules().getOffset(Instant.now()));
  }

  public int getMonth() {
    return date.atZone(TZ.toZoneId()).getMonth().getValue();
  }

  public int getDayOfMonth() {
    return date.atZone(TZ.toZoneId()).getDayOfMonth();
  }

  public int getYear() {
    return date.atZone(TZ.toZoneId()).getYear();
  }

  public int getHourOfDay() {
    return date.atZone(TZ.toZoneId()).getHour();
  }

  public ScheduleOfDay getSchedule() {
    return schedule;
  }

  public List<VEvent> getEvent() {
    List<VEvent> list = new ArrayList<>();
    int year = getYear();
    int month = getMonth();
    int day = getDayOfMonth();
    for (Lesson lesson : schedule.getLessons()) {
      FormatLesson formatLesson = new FormatLesson(lesson);
      VEvent event = formatLesson.generateEvent(year, month, day);
      list.add(event);
    }
    return list;
  }

  public static String getFormatDate() {
    return FORMAT_DATE;
  }

  public static String getTimeZone() {
    return TIME_ZONE;
  }

  public static java.util.TimeZone getTz() {
    return TZ;
  }

  public Instant getDate() {
    return date;
  }

  public void setDate(Instant date) {
    this.date = date;
  }
}
