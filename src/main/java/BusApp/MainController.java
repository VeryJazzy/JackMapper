package BusApp;

import BusApp.Json.JsonParser;
import com.thalesgroup.rtti._2017_10_01.ldb.types.ServiceItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class MainController {

    @GetMapping("/getBusesToRomford")
    public String getBusesToRomford(Model model) {
        model.addAttribute("morayWay", getBusStop("https://api.tfl.gov.uk/StopPoint/490010016S/Arrivals"));
        model.addAttribute("belleVue", getBusStop("https://api.tfl.gov.uk/StopPoint/490003843W/Arrivals"));
        model.addAttribute("collierRow", getBusStop("https://api.tfl.gov.uk/StopPoint/490005438J/Arrivals",
                List.of("375")));
        return "index";
    }

    @GetMapping("/getBusesToHome")
    public String getBusesToHome(Model model) {
        model.addAttribute("romfordStation", getBusStop("https://api.tfl.gov.uk/StopPoint/490001243V/Arrivals",
                List.of("174", "193", "370", "496", "498")));

        model.addAttribute("westernRoad", getBusStop("https://api.tfl.gov.uk/StopPoint/490014452P/Arrivals",
                List.of("296", "498", "66", "486", "375", "365", "86")));

        model.addAttribute("romfordMarket", getBusStop("https://api.tfl.gov.uk/StopPoint/490011659H/Arrivals",
                List.of("296", "66")));

        model.addAttribute("romfordPoliceStation", getBusStop("https://api.tfl.gov.uk/StopPoint/490011660NA/Arrivals",
                List.of("498", "174")));
        return "index";
    }

    @GetMapping("/getHAITrains")
    public String getHighburyAndIslingtionTrains(Model model) {
        model.addAttribute("HAITrains", getTrains("https://api.tfl.gov.uk/StopPoint/910GSTFD/ArrivalDepartures?lineIds=london-overground"));
        return "index";
    }

    @GetMapping("/getHAItoStratfordTrains")
    public String getHighburyAndIslingtionToStratfordTrains(Model model) {
        model.addAttribute("HAItoStratfordTrains", getTrains("https://api.tfl.gov.uk/StopPoint/910GHGHI/Arrivals", "stratford"));
        return "index";
    }

    @GetMapping("/getRomfordTrains")
    public String getRomfordTrains(Model model) {
        model.addAttribute("romfordTrains", getNationalRailTrains("RMF", "London Liverpool Street"));
        return "index";
    }

    @GetMapping("/getStratfordTrains")
    public String getStratfordTrains(Model model) {
        model.addAttribute("stratfordTrains", getNationalRailTrains("SRA", "Shenfield"));
        return "index";
    }

    @GetMapping("/getLiverpoolStreetTrains")
    public String getLiverpoolStreetTrains(Model model) {
        model.addAttribute("liverpoolStreetTrains", getNationalRailTrains("LST", "Shenfield"));
        return "index";
    }

    public ArrayList<Train> getNationalRailTrains(String crs, String destination) {
        ArrayList<Train> trainList = null;
        try {
            List<ServiceItem> serviceItemList = SoapDepartureBoard.getDepartureBoards(crs);
            assert serviceItemList != null;
            trainList = ServiceItemParser.parseServiceItem(serviceItemList, destination);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trainList;
    }























    public ArrayList<Train> getTrains(String uri) {
        return getTrains(uri, "");
    }

    public ArrayList<Train> getTrains(String uri, String towards) {
        String response = Client.ExecuteGetRequest(uri);
        return JsonParser.parseJsonTrain(response, towards);
    }

    public ArrayList<Bus> getBusStop(String uri) {
        return getBusStop(uri, Collections.emptyList());
    }

    public ArrayList<Bus> getBusStop(String uri, List<String> remove) {
        String response = Client.ExecuteGetRequest(uri);
        ArrayList<Bus> busList = JsonParser.parseJsonBus(response);
        busList = removeBuses(busList, remove);
        return busList;
    }

    public ArrayList<Bus> removeBuses(ArrayList<Bus> busList, List<String> remove) {
        for (String busNumber : remove) {
            busList.removeIf(Bus -> (Bus.getName().equals(busNumber)));
        }
        return busList;
    }
}
