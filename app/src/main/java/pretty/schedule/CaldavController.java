package pretty.schedule;

import java.time.Instant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
