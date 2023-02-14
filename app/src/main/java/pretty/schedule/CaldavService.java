package pretty.schedule;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Description;

@Service
public class CaldavService {

    public Calendar getCalendarFromJsonResource(String jsonUrl) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(jsonUrl, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            VEvent vevent = new VEvent();
            vevent.getProperties().add(new Description(jsonNode.get("property1").asText()));
            vevent.getProperties().add(new Description(jsonNode.get("property2").asText()));
            Calendar calendar = new Calendar();
            List<VEvent> events = new ArrayList<VEvent>();
            events.add(vevent);
            calendar.getComponents().addAll(events);

            return calendar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
