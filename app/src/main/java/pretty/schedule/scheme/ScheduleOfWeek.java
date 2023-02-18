package pretty.schedule.scheme;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScheduleOfWeek {
	private Week week;
	private List<ScheduleOfDay> days;
	private Group group;
	private boolean error;

	@JsonProperty("text")
	private String textOfError;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getTextOfError() {
		return textOfError;
	}

	public void setTextOfError(String textOfError) {
		this.textOfError = textOfError;
	}

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
