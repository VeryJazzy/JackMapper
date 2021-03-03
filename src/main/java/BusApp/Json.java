package BusApp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Json {

    static List<JsonBus> parseJson(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<JsonBus> jsonBuses = objectMapper.readValue(json, new TypeReference<List<JsonBus>>(){});
            return jsonBuses;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static ArrayList<Bus> parseJsonBuses(List<JsonBus> jsonBusList) {
        ArrayList<Bus> busList = new ArrayList<>();
        assert jsonBusList != null;

        for (JsonBus jBus : jsonBusList) {
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
