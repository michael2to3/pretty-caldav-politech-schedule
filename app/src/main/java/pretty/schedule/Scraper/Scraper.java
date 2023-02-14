package pretty.schedule.Scraper;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pretty.schedule.Type.ScheduleOfWeek;

public class Scraper {
    private final String url;

    public Scraper(final String url) {
        this.url = url;
    }

    private void load() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        var url = "https://ruz.spbstu.ru/api/v1/ruz/scheduler/35390?date=2023-2-20";
        URL apiUrl = new URL(url);
        ScheduleOfWeek schedule = mapper.readValue(apiUrl, ScheduleOfWeek.class);
        System.out.println(schedule);
    }

    public void ex() {
        try {
            load();
        } catch (Exception e) {
            System.out.println(e + "");
        }
    }
}
