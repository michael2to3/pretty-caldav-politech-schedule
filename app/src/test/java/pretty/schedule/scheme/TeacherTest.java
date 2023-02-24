package pretty.schedule.scheme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class TeacherTest {
  private Teacher getTeacher() {
    int id = 0;
    int oid = 0;
    String fullName = "fullName";
    String firstName = "first";
    String middleName = "middle";
    String lastName = "last";
    String grade = "grade";
    String chair = "chair";
    Teacher teacher = new Teacher(id, oid, fullName, firstName, middleName, lastName, grade, chair);
    return teacher;
  }

  @Test
  void testGetter() {
    int id = 0;
    int oid = 0;
    String fullName = "fullName";
    String firstName = "first";
    String middleName = "middle";
    String lastName = "last";
    String grade = "grade";
    String chair = "chair";
    Teacher teacher = new Teacher(id, oid, fullName, firstName, middleName, lastName, grade, chair);
    assertEquals(id, teacher.getId());
    assertEquals(oid, teacher.getOid());
    assertEquals(fullName, teacher.getFullName());
    assertEquals(firstName, teacher.getFirstName());
    assertEquals(middleName, teacher.getMiddleName());
    assertEquals(lastName, teacher.getLastName());
    assertEquals(grade, teacher.getGrade());
    assertEquals(chair, teacher.getChair());
  }

  @Test
  void testSetters() {
    int id = 0;
    int oid = 0;
    String fullName = "fullName";
    String firstName = "first";
    String middleName = "middle";
    String lastName = "last";
    String grade = "grade";
    String chair = "chair";

    Teacher teacher = new Teacher();
    teacher.setId(id);
    teacher.setOid(oid);
    teacher.setFullName(fullName);
    teacher.setFirstName(firstName);
    teacher.setMiddleName(middleName);
    teacher.setLastName(lastName);
    teacher.setGrade(grade);
    teacher.setChair(chair);

    assertEquals(id, teacher.getId());
    assertEquals(oid, teacher.getOid());
    assertEquals(fullName, teacher.getFullName());
    assertEquals(firstName, teacher.getFirstName());
    assertEquals(middleName, teacher.getMiddleName());
    assertEquals(lastName, teacher.getLastName());
    assertEquals(grade, teacher.getGrade());
    assertEquals(chair, teacher.getChair());
  }

  @Test
  void testCompare() {
    Teacher lhs = getTeacher();
    Teacher rhs = getTeacher();
    Teacher other = getTeacher();
    other.setId(123);

    assertEquals(lhs, rhs);
    assertNotEquals(lhs, other);
    assertNotEquals(lhs, null);
    assertEquals(lhs, lhs);
    assertEquals(lhs.hashCode(), rhs.hashCode());
    assertNotEquals(lhs.hashCode(), other.hashCode());
  }
}
