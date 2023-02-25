package pretty.schedule.scraper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

public class RangeOfDateTest {

    @Test
    public void testFormatStartDate() throws Exception {
        RangeOfDate rangeOfDate = new RangeOfDate(7);
        Method method = RangeOfDate.class.getDeclaredMethod("parseOrDefault", String.class, Instant.class);
        method.setAccessible(true);

        String startDate = "2023-02-13T00:00:00Z";
        Instant expectedStartDate = Instant.parse(startDate);
        Instant actualStartDate = (Instant) method.invoke(rangeOfDate, startDate, Instant.now());
        assertEquals(expectedStartDate, actualStartDate);

        String nullStartDate = null;
        Instant defaultStartDate = Instant.now().minus(7, ChronoUnit.DAYS);
        Instant actualDefaultStartDate = (Instant) method.invoke(rangeOfDate, nullStartDate, defaultStartDate);
        assertEquals(defaultStartDate, actualDefaultStartDate);
    }

    @Test
    public void testFormatEndDate() throws Exception {
        RangeOfDate rangeOfDate = new RangeOfDate(7);
        Method method = RangeOfDate.class.getDeclaredMethod("parseOrDefault", String.class, Instant.class);
        method.setAccessible(true);

        String endDate = "2023-02-20T00:00:00Z";
        Instant expectedEndDate = Instant.parse(endDate);
        Instant actualEndDate = (Instant) method.invoke(rangeOfDate, endDate, Instant.now());
        assertEquals(expectedEndDate, actualEndDate);

        String nullEndDate = null;
        Instant defaultEndDate = Instant.now().plus(7, ChronoUnit.DAYS);
        Instant actualDefaultEndDate = (Instant) method.invoke(rangeOfDate, nullEndDate, defaultEndDate);
        assertEquals(defaultEndDate, actualDefaultEndDate);
    }
}
