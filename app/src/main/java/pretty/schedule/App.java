package pretty.schedule;

import java.time.Instant;

import pretty.schedule.Scraper.Scraper;

public class App {
    public static void main(String[] args) {
        var url = "https://ruz.spbstu.ru/";
        var s = new Scraper(url);
        try {
            System.out.println(s.getScheduleOfWeek("36267", Instant.now()).getWeek().getDateEnd() + "");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
