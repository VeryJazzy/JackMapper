package BusApp;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalTime;
import java.util.List;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        //		SpringApplication.run(TflBusApplication.class, args);
//        Main main = new Main();
//        String morayWay = "https://api.tfl.gov.uk/StopPoint/490010016S/Arrivals";
//        String belleVueRoad = "https://api.tfl.gov.uk/StopPoint/490003843W/Arrivals?lineId=175";
//
//        String jsonResponse = Client.ExecuteGetRequest(morayWay);
//        List<Bus> busList = Json.parseJson(jsonResponse);
//
//        for (Bus b : busList) {
//            System.out.println(b);
//        }
        System.out.println(LocalTime.now());

    }







}
