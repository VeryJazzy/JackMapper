package BusApp.Json;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonTrain {

    @JsonProperty("platformName")
    private String platform;
    @JsonProperty("destinationName")
    private String destination;
    @JsonProperty("estimatedTimeOfDeparture")
    private String timeDeparting;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("platformName")
    public String getPlatform() {
        return platform;
    }

    @JsonProperty("destinationName")
    public String getDestination() {
        return destination;
    }

    @JsonProperty("estimatedTimeOfDeparture")
    public String getTimeDeparting() {
        return timeDeparting;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
