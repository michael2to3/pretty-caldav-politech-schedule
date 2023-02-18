package pretty.schedule.handleController;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.fortuna.ical4j.model.Calendar;
import pretty.schedule.ical.Ical;
import pretty.schedule.scheme.ScheduleOfWeek;
import pretty.schedule.scraper.Scraper;

public class HandlerSchedule {
    private String source;
    private int defaultRangeOfSchedule;

    public HandlerSchedule(final String source) {
        this.source = source;
        this.defaultRangeOfSchedule = 3;
    }

    private String toJson(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
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

        Scraper scraper = new Scraper(source);
        List<ScheduleOfWeek> schedules = scraper.getRangeScheduleOfWeek(groupId, formatStartDate(startDate),
                formatEndDate(endDate));

        return toJson(schedules);
    }

    public Calendar generateScheduleIcal(final String groupId, final String startDate, final String endDate)
            throws JsonParseException, JsonMappingException, IOException {

        Scraper scraper = new Scraper(source);
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
}
