package pretty.schedule.scheme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AuditorieTest {
  private Building getBuilding() {
    Building b = new Building(0, "name", "build", "build");
    return b;
  }

  @Test
  @DisplayName("Test auditorie with id, name and build fields")
  void testCompare() {
    Auditorie a1 = new Auditorie(0, "name", getBuilding());
    Auditorie a2 = new Auditorie(0, "name", getBuilding());
    Auditorie a3 = new Auditorie(1, "name", getBuilding());
    assertEquals(a1, a2);
    assertNotEquals(a1, a3);
  }

  @Test
  void testGetters() {
    int id = 0;
    String name = "name";
    Building building = getBuilding();
    Auditorie a1 = new Auditorie(id, name, building);
    assertEquals(id, a1.getId());
    assertEquals(name, a1.getName());
    assertEquals(building, a1.getBuilding());
  }

  @Test
  void testSetters() {
    int id = 0;
    String name = "name";
    Building building = getBuilding();
    Building other = new Building(321, "othername", "otherbuild", "otherbuild");
    Auditorie a1 = new Auditorie(123, "othername", other);
    a1.setId(id);
    a1.setName(name);
    a1.setBuilding(building);
    assertEquals(id, a1.getId());
    assertEquals(name, a1.getName());
    assertEquals(building, a1.getBuilding());
  }
}
