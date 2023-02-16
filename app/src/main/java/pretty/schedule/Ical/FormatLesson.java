package pretty.schedule.Ical;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStamp;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.Summary;
import net.fortuna.ical4j.model.property.Uid;
import pretty.schedule.Type.Lesson;

public class FormatLesson {
    private final Lesson lesson;

    public FormatLesson(final Lesson lesson) {
        this.lesson = lesson;
    }

    private Calendar generateCal() {
        TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
        TimeZone timezone = registry.getTimeZone(FormatSchedule.getTimeZone());

        java.util.Calendar date = new GregorianCalendar();
        date.setTimeZone(timezone);

        return date;

    }

    public Calendar getStartDate(final java.util.TimeZone tz) {
        java.util.Calendar date = generateCal();
        var time = new FormatTime(lesson.getTimeStart());
        date.setTimeZone(tz);
        date.set(java.util.Calendar.HOUR, time.getHour());
        date.set(java.util.Calendar.MINUTE, time.getMinute());
        return date;
    }

    public Calendar getEndDate(final java.util.TimeZone tz) {
        java.util.Calendar date = generateCal();
        var time = new FormatTime(lesson.getTimeEnd());
        date.setTimeZone(tz);
        date.set(java.util.Calendar.HOUR, time.getHour());
        date.set(java.util.Calendar.MINUTE, time.getMinute());
        return date;
    }

    public VEvent generateEvent(final java.util.TimeZone tz, final int year, final int month, final int day) {
        java.util.Calendar start = getStartDate(tz);
        start.set(java.util.Calendar.YEAR, year);
        start.set(java.util.Calendar.MONTH, month);
        start.set(java.util.Calendar.DAY_OF_MONTH, day);

        java.util.Calendar end = getEndDate(tz);
        end.set(java.util.Calendar.YEAR, year);
        end.set(java.util.Calendar.MONTH, month);
        end.set(java.util.Calendar.DAY_OF_MONTH, day);

        var name = lesson.getSubject();
        var event = new VEvent(new DateTime(start.getTime()), new DateTime(end.getTime()), name);

        Uid uid = new Uid(UUID.randomUUID().toString() + System.currentTimeMillis());

        StringBuilder desc = new StringBuilder();
        desc.append(lesson.getAuditories());

        event.getProperties().add(uid);
        event.getProperties().add(new Description(desc.toString()));
        event.getProperties().add(new Location(lesson.getAuditories().toString()));
        event.getProperties().add(new Summary(lesson.getSubjectShort()));
        event.getProperties().add(new DtStamp(new DateTime()));
        event.getProperties().add(new DtStart(new DateTime(start.getTime())));
        event.getProperties().add(new DtEnd(new DateTime(end.getTime())));

        return event;
    }

}
