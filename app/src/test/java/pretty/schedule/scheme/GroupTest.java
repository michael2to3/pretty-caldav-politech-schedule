package pretty.schedule.scheme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class GroupTest {
  private Group getGroup() {
    int id = 0;
    String name = "name";
    int level = 0;
    String type = "type";
    int kind = 0;
    String spec = "spec";
    int year = 2020;
    String abbr = "abbr";
    Faculty faculty = new Faculty(id, name, abbr);
    Group lhs = new Group(id, name, level, type, kind, spec, year, faculty);

    return lhs;
  }

  @Test
  void testGettersAndSetters() {
    int id = 0;
    String name = "name";
    int level = 0;
    String type = "type";
    int kind = 0;
    String spec = "spec";
    int year = 2020;
    String abbr = "abbr";
    Faculty faculty = new Faculty(id, name, abbr);
    Group lhs = new Group();
    lhs.setId(id);
    lhs.setName(name);
    lhs.setLevel(level);
    lhs.setType(type);
    lhs.setKind(kind);
    lhs.setSpec(spec);
    lhs.setYear(year);
    lhs.setFaculty(faculty);
    assertEquals(id, lhs.getId());
    assertEquals(name, lhs.getName());
    assertEquals(level, lhs.getLevel());
    assertEquals(type, lhs.getType());
    assertEquals(kind, lhs.getKind());
    assertEquals(spec, lhs.getSpec());
    assertEquals(year, lhs.getYear());
    assertEquals(faculty, lhs.getFaculty());
  }

  @Test
  void testCompare() {
    Group lhs = getGroup();
    Group rhs = getGroup();
    Group other = getGroup();
    other.setId(123);

    assertEquals(lhs, rhs);
    assertNotEquals(lhs, other);
    assertNotEquals(lhs, null);
    assertEquals(lhs.hashCode(), rhs.hashCode());
    assertNotEquals(lhs.hashCode(), other.hashCode());
  }

}
