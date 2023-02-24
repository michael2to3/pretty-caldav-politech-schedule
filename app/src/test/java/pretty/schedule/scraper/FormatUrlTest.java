package pretty.schedule.scraper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;

public class FormatUrlTest {
    private static final String DOMAIN = "http://example.com";
    private static final String NUMBER = "123";
    private static final Instant DATE = LocalDateTime.of(2023, 2, 14, 0, 0).toInstant(ZoneOffset.UTC);
    private static final String EXPECTED_FACULTIES_URL = DOMAIN + "/api/v1/ruz/faculties";
    private static final String EXPECTED_GROUPS_URL = DOMAIN + "/api/v1/ruz/faculties/123/groups";
    private static final String EXPECTED_SCHEDULE_URL = DOMAIN + "/api/v1/ruz/scheduler/123?date=2023-2-14";

    @Test
    void testGetFaculties() {
        String result = FormatUrl.getFaculties(DOMAIN);
        assertEquals(EXPECTED_FACULTIES_URL, result);
    }

    @Test
    void testGetGroups() {
        String result = FormatUrl.getGroups(DOMAIN, NUMBER);
        assertEquals(EXPECTED_GROUPS_URL, result);
    }

    @Test
    void testGetSchedule() {
        String result = FormatUrl.getSchedule(DOMAIN, NUMBER, DATE);
        assertEquals(EXPECTED_SCHEDULE_URL, result);
    }
}
