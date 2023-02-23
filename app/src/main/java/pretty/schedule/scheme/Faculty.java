package pretty.schedule.scheme;

/** Represents a faculty in a univeristy. */
public class Faculty {
  /** The ID of the faculty. */
  private int id;
  /** The name of the faculty. */
  private String name;
  /** The department of the faculty. */
  private String abbr;

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
