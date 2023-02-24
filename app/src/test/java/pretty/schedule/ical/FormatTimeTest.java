package pretty.schedule.ical;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormatTimeTest {
    private static final int OFFSET = 3;

    @Test
    public void testFormatTime() {
        FormatTime formatTime = new FormatTime("23:45");
        assertEquals(23 - OFFSET, formatTime.getHour());
        assertEquals(45, formatTime.getMinute());
    }
}
