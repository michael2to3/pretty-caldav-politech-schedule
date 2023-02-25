package pretty.schedule.controllers;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;
import pretty.schedule.ical.Ical;
import pretty.schedule.scheme.Faculty;
import pretty.schedule.scheme.Group;
import pretty.schedule.scheme.ScheduleOfWeek;
import pretty.schedule.scraper.RangeOfDate;
import pretty.schedule.scraper.Scraper;

@RestController
@RequestMapping("/schedule/")
class ScheduleMap {
  private Scraper scraper;
  private RangeOfDate rangeOfDate;
  @Value("${application.source}")
  private String source;
  @Value("${application.target}")
  private String target;

  @PostConstruct
  public void init() {
    scraper = new Scraper(target);
    this.rangeOfDate = new RangeOfDate(6 * 7 * 7);
  }

  @GetMapping("/")
  @ResponseBody
  public String index() {
    return "This a schedule service, you can check documentation at: " + source;
  }

  @GetMapping("/groups/id/{group}")
  @ResponseBody
  public List<Group> getGroupsById(@PathVariable("group") final String group) throws IOException {
    return scraper.getGroups(group);
  }

  @GetMapping("/group/{faculty}/{group}")
  @ResponseBody
  public Group getGroupByName(
      @PathVariable("faculty") final String faculty,
      @PathVariable("group") final String groupOfName) throws IOException {
    return scraper.getGroupByName(faculty + "/" + groupOfName);
  }

  @GetMapping("/json/group/id/{group}")
  @ResponseBody
  public List<ScheduleOfWeek> getScheduleByIdAsJson(
      @PathVariable("group") final String group,
      @RequestParam("start") final String start,
      @RequestParam("end") final String end) throws IOException {
    Instant startInstant = rangeOfDate.formatStartDate(start);
    Instant endInstant = rangeOfDate.formatEndDate(end);
    return scraper.getRangeScheduleById(group, startInstant, endInstant);
  }

  @GetMapping("/json/{faculty}/{group}")
  @ResponseBody
  public List<ScheduleOfWeek> getScheduleByNameAsJson(
      @PathVariable("faculty") final String faculty,
      @PathVariable("group") final String group,
      @RequestParam(value = "start", required = false) final String start,
      @RequestParam(value = "end", required = false) final String end) throws IOException {
    Instant startInstant = rangeOfDate.formatStartDate(start);
    Instant endInstant = rangeOfDate.formatEndDate(end);
    String name = faculty + "/" + group;
    return scraper.getRangeScheduleByName(name, startInstant, endInstant);
  }

  @GetMapping(value = "/ical/id/{group}", produces = "text/calendar")
  @ResponseBody
  public String getScheduleByIdAsIcal(
      @PathVariable("group") final String group,
      @RequestParam("start") final String start,
      @RequestParam("end") final String end) throws IOException {
    Instant startInstant = rangeOfDate.formatStartDate(start);
    Instant endInstant = rangeOfDate.formatEndDate(end);
    final var schedule = scraper.getRangeScheduleById(group, startInstant, endInstant);
    Ical ical = new Ical("Schedule of " + group, schedule);
    return ical.getCalendar().toString();
  }

  @GetMapping(value = "/ical/{faculty}/{group}", produces = "text/calendar")
  @ResponseBody
  public String getScheduleByNameAsIcal(
      @PathVariable("faculty") final String faculty,
      @PathVariable("group") final String group,
      @RequestParam(value = "start", required = false) final String start,
      @RequestParam(value = "end", required = false) final String end) throws IOException {

    Instant startInstant = rangeOfDate.formatStartDate(start);
    Instant endInstant = rangeOfDate.formatEndDate(end);

    final String name = faculty + "/" + group;
    final var schedule = scraper.getRangeScheduleByName(name, startInstant, endInstant);
    Ical ical = new Ical("Schedule of " + name, schedule);
    return ical.getCalendar().toString();
  }

  @GetMapping("/faculties")
  @ResponseBody
  public List<Faculty> getFaculties() throws IOException {
    return scraper.getFaculties();
  }
}
