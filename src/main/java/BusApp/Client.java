package BusApp;

import BusApp.Json.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Client {

    private static HttpClient client = HttpClients.createDefault();

    static String ExecuteGetRequest(String uri) {
        try {
            HttpResponse response = client.execute(new HttpGet(uri));
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            System.out.println("Bad request : " + e.getMessage());
            return null;
        }
    }



    public static ArrayList<Train> getTrains(String uri) {
        return getTrains(uri, "");
    }

    public static ArrayList<Train> getTrains(String uri, String towards) {
        String response = ExecuteGetRequest(uri);
        return JsonParser.parseTrainResponse(response, towards);
    }

    public static ArrayList<Bus> getBusStop(String stopPoint) {

        return getBusStop(stopPoint, Collections.emptyList());
    }

    public static ArrayList<Bus> getBusStop(String stopPoint, List<String> remove) {
        String response = ExecuteGetRequest("https://api.tfl.gov.uk/StopPoint/" + stopPoint + "/Arrivals");
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
