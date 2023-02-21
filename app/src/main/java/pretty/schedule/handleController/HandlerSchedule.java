package pretty.schedule.handleController;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import net.fortuna.ical4j.model.Calendar;
import pretty.schedule.ical.Ical;
import pretty.schedule.scheme.Group;
import pretty.schedule.scheme.ScheduleOfWeek;
import pretty.schedule.scraper.Scraper;
import pretty.schedule.util.Json;

public class HandlerSchedule {
    private int defaultRangeOfSchedule;
    private Scraper scraper;

    public HandlerSchedule(final String source) {
        this.defaultRangeOfSchedule = 1;
        this.scraper = new Scraper(source);
    }

    private Instant formatStartDate(final String startDate) {
        Instant sDate = Instant.now().minus(defaultRangeOfSchedule * 7, ChronoUnit.DAYS);
        if (startDate != null) {
            sDate = Instant.parse(startDate);
        }
        return sDate;
    }

    private Instant formatEndDate(final String endDate) {
        Instant eDate = Instant.now().plus(defaultRangeOfSchedule * 7, ChronoUnit.DAYS);
        if (endDate != null) {
            eDate = Instant.parse(endDate);
        }
        return eDate;
    }

    public String generateScheduleJson(final String groupId, final String startDate, final String endDate)
            throws JsonParseException, JsonMappingException, IOException {

        List<ScheduleOfWeek> schedules = scraper.getRangeScheduleOfWeek(groupId, formatStartDate(startDate),
                formatEndDate(endDate));

        return Json.convertString(schedules);
    }

    public Calendar generateScheduleIcal(final String groupId, final String startDate, final String endDate)
            throws JsonParseException, JsonMappingException, IOException {

        List<ScheduleOfWeek> schedules = scraper.getRangeScheduleOfWeek(groupId, formatStartDate(startDate),
                formatEndDate(endDate));

        Calendar total = new Ical("schedule", schedules.get(0)).getCalendar();
        for (var schedule : schedules) {
            total = schedule.getDays().stream()
                    .map(sch -> new Ical("schedule", sch).getCalendar())
                    .reduce(total, Ical::merge);
        }
        return total;
    }

    public String generateFacultiesJson() throws JsonParseException, JsonMappingException, IOException {
        return Json.convertString(scraper.getFaculties());
    }

    public String generateGroupsJson(final String id) throws JsonParseException, JsonMappingException, IOException {
        return Json.convertString(scraper.getGroups(id));
    }

    public String generateGroupOfNameJson(final String name)
            throws JsonParseException, JsonMappingException, IOException {
        Group group = scraper.getGroupOfName(name);
        return Json.convertString(group);
    }
}
