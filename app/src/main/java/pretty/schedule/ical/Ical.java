package pretty.schedule.ical;

import java.util.List;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import pretty.schedule.scheme.ScheduleOfDay;
import pretty.schedule.scheme.ScheduleOfWeek;

public class Ical {
  private final Calendar schedule;

  public Ical(final String name, final List<ScheduleOfWeek> schedules) {
    this.schedule = merge(name, schedules);
  }

  public Ical(final String name, final ScheduleOfWeek schedules) {
    this.schedule = merge(name, schedules);
  }

  public Ical(final String name, final ScheduleOfDay schedule) {
    this.schedule = getCalendar(name, new FormatSchedule(schedule));
  }

  public Ical(final String name, final FormatSchedule schedule) {
    this.schedule = getCalendar(name, schedule);
  }

  public Calendar getCalendar() {
    return this.schedule;
  }

  private static Calendar getCalendar(final String name, final FormatSchedule schedule) {
    Calendar cal = new Calendar();
    cal.getProperties().add(new ProdId(String.format("-//{0}//RU", name)));
    cal.getProperties().add(Version.VERSION_2_0);
    cal.getProperties().add(CalScale.GREGORIAN);
    List<VEvent> evlessons = schedule.getEvent();
    cal.getComponents().addAll(evlessons);

    return cal;
  }

  private static Calendar merge(final String name, final ScheduleOfWeek schedules) {
    Calendar total = getCalendar(name, new FormatSchedule(schedules.getDays().get(0)));
    for (var schedule : schedules.getDays()) {
      total = CalendarMerge.merge(getCalendar(name, new FormatSchedule(schedule)), total);
    }
    return total;
  }

  private static Calendar merge(final String name, final List<ScheduleOfWeek> schedules) {
    Calendar total = null;
    for (var scheduleOfWeek : schedules) {
      for (var scheduleOfDay : scheduleOfWeek.getDays()) {
        total = CalendarMerge.merge(getCalendar(name, new FormatSchedule(scheduleOfDay)), total);
      }
    }
    return total;
  }
}
