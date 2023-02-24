package pretty.schedule.ical;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class FactoryTimeZone {
  final static private String TIME_ZONE = "Erope/Moscow";
  private static final java.util.TimeZone TZ = TimeZone.getTimeZone(TIME_ZONE);

  public static String getTimeZone() {
    return TIME_ZONE;
  }

  public static java.util.TimeZone getTz() {
    return TZ;
  }

  public static int getOffset() {
    ZonedDateTime localTime = ZonedDateTime.now();
    ZoneOffset localOffset = localTime.getOffset();
    ZoneOffset utcOffset = ZoneOffset.ofHours(0);
    int offsetDiff = localOffset.getTotalSeconds() - utcOffset.getTotalSeconds();
    return offsetDiff;
  }

  public static int getOffsetAsHour() {
    return getOffset() / 3600;
  }
}
