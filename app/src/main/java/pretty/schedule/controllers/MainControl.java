package pretty.schedule.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pretty.schedule.handleController.HandlerSchedule;
import pretty.schedule.scheme.ErrorResponse;
import pretty.schedule.util.Json;

@RestController
@RequestMapping("/v1/")
public class MainControl {
  @Autowired
  private Environment env;
  private static final Logger LOGGER = LoggerFactory.getLogger(MainControl.class);
  private HandlerSchedule handler;
  private HttpHeaders headers;

  @PostConstruct
  public void init() {
    handler = new HandlerSchedule(env.getProperty("application.source"));
    headers = new HttpHeaders();
    headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
    headers.add(HttpHeaders.PRAGMA, "no-cache");
    headers.add(HttpHeaders.EXPIRES, "0");
  }

  @GetMapping("/source/")
  public ResponseEntity<Resource> getSource() {
    String source = env.getProperty("application.source");
    ByteArrayResource res = new ByteArrayResource(source.getBytes());
    return ResponseEntity.ok().contentLength(res.contentLength()).body(res);
  }

  @GetMapping("/sch/{groupId}")
  public ResponseEntity<Resource> getSch(@PathVariable final String groupId,
      @RequestParam(required = false) String startDate,
      @RequestParam(required = false) String endDate) {
    String scheduleJson = "";
    ErrorResponse eResp = null;
    Resource resource = null;
    try {
      scheduleJson = handler.generateScheduleJson(groupId, startDate, endDate);
    } catch (JsonParseException e) {
      eResp = new ErrorResponse("error", "JSON is not valid");
    } catch (JsonMappingException e) {
      eResp = new ErrorResponse("error", "JSON mapping is corrupted");
    } catch (IOException e) {
      eResp = new ErrorResponse("error", "IO operation is corrupted");
    }
    if (eResp != null) {
      LOGGER.error(eResp.getMessage());
      resource = new ByteArrayResource(Json.convertString(eResp).getBytes());
      return ResponseEntity.badRequest().headers(headers).body(resource);
    } else {
      resource = new ByteArrayResource(scheduleJson.getBytes());
      headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
      return ResponseEntity.ok().headers(headers).body(resource);
    }
  }

  @GetMapping(value = "/sch/{groupId}/ics", produces = "text/calendar")
  public ResponseEntity<Resource> getSchIcs(@PathVariable final String groupId,
      @RequestParam(required = false) String startDate,
      @RequestParam(required = false) String endDate) {
    ErrorResponse eResp = null;
    Resource resource = null;
    try {
      Calendar calendar = handler.generateScheduleIcal(groupId, startDate, endDate);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      var outputter = new CalendarOutputter();
      outputter.output(calendar, baos);
      resource = new ByteArrayResource(baos.toByteArray());
    } catch (JsonParseException e) {
      eResp = new ErrorResponse("error", "JSON is not valid");
    } catch (JsonMappingException e) {
      eResp = new ErrorResponse("error", "JSON mapping is corrupted");
    } catch (IOException e) {
      eResp = new ErrorResponse("error", "IO operation is corrupted");
    }
    if (eResp != null) {
      LOGGER.error(eResp.getMessage());
      resource = new ByteArrayResource(Json.convertString(eResp).getBytes());
      return ResponseEntity.badRequest().headers(headers).body(resource);
    } else {
      headers.add(HttpHeaders.CONTENT_TYPE, "text/calendar");
      return ResponseEntity.ok().headers(headers).body(resource);
    }
  }

  @GetMapping("/facultics/")
  public ResponseEntity<Resource> getFacultics() {
    Resource resource = null;
    long len = 0;
    try {
      String facultics = handler.generateFacultiesJson();
      resource = new ByteArrayResource(facultics.getBytes());
      len = resource.contentLength();
    } catch (IOException e) {
      LOGGER.error(e.getMessage());
      resource = new ByteArrayResource(
          Json.convertString(new ErrorResponse("error", "IO operation is corrupted")).getBytes());
    }
    return ResponseEntity.ok().headers(headers).contentLength(len).body(resource);
  }

  @GetMapping("/group/{nameOfFacult}/{nameOfGroup}")
  public ResponseEntity<Resource> getGroupOfName(
      @PathVariable final String nameOfFacult, @PathVariable final String nameOfGroup) {
    String groups = null;
    try {
      groups = handler.generateGroupOfNameJson(nameOfFacult + "/" + nameOfGroup);
    } catch (IOException e) {
      LOGGER.error(e.toString());
      var error = new ErrorResponse("error", "IO operation is corrupted");
      var eResp = new ByteArrayResource(Json.convertString(error).getBytes());
      return ResponseEntity.badRequest().contentLength(eResp.contentLength()).body(eResp);
    }
    var resource = new ByteArrayResource(groups.getBytes());
    return ResponseEntity.ok().contentLength(resource.contentLength()).body(resource);
  }

  @GetMapping("/group/sch/{nameOfFacult}/{nameOfGroup}")
  public ResponseEntity<Resource> getGroupOfNameSch(@PathVariable final String nameOfFacult,
      @PathVariable final String nameOfGroup, @RequestParam(required = false) String startDate,
      @RequestParam(required = false) String endDate) {
    String groups = null;
    try {
      groups = handler.generateScheduleOfNameJson(nameOfFacult + "/" + nameOfGroup, startDate, endDate);
    } catch (IOException e) {
      LOGGER.error(e.toString());
      var error = new ErrorResponse("error", "IO operation is corrupted");
      var eResp = new ByteArrayResource(Json.convertString(error).getBytes());
      return ResponseEntity.badRequest().contentLength(eResp.contentLength()).body(eResp);
    }
    var resource = new ByteArrayResource(groups.getBytes());
    return ResponseEntity.ok().contentLength(resource.contentLength()).body(resource);
  }

  @GetMapping("/group/sch/{nameOfFacult}/{nameOfGroup}/ics")
  public ResponseEntity<Resource> getGroupOfNameSchIcs(@PathVariable final String nameOfFacult,
      @PathVariable final String nameOfGroup, @RequestParam(required = false) String startDate,
      @RequestParam(required = false) String endDate) {
    String groups = null;
    try {
      groups = handler.generateScheduleOfNameGroupIcal(
          nameOfFacult + "/" + nameOfGroup, startDate, endDate);
    } catch (IOException e) {
      LOGGER.error(e.toString());
      var error = new ErrorResponse("error", "IO operation is corrupted");
      var eResp = new ByteArrayResource(Json.convertString(error).getBytes());
      return ResponseEntity.badRequest().contentLength(eResp.contentLength()).body(eResp);
    }
    var resource = new ByteArrayResource(groups.getBytes());
    return ResponseEntity.ok().contentLength(resource.contentLength()).body(resource);
  }
}
