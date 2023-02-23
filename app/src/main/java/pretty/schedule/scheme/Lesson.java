package pretty.schedule.scheme;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Lesson {
    private String subject;
    @JsonProperty("subject_short")
    private String subjectShort;
    private int type;
    @JsonProperty("additional_info")
    private String additionalInfo;
    @JsonProperty("time_start")
    private String timeStart;
    @JsonProperty("time_end")
    private String timeEnd;
    private int parity;
    private TypeObj typeObj;
    private List<Group> groups;
    private List<Teacher> teachers;
    private List<Auditorie> auditories;
    @JsonProperty("webinar_url")
    private String webinarUrl;
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
