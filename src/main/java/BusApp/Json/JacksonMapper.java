package BusApp.Json;

import BusApp.Bus;
import BusApp.Train;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JacksonMapper {

    public static List<Bus> extractJsonBuses(String json) {
        List<Bus> jsonBuses = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonBuses = objectMapper.readValue(json, new TypeReference<List<Bus>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonBuses;
    }

    public static List<JsonTrain> extractJsonTrains(String json) {
        List<JsonTrain> jsonTrains = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonTrains = objectMapper.readValue(json, new TypeReference<List<JsonTrain>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonTrains;
    }
}
