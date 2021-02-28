package BusApp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class Json {


    static List<Bus> parseJson(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Bus> buses = objectMapper.readValue(json, new TypeReference<List<Bus>>(){});
            return buses;

        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }
}
