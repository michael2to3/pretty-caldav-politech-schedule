package pretty.schedule.Ical;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import pretty.schedule.Type.ScheduleOfDay;

public class FormatSchedule {
    final static private String TIME_ZONE = "Erope/Moscow";
    final static private String FORMAT_DATE = "YYYY-M-d";
    private ScheduleOfDay schedule;
    private LocalDate date;

    public FormatSchedule(final ScheduleOfDay schedule) {
        this.schedule = schedule;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
        this.date = LocalDate.parse(schedule.getDate(), dateFormatter);
    }

    public int getMonth() {
        return date.getMonth().getValue();
    }

    public int getDayOfMonth() {
        return date.getDayOfMonth();
    }

    public int getYear() {
        return date.getYear();
    }

    public int getHourOfDay() {
        return date.getYear();
    }

    public static String getTimeZone() {
        return TIME_ZONE;
    }

    public static String getFormatDate() {
        return FORMAT_DATE;
    }

    public ScheduleOfDay getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleOfDay schedule) {
        this.schedule = schedule;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}