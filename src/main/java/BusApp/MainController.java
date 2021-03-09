package BusApp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static BusApp.Client.getBusStop;
import static BusApp.Client.getTrains;
import static BusApp.SoapClient.getNationalRailTrains;


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
                List.of("296", "66", "375")));

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





}
