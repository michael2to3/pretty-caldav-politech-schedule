package pretty.schedule.scheme;

import java.util.Objects;

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

  public Teacher() {
  }

  public Teacher(int id, int oid, String fullName, String firstName, String middleName, String lastName, String grade,
      String chair) {
    this.id = id;
    this.oid = oid;
    this.fullName = fullName;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.grade = grade;
    this.chair = chair;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Teacher))
      return false;
    Teacher teacher = (Teacher) o;
    return id == teacher.id &&
        oid == teacher.oid &&
        fullName.equals(teacher.fullName) &&
        firstName.equals(teacher.firstName) &&
        middleName.equals(teacher.middleName) &&
        lastName.equals(teacher.lastName) &&
        grade.equals(teacher.grade) &&
        chair.equals(teacher.chair);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, oid, fullName, firstName, middleName, lastName, grade, chair);
  }

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
