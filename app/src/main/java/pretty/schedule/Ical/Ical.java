package pretty.schedule.Ical;

import java.util.List;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import pretty.schedule.Scheme.ScheduleOfDay;

public class Ical {
    private FormatSchedule schedule;

    public Ical(final ScheduleOfDay schedule) {
        this.schedule = new FormatSchedule(schedule);
    }

    public Ical(final FormatSchedule schedule) {
        this.schedule = schedule;
    }

    public Calendar generateCal() {
        Calendar cal = new Calendar();
        cal.getProperties().add(new ProdId("-//My calendar//EN"));
        cal.getProperties().add(Version.VERSION_2_0);
        cal.getProperties().add(CalScale.GREGORIAN);
        List<VEvent> evlessons = schedule.getEvent();
        cal.getComponents().addAll(evlessons);

        return cal;
    }
}
