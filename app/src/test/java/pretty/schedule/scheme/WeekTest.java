package pretty.schedule.scheme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class WeekTest {
  @Test
  void testGettersAndSetters() {
    String dateStart = "2020-01-01";
    String dateEnd = "2020-01-07";
    boolean isOdd = true;
    Week week = new Week();
    week.setDateStart(dateStart);
    week.setDateEnd(dateEnd);
    week.setOdd(isOdd);
    assertEquals(dateStart, week.getDateStart());
    assertEquals(dateEnd, week.getDateEnd());
    assertEquals(isOdd, week.isOdd());
  }

  @Test
  void testCompare() {
    Week lhs = new Week("2020-01-01", "2020-01-07", true);
    Week rhs = new Week("2020-01-01", "2020-01-07", true);
    Week other = new Week("9999-01-01", "9999-01-07", false);

    assertEquals(lhs, rhs);
    assertEquals(lhs.hashCode(), rhs.hashCode());
    assertNotEquals(lhs.hashCode(), other.hashCode());
    assertNotEquals(lhs, other);
    assertNotEquals(lhs, null);
  }
}
