package pretty.schedule.Ical;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VTimeZone;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import pretty.schedule.Type.Lesson;

public class Ical {
    final static private String TIME_ZONE = "Erope/Moscow";

    private DateTime generateDateTime(final String stime) {
        java.util.Calendar date = new GregorianCalendar();

        FormatTime formatTime = new FormatTime(stime);
        date.set(java.util.Calendar.HOUR_OF_DAY, (int) formatTime.getHour());
        date.set(java.util.Calendar.MINUTE, (int) formatTime.getMinute());

        DateTime time = new DateTime(date.getTime());

        return time;
    }

    private List<VEvent> generateVEvent(final FormatSchedule schedule) {
        TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
        TimeZone timezone = registry.getTimeZone(TIME_ZONE);
        VTimeZone tz = timezone.getVTimeZone();
        java.util.Calendar date = new GregorianCalendar();
        date.setTimeZone(timezone);
        date.set(java.util.Calendar.MONTH, schedule.getMonth());
        date.set(java.util.Calendar.DAY_OF_MONTH, schedule.getDayOfMonth());
        date.set(java.util.Calendar.YEAR, schedule.getYear());

        List<VEvent> list = new ArrayList<>();
        for (var lesson : schedule.getSchedule().getLessons()) {
            String eventName = lesson.getSubject();

            DateTime timeStart = generateDateTime(lesson.getTimeStart());
            DateTime timeEnd = generateDateTime(lesson.getTimeEnd());

            VEvent meeting = new VEvent(timeStart, timeEnd, eventName);
            meeting.getProperties().add(tz.getTimeZoneId());

            Uid uid = new Uid(UUID.randomUUID().toString() + System.currentTimeMillis());
            meeting.getProperties().add(uid);

            Calendar icsCalendar = new Calendar();
            icsCalendar.getProperties().add(new ProdId("-//Events Calendar//iCal4j 1.0//EN"));
            icsCalendar.getProperties().add(CalScale.GREGORIAN);

            icsCalendar.getComponents().add(meeting);

            list.add(meeting);
        }
        return list;
    }

    private Calendar builder(final FormatSchedule schedule) {
        TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
        TimeZone timezone = registry.getTimeZone(TIME_ZONE);
        VTimeZone tz = timezone.getVTimeZone();

        List<VEvent> list = generateVEvent(schedule);

        return icsCalendar;
    }
}
