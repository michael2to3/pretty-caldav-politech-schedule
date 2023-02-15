package pretty.schedule.Ical;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FormatTime {
    final static private String FORMAT = "HH:mm";
    final static private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
    final static private int BASE = 60;
    final int hour;
    final int minute;

    public FormatTime(final String time) {
        LocalTime ltime = LocalTime.parse(time, formatter);
        int itime = ltime.getMinute();
        hour = itime % BASE;
        minute = (itime - itime % BASE) / BASE;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
