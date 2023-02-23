package pretty.schedule.ical;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Summary;

public class CalendarMergeTest {

  @Test
  public void testMergeWithNonNullCalendars() throws ParseException {
    Calendar lhs = new Calendar();
    Calendar rhs = new Calendar();
    VEvent event1 = new VEvent();
    event1.getProperties().add(new Summary("Event 1"));
    VEvent event2 = new VEvent();
    event2.getProperties().add(new Summary("Event 2"));
    lhs.getComponents().add(event1);
    rhs.getComponents().add(event2);

    Calendar expected = new Calendar();
    expected.getComponents().add(event1);
    expected.getComponents().add(event2);

    Calendar actual = CalendarMerge.merge(lhs, rhs);

    assertEquals(expected, actual);
    assertIterableEquals(expected.getComponents(), actual.getComponents(), "Components should be the same instance");
  }

  @Test
  public void testMergeWithNullCalendars() {

    Calendar lhs = null;
    Calendar rhs = new Calendar();

    Calendar actual1 = CalendarMerge.merge(lhs, rhs);
    Calendar actual2 = CalendarMerge.merge(rhs, lhs);

    assertSame(rhs, actual1);
    assertSame(rhs, actual2);
  }

}
