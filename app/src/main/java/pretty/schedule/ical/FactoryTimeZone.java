package pretty.schedule.ical;

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
}
