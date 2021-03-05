package BusApp.Json;

import BusApp.Bus;
import BusApp.Time;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public static ArrayList<Bus> parseJson(String json) {
        List<JsonBus> jsonBuses = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonBuses = objectMapper.readValue(json, new TypeReference<List<JsonBus>>(){});
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Bus> busList = new ArrayList<>();
        assert jsonBuses != null;

        for (JsonBus jBus : jsonBuses) {
            Bus bus = new Bus(jBus.getName());

            if (busList.contains(bus)) {
                for (Bus b : busList) {
                    if (b.equals(bus)) {

                        int time = Time.howLong(jBus.getExpectedArrival());
                        b.addTime(time);
                    }
                }
            } else {
                int time = Time.howLong(jBus.getExpectedArrival());
                bus.addTime(time);
                busList.add(bus);
            }
        }
        return busList;
    }

 
}
