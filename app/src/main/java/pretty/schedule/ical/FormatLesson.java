package pretty.schedule.ical;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.GregorianCalendar;

import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.DtStamp;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.Summary;
import net.fortuna.ical4j.model.property.Uid;
import pretty.schedule.scheme.Auditorie;
import pretty.schedule.scheme.Group;
import pretty.schedule.scheme.Lesson;
import pretty.schedule.scheme.Teacher;

public class FormatLesson extends Lesson {
  final static private String TIME_ZONE = "Erope/Moscow";
  private static final java.util.TimeZone TZ = TimeZone.getTimeZone(TIME_ZONE);

  public FormatLesson(final Lesson lesson) {
    super(lesson);
  }

  private Calendar generateCal() {
    TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
    TimeZone timezone = registry.getTimeZone(TIME_ZONE);

    java.util.Calendar date = new GregorianCalendar();
    date.setTimeZone(timezone);

    return date;
  }

  public Calendar getDate(String time, final int day, final int month, final int year) {
    java.util.Calendar date = generateCal();
    FormatTime formatTime = new FormatTime(time);
    date.setTimeZone(TZ);
    date.set(java.util.Calendar.MINUTE, formatTime.getMinute());
    date.set(java.util.Calendar.HOUR_OF_DAY, formatTime.getHour());
    date.set(java.util.Calendar.DAY_OF_MONTH, day);
    date.set(java.util.Calendar.MONTH, month - 1);
    date.set(java.util.Calendar.YEAR, year);
    return date;
  }

  public VEvent generateEvent(final int year, final int month, final int day) {
    java.util.Calendar start = getStartDate();
    start.set(java.util.Calendar.YEAR, year);
    start.set(java.util.Calendar.MONTH, month);
    start.set(java.util.Calendar.DAY_OF_MONTH, day);

    java.util.Calendar end = getEndDate();
    end.set(java.util.Calendar.YEAR, year);
    end.set(java.util.Calendar.MONTH, month);
    end.set(java.util.Calendar.DAY_OF_MONTH, day);

    var name = getSubject();
    var event = new VEvent(new DateTime(start.getTime()), new DateTime(end.getTime()), name);

    event.getProperties().add(getUid());
    event.getProperties().add(getDescription());
    event.getProperties().add(new Location(getAuditories().toString()));
    event.getProperties().add(new Summary(getSubjectShort()));
    event.getProperties().add(new DtStamp(new DateTime()));
    event.getProperties().add(new DtStart(new DateTime(start.getTime())));
    event.getProperties().add(new DtEnd(new DateTime(end.getTime())));

    return event;
  }

  private Description getDescription() {
    StringBuilder str = new StringBuilder();
    str.append(getTypeObj());
    return new Description(str.toString());
  }
}
