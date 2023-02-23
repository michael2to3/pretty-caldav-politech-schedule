package pretty.schedule.scheme;

/** Represents the type of an object, such as a lesson. */
public class TypeObj {
  /** The ID of the type object. */
  private int id;
  /** The name of the type object. */
  private String name;
  /** The abbreviation of the type object. */
  private String abbr;

  @Override
  public String toString() {
    return String.format("%s(%s)", name, abbr);
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
}
