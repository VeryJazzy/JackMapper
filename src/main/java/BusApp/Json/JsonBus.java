//package BusApp.Json;
//
//import java.util.HashMap;
//import java.util.Map;
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//public class JsonBus {
//
//    @JsonProperty("lineName")
//    private String name;
//    @JsonProperty("expectedArrival")
//    private String expectedArrival;
//
//    public String getName() {
//        return name;
//    }
//
//    public String getExpectedArrival() {
//        return expectedArrival;
//    }
//
//
//    @JsonIgnore
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
//
//    @JsonAnySetter
//    public void setAdditionalProperty(String name, Object value) {
//        this.additionalProperties.put(name, value);
//    }
//
//}
