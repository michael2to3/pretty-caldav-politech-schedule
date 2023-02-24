package pretty.schedule.scheme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class FacultyTest {
  @Test
  void testGettersAndSetters() {
    String name = "Test";
    int id = 0;
    String abbr = "TEST";

    Faculty faculty = new Faculty();
    faculty.setName(name);
    faculty.setId(id);
    faculty.setAbbr(abbr);
    assertEquals(name, faculty.getName());
    assertEquals(id, faculty.getId());
    assertEquals(abbr, faculty.getAbbr());
  }

  @Test
  void testCompare() {
    String name = "Test";
    int id = 0;
    String abbr = "TEST";
    String oname = "Other Test";
    Faculty lhs = new Faculty(id, name, abbr);
    Faculty rhs = new Faculty(id, name, abbr);
    Faculty other = new Faculty(id, oname, abbr);
    assertEquals(lhs, rhs);
    assertEquals(lhs.hashCode(), rhs.hashCode());
    assertNotEquals(lhs, other);
    assertNotEquals(lhs, null);
    assertNotEquals(lhs.hashCode(), other.hashCode());
  }
}
