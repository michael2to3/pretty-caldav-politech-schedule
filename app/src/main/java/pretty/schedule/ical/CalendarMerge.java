package pretty.schedule.ical;

import java.util.ArrayList;
import java.util.List;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.CalendarComponent;

public class CalendarMerge {
  public static Calendar merge(Calendar lhs, Calendar rhs) {
    if (rhs == null) {
      return lhs;
    }
    if (lhs == null) {
      return rhs;
    }
    Calendar mergedCalendar = new Calendar();
    List<CalendarComponent> mergedComponents = new ArrayList<>(lhs.getComponents());

    mergedComponents.addAll(rhs.getComponents());

    mergedCalendar.getComponents().addAll(mergedComponents);
    return mergedCalendar;
  }
}
