package pretty.schedule.scheme;

/** Represents a study group */
public class Group {
  /** The union idenitfier of the group */
  private int id;
  /** The name of the group */
  private String name;
  /** The level of the group(e.g., bachelors, masters, etc.) */
  private int level;
  /** The type of the group(e.g., full-time, part-time, etc.) */
  private String type;
  /** The kind of the group(e.g., academic, non-academic, etc.) */
  private int kind;
  /**
   * The specializations of the group(e.g., computer science, mathematics, etc.)
   */
  private String spec;
  /** The year of the group */
  private int year;
  /** The faculty to which the group belongs. */
  private Faculty faculty;

  public Group() {
  }

  public Group(int id, String name, int level, String type, int kind, String spec, int year, Faculty faculty) {
    this.id = id;
    this.name = name;
    this.level = level;
    this.type = type;
    this.kind = kind;
    this.spec = spec;
    this.year = year;
    this.faculty = faculty;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Group))
      return false;
    Group g = (Group) o;
    return id == g.id && level == g.level && kind == g.kind && spec.equals(g.spec) && year == g.year
        && faculty.equals(g.faculty);
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

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getKind() {
    return kind;
  }

  public void setKind(int kind) {
    this.kind = kind;
  }

  public String getSpec() {
    return spec;
  }

  public void setSpec(String spec) {
    this.spec = spec;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public Faculty getFaculty() {
    return faculty;
  }

  public void setFaculty(Faculty faculty) {
    this.faculty = faculty;
  }
}
