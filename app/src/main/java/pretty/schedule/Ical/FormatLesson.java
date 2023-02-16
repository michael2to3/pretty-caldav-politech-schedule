package pretty.schedule.Ical;

import java.util.Calendar;
import java.util.GregorianCalendar;

import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.component.VEvent;
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

    public Calendar getStartDate() {
        java.util.Calendar date = generateCal();
        var time = new FormatTime(lesson.getTimeStart());
        date.set(java.util.Calendar.HOUR, time.getHour());
        date.set(java.util.Calendar.MINUTE, time.getMinute());
        return date;
    }

    public Calendar getEndDate() {
        java.util.Calendar date = generateCal();
        var time = new FormatTime(lesson.getTimeEnd());
        date.set(java.util.Calendar.HOUR, time.getHour());
        date.set(java.util.Calendar.MINUTE, time.getMinute());
        return date;
    }

    public VEvent generateEvent(final int year, final int month, final int day) {
        FormatLesson flesson = new FormatLesson(lesson);
        String eventName = lesson.getSubject();
        java.util.Calendar start = flesson.getStartDate();
        start.set(java.util.Calendar.YEAR, year);
        start.set(java.util.Calendar.MONTH, month);
        start.set(java.util.Calendar.DAY_OF_MONTH, day);

        java.util.Calendar end = flesson.getEndDate();
        end.set(java.util.Calendar.YEAR, year);
        end.set(java.util.Calendar.MONTH, month);
        end.set(java.util.Calendar.DAY_OF_MONTH, day);

        return new VEvent(new DateTime(start.getTime()), new DateTime(end.getTime()), eventName);
    }

}
