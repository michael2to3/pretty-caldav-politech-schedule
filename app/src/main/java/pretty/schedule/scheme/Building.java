package pretty.schedule.scheme;

import java.util.Objects;

/** Repsents a building on campus. */
public class Building {
  /** The ID of the building. */
  private int id;
  /** The name of the building. */
  private String name;
  /** The full addres of the building. */
  private String abbr;
  /** The short address of the building. */
  private String address;

  public Building() {
  }

  public Building(int id, String name, String abbr, String address) {
    this.id = id;
    this.name = name;
    this.abbr = abbr;
    this.address = address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Building))
      return false;
    Building other = (Building) o;
    return id == other.id &&
        name.equals(other.name) &&
        abbr.equals(other.abbr) &&
        address.equals(other.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, abbr, address);
  }

  @Override
  public String toString() {
    return String.format("%s, %s, %s", name, abbr, address);
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

  public String getAbbr() {
    return abbr;
  }

  public void setAbbr(String abbr) {
    this.abbr = abbr;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
