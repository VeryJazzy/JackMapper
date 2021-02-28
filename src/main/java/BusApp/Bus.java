package BusApp;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Bus {

    @JsonProperty("lineName")
    private String busNumber;
    @JsonProperty("towards")
    private String towards;
    @JsonProperty("expectedArrival")
    private String expectedArrival;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("lineName")

    public String getBusNumber() {
        return busNumber;
    }

    @JsonProperty("lineName")
    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    @JsonProperty("towards")
    public String getTowards() {
        return towards;
    }

    @JsonProperty("towards")
    public void setTowards(String towards) {
        this.towards = towards;
    }

    @JsonProperty("expectedArrival")
    public String getExpectedArrival() {
        return expectedArrival;
    }

    @JsonProperty("expectedArrival")
    public void setExpectedArrival(String expectedArrival) {
        this.expectedArrival = expectedArrival;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "bus name: " + busNumber + "\n" +
                "direction : " + towards + "\n" +
                "arrival time: " + expectedArrival;
    }



}
