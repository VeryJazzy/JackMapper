package BusApp.Json;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonBus {

    @JsonProperty("lineName")
    private String name;
    @JsonProperty("towards")
    private String towards;
    @JsonProperty("expectedArrival")
    private String expectedArrival;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("lineName")
    public String getName() {
        return name;
    }

    @JsonProperty("towards")
    public String getTowards() {
        return towards;
    }

    @JsonProperty("expectedArrival")
    public String getExpectedArrival() {
        return expectedArrival;
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
        return "bus name: " + name + "\n" +
                "direction : " + towards + "\n" +
                "arrival time: " + expectedArrival;
    }



}
