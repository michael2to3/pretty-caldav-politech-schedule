package pretty.schedule.Type;

public class Week {
    private String date_start;
    private String date_end;
    private boolean is_odd;

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public boolean isIs_odd() {
        return is_odd;
    }

    public void setIs_odd(boolean is_odd) {
        this.is_odd = is_odd;
    }
}
