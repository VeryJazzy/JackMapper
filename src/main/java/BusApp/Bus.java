package BusApp;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Bus {

    @JsonProperty("lineName")
    private String name;
    @JsonProperty("expectedArrival")
    private String expectedArrival;
    private List<Integer> howLongs = new ArrayList<>();

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getName() {
        return name;
    }

    public String getExpectedArrival() {
        return expectedArrival;
    }

    public List<Integer> getHowLongs() {
        return howLongs;
    }

    public void addTime(int time) {
        howLongs.add(time);
        Collections.sort(howLongs);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Bus)) {
            return false;
        }
        Bus busO = (Bus) o;
        if (this.name.equals(busO.name)) {
            return true;
        }
        return false;
    }

    public String getInfo() {
        StringBuilder info = new StringBuilder(this.name + ": ---- ");

        for (int i = 0; i < howLongs.size(); i++) {
            info.append(howLongs.get(i).equals(0) ? "due" : howLongs.get(i));
            info.append(i == howLongs.size() - 1 ? "" : ", ");
        }

        return info.toString();
    }

}
