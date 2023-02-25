package pretty.schedule.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import pretty.schedule.scheme.Group;
import pretty.schedule.scheme.ScheduleOfWeek;

@SpringBootTest
@ActiveProfiles("test")
class ScheduleMapTest {
  @Autowired
  ScheduleMap scheduleMap;
  final String faculty = "3530904";
  final String group = "10006";
  final String name = faculty + "/" + group;

  @Test
  public void testIndex() {
    assertNotEquals(null, scheduleMap.index());
    assertNotEquals("", scheduleMap.index());
  }

  @Test
  public void testGetGroupsById() throws IOException {
    List<Group> groups = scheduleMap.getGroupsById("100");
    assertTrue(groups.size() > 0);
    assertNotEquals(null, groups.get(0));
  }

  @Test
  public void testGetGroupByName() throws IOException {
    Group gr = scheduleMap.getGroupByName(faculty, group);
    assertEquals(name, gr.getName());
  }

  @Test
  public void testGetScheduleById() throws IOException {
    List<ScheduleOfWeek> schedules = scheduleMap.getScheduleByIdAsJson("35610", null, null);
    assertTrue(schedules.size() > 0);
    assertNotEquals(null, schedules.get(0).getGroup().getName());
    assertNotEquals("", schedules.get(0).getGroup().getName());
  }

  @Test
  public void testGetScheduleByName() throws IOException {
    List<ScheduleOfWeek> schedules = scheduleMap.getScheduleByNameAsJson(faculty, group, null, null);
    assertTrue(schedules.size() > 0);
    assertEquals(name, schedules.get(0).getGroup().getName());
  }

  @Test
  public void testGetScheduleByIdAsIcal() throws IOException {
    String icalString = scheduleMap.getScheduleByIdAsIcal("36510", null, null);
    assertTrue(icalString.startsWith("BEGIN:VCALENDAR"));
  }
}
