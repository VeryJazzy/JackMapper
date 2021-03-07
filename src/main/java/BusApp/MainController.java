package BusApp;

import BusApp.Json.JsonParser;
import com.thalesgroup.rtti._2017_10_01.ldb.types.ServiceItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @GetMapping("/getBuses")
    public String getBuses(Model model) {
        ArrayList<Bus> morayWayList = getBuses("https://api.tfl.gov.uk/StopPoint/490010016S/Arrivals");
        model.addAttribute("morayWay", morayWayList);

        ArrayList<Bus> belleVueList = getBuses("https://api.tfl.gov.uk/StopPoint/490003843W/Arrivals");
        model.addAttribute("belleVue", belleVueList);

        ArrayList<Bus> collierRowList = getBuses("https://api.tfl.gov.uk/StopPoint/490005438J/Arrivals");
        collierRowList.removeIf(Bus -> (Bus.getName().equals("375")));
        model.addAttribute("collierRow", collierRowList);

        ArrayList<Bus> romfordStationList = getBuses("https://api.tfl.gov.uk/StopPoint/490001243V/Arrivals");
        romfordStationList.removeIf(Bus ->
                (Bus.getName().equals("174")) ||
                        (Bus.getName().equals("193")) ||
                        (Bus.getName().equals("370")) ||
                        (Bus.getName().equals("496")) ||
                        (Bus.getName().equals("498")));
        model.addAttribute("romfordStation", romfordStationList);


        ArrayList<Bus> westernRoadList = getBuses("https://api.tfl.gov.uk/StopPoint/490014452P/Arrivals");
        westernRoadList.removeIf(Bus ->
                (Bus.getName().equals("252")) ||
                        (Bus.getName().equals("296")) ||
                        (Bus.getName().equals("498")) ||
                        (Bus.getName().equals("66")) ||
                        (Bus.getName().equals("486")));
        model.addAttribute("westernRoad", westernRoadList);

        ArrayList<Bus> romfordMarketList = getBuses("https://api.tfl.gov.uk/StopPoint/490011659H/Arrivals");
        romfordMarketList.removeIf(Bus ->
                (Bus.getName().equals("252")) ||
                        (Bus.getName().equals("296")) ||
                        (Bus.getName().equals("66")));
        model.addAttribute("romfordMarket", romfordMarketList);
        ArrayList<Bus> romfordPoliceStationList = getBuses("https://api.tfl.gov.uk/StopPoint/490011660NA/Arrivals");
        romfordPoliceStationList.removeIf(Bus ->
                (Bus.getName().equals("498")) ||
                        (Bus.getName().equals("174")));
        model.addAttribute("romfordPoliceStation", romfordPoliceStationList);
        return "index";
    }

    @GetMapping("/getHAITrains")
    public String getHighburyAndIslingtionTrains(Model model) {
        ArrayList<TflTrain> highburyAndIslingtonList = getTrains("https://api.tfl.gov.uk/StopPoint/910GSTFD/ArrivalDepartures?lineIds=london-overground", "");
        model.addAttribute("HAITrains", highburyAndIslingtonList);
        return "index";
    }

    @GetMapping("/getHAItoStratfordTrains")
    public String getHighburyAndIslingtionToStratfordTrains(Model model) {
        ArrayList<TflTrain> HAItoStratford = getTrains("https://api.tfl.gov.uk/StopPoint/910GHGHI/Arrivals", "stratford");
        model.addAttribute("HAItoStratfordTrains", HAItoStratford);
        return "index";
    }


    @GetMapping("/getRomfordTrains")
    public String getRomfordTrains(Model model) {
        try {
            List<ServiceItem> serviceItemList = GetDepartureBoardExample.getDepartureBoards("RMF");
            ArrayList<TflRailTrain> tflRailTrainList = ServiceItemParser.parseServiceItemForRomford(serviceItemList);
            model.addAttribute("romfordTrains", tflRailTrainList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    @GetMapping("/getStratfordTrains")
    public String getStratfordTrains(Model model) {
        try {
            List<ServiceItem> serviceItemList = GetDepartureBoardExample.getDepartureBoards("SRA");
            ArrayList<TflRailTrain> tflRailTrainList = ServiceItemParser.parseServiceItemForStratford(serviceItemList);
            model.addAttribute("stratfordTrains", tflRailTrainList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    @GetMapping("/getLiverpoolStreetTrains")
    public String getLiverpoolStreetTrains(Model model) {
        try {
            List<ServiceItem> serviceItemList = GetDepartureBoardExample.getDepartureBoards("LST");
            ArrayList<TflRailTrain> tflRailTrainList = ServiceItemParser.parseServiceItemForLiverpoolStreet(serviceItemList);
            model.addAttribute("liverpoolStreetTrains", tflRailTrainList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    public ArrayList<TflTrain> getTrains(String uri, String towards) {
        String response = Client.ExecuteGetRequest(uri);
        return JsonParser.parseJsonTrain(response, towards);
    }


    public ArrayList<Bus> getBuses(String uri) {
        String response = Client.ExecuteGetRequest(uri);
        return JsonParser.parseJsonBus(response);
    }
}
