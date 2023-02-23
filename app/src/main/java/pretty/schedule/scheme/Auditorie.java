package pretty.schedule.scheme;

/** Repsents an auditorium with its ID, name, and building information. */
public class Auditorie {
  /** The ID of the auditorium. */
  private int id;
  /** The name of the auditorium. */
  private String name;
  /** The building where the auditorium is located. */
  private Building building;

  @Override
  public String toString() {
    return String.format("%s, %s", name, building);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Building getBuilding() {
    return building;
  }

  public void setBuilding(Building building) {
    this.building = building;
  }
}
