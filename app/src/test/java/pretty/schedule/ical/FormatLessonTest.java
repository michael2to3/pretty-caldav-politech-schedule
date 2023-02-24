package pretty.schedule.ical;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import pretty.schedule.scheme.Lesson;

public class FormatLessonTest {
  private static final int OFFSET = FactoryTimeZone.getOffsetAsHour();

  @Test
  public void testGetDate() {
    FormatLesson formatLesson = new FormatLesson(new Lesson());
    Calendar expected = new GregorianCalendar();
    expected.setTimeZone(FactoryTimeZone.getTz());
    expected.set(Calendar.MILLISECOND, 0);
    expected.set(Calendar.SECOND, 0);
    expected.set(Calendar.MINUTE, 0);
    expected.set(Calendar.HOUR_OF_DAY, 12 - OFFSET);
    expected.set(Calendar.DAY_OF_MONTH, 14);
    expected.set(Calendar.MONTH, 1);
    expected.set(Calendar.YEAR, 2023);

    Calendar actual = formatLesson.getDate("12:00", 14, 2, 2023);
    assertEquals(expected.getTime(), actual.getTime());
    assertEquals(expected.get(Calendar.DAY_OF_MONTH), actual.get(Calendar.DAY_OF_MONTH));
    assertEquals(expected.get(Calendar.MONTH), actual.get(Calendar.MONTH));
    assertEquals(expected.get(Calendar.YEAR), actual.get(Calendar.YEAR));
  }
}
