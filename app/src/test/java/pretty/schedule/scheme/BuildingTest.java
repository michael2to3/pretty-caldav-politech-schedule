package pretty.schedule.scheme;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BuildingTest {
  @Test
  void testCompare() {
    int id = 0;
    String name = "name";
    String abbr = "abbr";
    String address = "address";
    Building lhs = new Building(id, name, abbr, address);
    Building rhs = new Building(id, name, abbr, address);
    assertEquals(lhs, rhs);
  }

  @Test
  void testGetters() {
    int id = 0;
    String name = "name";
    String abbr = "abbr";
    String address = "address";
    Building lhs = new Building(id, name, abbr, address);
    assertEquals(id, lhs.getId());
    assertEquals(name, lhs.getName());
    assertEquals(abbr, lhs.getAbbr());
    assertEquals(address, lhs.getAddress());
  }

  @Test
  void testSetters() {
    int id = 0;
    String name = "name";
    String abbr = "abbr";
    String address = "address";
    Building lhs = new Building(id, name, abbr, address);
    lhs.setId(id);
    lhs.setName(name);
    lhs.setAbbr(abbr);
    lhs.setAddress(address);
    assertEquals(id, lhs.getId());
    assertEquals(name, lhs.getName());
    assertEquals(abbr, lhs.getAbbr());
    assertEquals(address, lhs.getAddress());
  }
}
