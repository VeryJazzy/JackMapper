package BusApp.Json;

import BusApp.Transport.*;

import java.time.LocalTime;
import java.util.*;

public class JsonParser {

    public static ArrayList<Bus> parseBusResponse(String json) {
        List<Bus> individualBuses = JacksonMapper.extractJsonBuses(json);
        HashMap<String, Bus> busesWeWant = new HashMap<>();

        for (Bus bus : individualBuses) {
            int howLong = Time.howLong(bus.getExpectedArrival());

            if (busesWeWant.containsValue(bus)) {
                busesWeWant.get(bus.getName()).addTime(howLong);
            } else {
                bus.addTime(howLong);
                busesWeWant.put(bus.getName(), bus);
            }
        }

        ArrayList<Bus> busList = new ArrayList<>(busesWeWant.values());
        busList.sort(new BusComparator());
        return busList;
    }


    public static ArrayList<Train> parseTrainResponse(String json, String destination) {
        List<JsonTrain> jsonTrainList = JacksonMapper.extractJsonTrains(json);
        ArrayList<Train> trainList = new ArrayList<>();

        for (JsonTrain jTrain : jsonTrainList) {
            int howLong = -1;
            LocalTime timeDeparting = null;

            switch (destination) {
                case "Stratford (London) Rail Station":
                    if (!jTrain.getDestination().equals(destination)) {
                        continue;
                    }
                    howLong = Time.howLong(jTrain.getExpectedArrival());// hi to st
                    timeDeparting = Time.parseTime(jTrain.getExpectedArrival());
                    break;

                case "Highbury & Islington Rail Station":
                    if (jTrain.getTimeDeparting() == null) { // if its terminated/inbound
                        continue;
                    }
                    howLong = Time.howLong(jTrain.getTimeDeparting()); //st to hi
                    timeDeparting = Time.parseTime(jTrain.getTimeDeparting());

                    break;
            }

            assert timeDeparting != null;
            Train train = new Train.Builder()
                    .withDestination(jTrain.getDestination())
                    .onPlatform(formatPlatform(jTrain.getPlatform()))
                    .addHowLong(howLong)
                    .timeDeparting(timeDeparting.toString()).build();

            if (HasTrainProbablyAlreadyLeft(train)) {
                continue;
            }

            trainList.add(train);
        }
        trainList.sort(new TrainComparator());
        return trainList;
    }

    private static boolean HasTrainProbablyAlreadyLeft(Train train) {
        LocalTime eta = LocalTime.of(
                Integer.parseInt(train.getTimeArriving().substring(0, 2)),
                Integer.parseInt(train.getTimeArriving().substring(3))).plusMinutes(1); // pushed up 1 min

        if (LocalTime.now().isAfter(eta)) {
            return true;
        }
        return false;
    }

    private static String formatPlatform(String platform) {
        if (!platform.equals("Platform Unknown")) {
            return platform.substring(8);
        }
        return platform;
    }


}
