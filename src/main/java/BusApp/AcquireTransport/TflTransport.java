package BusApp.AcquireTransport;

import BusApp.Transport.Bus;
import BusApp.Json.JsonParser;
import BusApp.Transport.Train;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TflTransport {

    public static ArrayList<Train> getTrains(String uri) {
        return getTrains(uri, "");
    }

    public static ArrayList<Train> getTrains(String uri, String towards) {
        String response = Client.ExecuteGetRequest(uri);
        return JsonParser.parseTrainResponse(response, towards);
    }

    public static ArrayList<Bus> getBusStop(String stopPoint) {
        return getBusStop(stopPoint, Collections.emptyList());
    }

    public static ArrayList<Bus> getBusStop(String stopPoint, List<String> remove) {
        String response = Client.ExecuteGetRequest("https://api.tfl.gov.uk/StopPoint/" + stopPoint + "/Arrivals");
        ArrayList<Bus> busList = JsonParser.parseBusResponse(response);
        busList = removeBuses(busList, remove);
        return busList;
    }

    public static ArrayList<Bus> removeBuses(ArrayList<Bus> busList, List<String> remove) {
        for (String busNumber : remove) {
            busList.removeIf(Bus -> (Bus.getName().equals(busNumber)));
        }
        return busList;
    }





}
