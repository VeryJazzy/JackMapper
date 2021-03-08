package BusApp.Json;

import BusApp.Bus;
import BusApp.Train;
import BusApp.Time;
import BusApp.TrainComparator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalTime;
import java.util.*;

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

    public static ArrayList<Train> parseJsonTrain(String json, String towards) {
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
            //checks its outbound
            //check its stratford


            int time = 66;
            LocalTime expectedArrival;
            if (towards.equals("stratford")) {
                if (!jTrain.getDestination().equals("Stratford (London) Rail Station")) {
                    continue;
                }
                time = Time.howLong(jTrain.getExpectedArrival());// hi to st
                expectedArrival = Time.parseTime(jTrain.getExpectedArrival());



            } else {
                if (jTrain.getTimeDeparting() == null) {
                    continue;
                }
                time = Time.howLong(jTrain.getTimeDeparting()); //st to hi
                expectedArrival = Time.parseTime(jTrain.getTimeDeparting());

            }




            Train train = new Train.Builder()
                    .withDestination(jTrain.getDestination())
                    .onPlatform(jTrain.getPlatform())
                    .addHowLong(time)
                    .timeArriving(expectedArrival.toString()).build();

            trainList.add(train);
        }

        trainList.sort(new TrainComparator());
        return trainList;
    }




}
