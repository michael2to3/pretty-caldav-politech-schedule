package pretty.schedule.ical;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import net.fortuna.ical4j.model.component.VEvent;
import pretty.schedule.scheme.Lesson;
import pretty.schedule.scheme.ScheduleOfDay;

public class FormatSchedule {
	final static private String FORMAT_DATE = "yyyy-M-d";
	private final ScheduleOfDay schedule;
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
		List<VEvent> list = new ArrayList<>();
		int year = getYear();
		int month = getMonth();
		int day = getDayOfMonth();
		for (Lesson lesson : schedule.getLessons()) {
			FormatLesson formatLesson = new FormatLesson(lesson);
			list.add(formatLesson.generateEvent(year, month, day));
		}
		return list;
	}
}
