package BusApp.Json;

import BusApp.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalTime;
import java.util.*;

public class JsonParser {

    public static ArrayList<Bus> parseBusResponse(String json) {
        List<Bus> individualBuses = JacksonMapper.extractJsonBuses(json);
        HashMap<String, Bus> busMap = new HashMap<>();

        for (Bus bus : individualBuses) {
            int howLong = Time.howLong(bus.getExpectedArrival());

            if (busMap.containsValue(bus)) {
                busMap.get(bus.getName()).addTime(howLong);
            } else {
                bus.addTime(howLong);
                busMap.put(bus.getName(), bus);
            }
        }

        ArrayList<Bus> busList = new ArrayList<>(busMap.values());
        busList.sort(new BusComparator());
        return busList;
    }


    public static ArrayList<Train> parseTrainResponse(String json, String towards) {
        List<JsonTrain> jsonTrainList = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonTrainList = objectMapper.readValue(json, new TypeReference<List<JsonTrain>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Train> trainList = new ArrayList<>();
        assert jsonTrainList != null;


        for (JsonTrain jTrain : jsonTrainList) {
            int howLong = 66;
            LocalTime timeDeparting;


            if (towards.equals("stratford")) {
                if (!jTrain.getDestination().equals("Stratford (London) Rail Station")) {
                    continue;
                }
                howLong = Time.howLong(jTrain.getExpectedArrival());// hi to st
                timeDeparting = Time.parseTime(jTrain.getExpectedArrival());
            }
            else {
                if (jTrain.getTimeDeparting() == null) {
                    continue;
                }
                howLong = Time.howLong(jTrain.getTimeDeparting()); //st to hi
                timeDeparting = Time.parseTime(jTrain.getTimeDeparting());

            }




            Train train = new Train.Builder()
                    .withDestination(jTrain.getDestination())
                    .onPlatform(jTrain.getPlatform())
                    .addHowLong(howLong)
                    .timeDeparting(timeDeparting.toString()).build();

            trainList.add(train);
        }

        trainList.sort(new TrainComparator());
        return trainList;
    }


}
