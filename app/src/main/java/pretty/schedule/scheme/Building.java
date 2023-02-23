package pretty.schedule.scheme;

public class Building {
    private int id;
    private String name;
    private String abbr;
    private String address;

    @Override
    public String toString() {
        return String.format("%s, %s, %s", name, abbr, address);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
