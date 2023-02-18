package pretty.schedule.Ical;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.component.VEvent;
import pretty.schedule.Scheme.ScheduleOfDay;

public class FormatSchedule {
	final static private String TIME_ZONE = "Erope/Moscow";
	final static private String FORMAT_DATE = "yyyy-M-d";
	private final ScheduleOfDay schedule;
	private LocalDate date;

	public static String getTimeZone() {
		return TIME_ZONE;
	}

	public static String getFormatDate() {
		return FORMAT_DATE;
	}

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

	public ScheduleOfDay getSchedule() {
		return schedule;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<VEvent> getEvent() {
		final java.util.TimeZone tz = TimeZone.getTimeZone(TIME_ZONE);
		List<VEvent> list = new ArrayList<>();
		int year = getYear();
		int month = getMonth();
		int day = getDayOfMonth();
		for (var lesson : schedule.getLessons()) {
			var formatLesson = new FormatLesson(lesson);
			list.add(formatLesson.generateEvent(tz, year, month, day));
		}
		return list;
	}
}
