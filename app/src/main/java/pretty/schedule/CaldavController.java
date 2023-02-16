package pretty.schedule;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Instant;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.fortuna.ical4j.data.CalendarOutputter;
import pretty.schedule.Ical.Ical;
import pretty.schedule.Scraper.Scraper;
import pretty.schedule.Type.ScheduleOfWeek;

@RestController
public class CaldavController {
    @GetMapping("/scheduleofweek/")
    public ScheduleOfWeek getScheduleOfWeek() {
        String u = "https://ruz.spbstu.ru/";
        Scraper sc = new Scraper(u);
        ScheduleOfWeek s;
        try {
            s = sc.getScheduleOfWeek("36267", Instant.now());
            return s;
        } catch (Exception e) {
        }
        throw new NullPointerException();
    }

    @GetMapping("/ics/")
    public ResponseEntity<Resource> ics() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CalendarOutputter outputter = new CalendarOutputter();

        String u = "https://ruz.spbstu.ru/";
        Scraper sc = new Scraper(u);
        ScheduleOfWeek s = sc.getScheduleOfWeek("36267", Instant.now());
        Ical ics = new Ical(s.getDays().get(0));
        outputter.output(ics.generateCal(), baos);

        ByteArrayResource resource = new ByteArrayResource(baos.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=calendar.ics");
        headers.add(HttpHeaders.CONTENT_TYPE, "text/calendar");
        headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
        headers.add(HttpHeaders.PRAGMA, "no-cache");
        headers.add(HttpHeaders.EXPIRES, "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .body(resource);
    }
}
