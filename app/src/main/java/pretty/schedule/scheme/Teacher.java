package pretty.schedule.scheme;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Teacher {
  private int id;
  private int oid;
  @JsonProperty("full_name") private String fullName;
  @JsonProperty("first_name") private String firstName;
  @JsonProperty("middle_name") private String middleName;
  @JsonProperty("last_name") private String lastName;
  private String grade;
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
