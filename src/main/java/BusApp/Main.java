package BusApp;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        //		SpringApplication.run(TflBusApplication.class, args);
        Main main = new Main();
        String morayWay = "https://api.tfl.gov.uk/StopPoint/490010016S/Arrivals";
        String belleVueRoad = "https://api.tfl.gov.uk/StopPoint/490003843W/Arrivals?lineId=175";

        String morayResponse = Client.ExecuteGetRequest(morayWay);
        String bvrResponse = Client.ExecuteGetRequest(belleVueRoad);

        List<JsonBus> jsonBusList = Json.parseJson(morayResponse);
        jsonBusList.addAll(Json.parseJson(bvrResponse));

        ArrayList<Bus> busList = Json.parseJsonBuses(jsonBusList);





        for (Bus bus : busList) {
            System.out.println(bus.getInfo());
        }



    }


}
