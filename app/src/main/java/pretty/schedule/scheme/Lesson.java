package pretty.schedule.scheme;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/** A class representing a single lesson in the schedule. */
public class Lesson {
  /** The subject of the lesson. */
  private String subject;
  /** The abbreviated name of the lesson. */
  @JsonProperty("subject_short")
  private String subjectShort;
  /** The type of the lesson(e.g. "Lecture", "Lab", etc.). */
  private int type;
  /** Additional information about the lesson. */
  @JsonProperty("additional_info")
  private String additionalInfo;
  /** The start time of the lesson in HH:mm format */
  @JsonProperty("time_start")
  private String timeStart;
  /** The end time of the lesson in HH:mm format */
  @JsonProperty("time_end")
  private String timeEnd;
  /** The parity of the lesson(even, odd, etc.). */
  private int parity;
  /** The type of the educational object */
  private TypeObj typeObj;
  /** The list of groups attending the lesson. */
  private List<Group> groups;
  /** The list of teachers conducting the lesson. */
  private List<Teacher> teachers;
  /** The list of auditoriums where are the lesson takes place. */
  private List<Auditorie> auditories;
  /** The URL of the webinar for the lesson(if applicable). */
  @JsonProperty("webinar_url")
  private String webinarUrl;
  /** The URL of the lms for the lesson(if applicable). */
  @JsonProperty("lms_url")
  private String lmsUrl;

  public Lesson(final Lesson lesson) {
    this.subject = lesson.subject;
    this.subjectShort = lesson.subjectShort;
    this.type = lesson.type;
    this.additionalInfo = lesson.additionalInfo;
    this.timeStart = lesson.timeStart;
    this.timeEnd = lesson.timeEnd;
    this.parity = lesson.parity;
    this.typeObj = lesson.typeObj;
    this.groups = lesson.groups;
    this.teachers = lesson.teachers;
    this.auditories = lesson.auditories;
    this.webinarUrl = lesson.webinarUrl;
    this.lmsUrl = lesson.lmsUrl;
  }

  public Lesson() {
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Lesson))
      return false;
    final Lesson lesson = (Lesson) o;
    return subject.equals(lesson.subject) &&
        subjectShort.equals(lesson.subjectShort) &&
        type == lesson.type &&
        additionalInfo.equals(lesson.additionalInfo) &&
        timeStart.equals(lesson.timeStart) &&
        timeEnd.equals(lesson.timeEnd) &&
        parity == lesson.parity &&
        typeObj == lesson.typeObj &&
        groups.equals(lesson.groups) &&
        teachers.equals(lesson.teachers) &&
        auditories.equals(lesson.auditories) &&
        webinarUrl.equals(lesson.webinarUrl) &&
        lmsUrl.equals(lesson.lmsUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subject, subjectShort, type, additionalInfo, timeStart, timeEnd, parity, typeObj, groups,
        teachers, auditories, webinarUrl, lmsUrl);
  }

  public String getLmsUrl() {
    return lmsUrl;
  }

  public void setLmsUrl(String lmsUrl) {
    this.lmsUrl = lmsUrl;
  }

  public String getSubjectShort() {
    return subjectShort;
  }

  public void setSubjectShort(String subjectShort) {
    this.subjectShort = subjectShort;
  }

  public String getAdditionalInfo() {
    return additionalInfo;
  }

  public void setAdditionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
  }

  public String getTimeStart() {
    return timeStart;
  }

  public void setTimeStart(String timeStart) {
    this.timeStart = timeStart;
  }

  public String getTimeEnd() {
    return timeEnd;
  }

  public void setTimeEnd(String timeEnd) {
    this.timeEnd = timeEnd;
  }

  public String getWebinarUrl() {
    return webinarUrl;
  }

  public void setWebinarUrl(String webinarUrl) {
    this.webinarUrl = webinarUrl;
  }

  public List<Auditorie> getAuditories() {
    return auditories;
  }

  public void setAuditories(List<Auditorie> auditories) {
    this.auditories = auditories;
  }

  public List<Teacher> getTeachers() {
    return teachers;
  }

  public void setTeachers(List<Teacher> teachers) {
    this.teachers = teachers;
  }

  public TypeObj getTypeObj() {
    return typeObj;
  }

  public void setTypeObj(TypeObj typeObj) {
    this.typeObj = typeObj;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public int getParity() {
    return parity;
  }

  public void setParity(int parity) {
    this.parity = parity;
  }

  public List<Group> getGroups() {
    return groups;
  }

  public void setGroups(List<Group> groups) {
    this.groups = groups;
  }
}
