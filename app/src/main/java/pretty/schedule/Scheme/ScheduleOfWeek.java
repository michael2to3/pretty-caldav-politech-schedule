package pretty.schedule.Scheme;

import java.util.List;

public class ScheduleOfWeek {
	private Week week;
	private List<ScheduleOfDay> days;
	private Group group;

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Week getWeek() {
		return week;
	}

	public void setWeek(Week week) {
		this.week = week;
	}

	public List<ScheduleOfDay> getDays() {
		return days;
	}

	public void setDays(List<ScheduleOfDay> days) {
		this.days = days;
	}
}
