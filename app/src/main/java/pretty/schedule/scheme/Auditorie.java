package pretty.schedule.scheme;

public class Auditorie {
  private int id;
  private String name;
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
