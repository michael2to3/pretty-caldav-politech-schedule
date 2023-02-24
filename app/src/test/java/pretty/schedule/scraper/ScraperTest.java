package pretty.schedule.scraper;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;

import pretty.schedule.scheme.ScheduleOfWeek;

public class ScraperTest {

  private Scraper scraper;
  private Request request;

  @BeforeEach
  public void setup() {
    request = mock(Request.class);
    scraper = new Scraper("http://example.com");
  }

  @Test
  public void testGetRangeScheduleOfWeek() throws IOException {
    Instant startDate = Instant.parse("2023-02-01T00:00:00Z");
    Instant endDate = Instant.parse("2023-02-28T00:00:00Z");
    final ZonedDateTime zone = startDate.atZone(ZoneId.systemDefault());
    final ZonedDateTime prevMonday = zone.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
    final List<Instant> expectedDates = new ArrayList<>();
    Instant current = prevMonday.toInstant();
    while (!current.isAfter(endDate)) {
      expectedDates.add(current);
      current = current.plus(Duration.ofDays(7));
    }

    ScheduleOfWeek mockSchedule = new ScheduleOfWeek();
    when(
        request.get("http://example.com/api/v1/ruz/scheduler/123?date=2023-02-05", new TypeReference<ScheduleOfWeek>() {
        })).thenReturn(mockSchedule);
  }

}
