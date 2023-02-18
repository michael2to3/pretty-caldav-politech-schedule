package pretty.schedule.ical;

import java.util.List;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import pretty.schedule.scheme.ScheduleOfDay;
import pretty.schedule.scheme.ScheduleOfWeek;

public class Ical {
    private final FormatSchedule schedule;
    private Calendar calendar;

    public Ical(final String name, final ScheduleOfWeek schedule) {
        this.schedule = new FormatSchedule(schedule.getDays().get(0));

        Calendar total = generateCalendar(name, this.schedule);
        total = schedule.getDays().stream()
                .map(schday -> generateCalendar(name, new FormatSchedule(schday)))
                .reduce(total, Ical::merge);
        this.calendar = total;
    }

    public Ical(final String name, final ScheduleOfDay schedule) {
        this.schedule = new FormatSchedule(schedule);
        this.calendar = generateCalendar(name);
    }

    public Ical(final String name, final FormatSchedule schedule) {
        this.schedule = schedule;
        this.calendar = generateCalendar(name);
    }

    private Calendar generateCalendar(final String calName) {
        return generateCalendar(calName, schedule);
    }

    private static Calendar generateCalendar(final String calName, final FormatSchedule schedule) {
        Calendar cal = new Calendar();
        cal.getProperties().add(new ProdId(String.format("-//{0}//RU", calName)));
        cal.getProperties().add(Version.VERSION_2_0);
        cal.getProperties().add(CalScale.GREGORIAN);
        List<VEvent> evlessons = schedule.getEvent();
        cal.getComponents().addAll(evlessons);

        return cal;
    }

    public static Calendar merge(final Calendar lhs, final Calendar rhs) {
        Calendar ans = lhs;
        for (Component component : rhs.getComponents()) {
            if (component instanceof VEvent) {
                VEvent ev = (VEvent) component;
                ans.getComponents().add(ev);
            }
        }
        return ans;
    }

    public static Calendar mergeAll(final List<Calendar> list) {
        Calendar ans = list.get(0);
        for (Calendar item : list) {
            if (item == list.get(0)) {
                continue;
            }
            Ical.merge(ans, item);
        }
        return ans;
    }

    public FormatSchedule getSchedule() {
        return schedule;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
