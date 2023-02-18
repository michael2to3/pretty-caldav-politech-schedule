package pretty.schedule.scraper;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

class FormatUrl {
    private final static String PATTERN_DATE_FORMAT = "{DATE}";
    private final static String PATTERN_NUMBER = "{NUMBER}";
    private final static String GET_FACULTIES = "/api/v1/ruz/faculties";
    private final static String GET_GROUPS = "/api/v1/ruz/faculties/{NUMBER}/groups";
    private final static String GET_SCHEDULE = "/api/v1/ruz/scheduler/{NUMBER}?date={DATE}";
    private final static String PATTERN_FORMAT_DATE = "yyyy-M-dd";
    private final static DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern(PATTERN_FORMAT_DATE)
            .withLocale(Locale.getDefault()).withZone(ZoneId.systemDefault());

    private FormatUrl() {
    }

    public static String getFaculties(final String domain) {
        return domain + GET_FACULTIES;
    }

    public static String getGroups(final String domain, final String number) {
        return domain + GET_GROUPS.replace(PATTERN_NUMBER, number);
    }

    public static String getSchedule(final String domain, final String numGroup, final Instant date) {
        final String fdate = FORMAT_DATE.format(date);
        return domain + GET_SCHEDULE.replace(PATTERN_NUMBER, numGroup).replace(PATTERN_DATE_FORMAT, fdate);
    }

}
