package pretty.schedule.scheme;

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
