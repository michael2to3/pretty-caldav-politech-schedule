package pretty.schedule.scheme;

import java.util.Objects;

/** Repsents an auditorium with its ID, name, and building information. */
public class Auditorie {
  /** The ID of the auditorium. */
  private int id;
  /** The name of the auditorium. */
  private String name;
  /** The building where the auditorium is located. */
  private Building building;

  public Auditorie() {
  }

  public Auditorie(int id, String name, Building building) {
    this.id = id;
    this.name = name;
    this.building = building;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Auditorie))
      return false;
    Auditorie auditorie = (Auditorie) o;
    return id == auditorie.id &&
        name.equals(auditorie.name) &&
        building.equals(auditorie.building);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, building);
  }

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
