package pretty.schedule.scraper;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class RangeOfDate {
  private final int RANGE_OF_SCHEDULE_AS_DAYS;
  private static final int STEP_OF_RANGE = 7;

  public RangeOfDate(int rangeOfScheduleAsDays) {
    this.RANGE_OF_SCHEDULE_AS_DAYS = rangeOfScheduleAsDays;
  }

  public Instant formatStartDate(final String startDate) {
    Instant defaultDate = Instant.now().minus(RANGE_OF_SCHEDULE_AS_DAYS, ChronoUnit.DAYS);
    return parseOrDefault(startDate, defaultDate);
  }

  public Instant formatEndDate(final String endDate) {
    Instant defaultDate = Instant.now().plus(RANGE_OF_SCHEDULE_AS_DAYS, ChronoUnit.DAYS);
    return parseOrDefault(endDate, defaultDate);
  }

  public static List<Instant> getListDateOfRange(final Instant startDate, final Instant endDate) {
    final List<Instant> list = new ArrayList<>();
    Instant current = startDate;
    while (!current.isAfter(endDate)) {
      list.add(current);
      current = current.plus(Duration.ofDays(STEP_OF_RANGE));
    }
    return list;
  }

  static public Instant getPreviousDate(final Instant date, final DayOfWeek dayOfWeek) {
    final ZonedDateTime zone = date.atZone(ZoneId.systemDefault());
    final ZonedDateTime prev = zone.with(TemporalAdjusters.previous(dayOfWeek));
    return prev.toInstant();
  }

  private Instant parseOrDefault(final String date, final Instant defaultValue) {
    if (date == null) {
      return defaultValue;
    }
    return Instant.parse(date);
  }
}
