package pretty.schedule.scheme;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Represents a teacher with its relavant information. */
public class Teacher {
  /** The ID of the teacher. */
  private int id;
  /** The OID of the teacher. */
  private int oid;
  /** The full name of the teacher. */
  @JsonProperty("full_name")
  private String fullName;
  /** The first name of the teacher. */
  @JsonProperty("first_name")
  private String firstName;
  /** The middle name of the teacher. */
  @JsonProperty("middle_name")
  private String middleName;
  /** The last name of the teacher. */
  @JsonProperty("last_name")
  private String lastName;
  /** The grade of the teacher. */
  private String grade;
  /** The chait of the teacher. */
  private String chair;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getOid() {
    return oid;
  }

  public void setOid(int oid) {
    this.oid = oid;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public String getChair() {
    return chair;
  }

  public void setChair(String chair) {
    this.chair = chair;
  }
}
