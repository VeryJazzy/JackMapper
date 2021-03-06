package BusApp.Json;

import BusApp.Bus;
import BusApp.TflTrain;
import BusApp.Time;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public static ArrayList<Bus> parseJsonBus(String json) {
        List<JsonBus> jsonBuses = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonBuses = objectMapper.readValue(json, new TypeReference<List<JsonBus>>() {
            });
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

    public static ArrayList<TflTrain> parseJsonTrain(String json) {
        List<JsonTrain> jsonTrainList = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonTrainList = objectMapper.readValue(json, new TypeReference<List<JsonTrain>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        ArrayList<TflTrain> tflTrainList = new ArrayList<>();
        assert jsonTrainList != null;

        for (JsonTrain jTrain : jsonTrainList) {
            //checks its outbound
            if (jTrain.getTimeDeparting() == null) {
                continue;
            }

            TflTrain tflTrain = new TflTrain(jTrain.getDestination(), jTrain.getPlatform());

            int time = Time.howLong(jTrain.getTimeDeparting());
            tflTrain.addTime(time);
            tflTrainList.add(tflTrain);
            LocalTime expectedArrival = Time.parseTime(jTrain.getTimeDeparting());

            tflTrain.setTimeArriving(expectedArrival.toString());
        }
        return tflTrainList;
    }


}
