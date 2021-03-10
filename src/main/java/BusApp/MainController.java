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
        model.addAttribute("t1", "Moray Way");
        model.addAttribute("morayWay", getBusStop("490010016S"));
        model.addAttribute("t2", "Belle Vue Road");
        model.addAttribute("belleVue", getBusStop("490003843W"));
        model.addAttribute("t3", "Collier Row Roundabout");
        model.addAttribute("collierRow", getBusStop("490005438J",
                List.of("375")));
        return "index";
    }

    @GetMapping("/getBusesToHome")
    public String getBusesToHome(Model model) {
        model.addAttribute("t4", "Romford Station");
        model.addAttribute("romfordStation", getBusStop("490001243V",
                List.of("174", "193", "370", "496", "498")));

        model.addAttribute("t5", "Western Road");
        model.addAttribute("westernRoad", getBusStop("490014452P",
                List.of("296", "498", "66", "486", "375", "365", "86")));

        model.addAttribute("t6", "Romford Market");
        model.addAttribute("romfordMarket", getBusStop("490011659H",
                List.of("296", "66", "375")));

        model.addAttribute("t7", "Romford Police Station");
        model.addAttribute("romfordPoliceStation", getBusStop("490011660NA",
                List.of("498", "174")));
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

    @GetMapping("/getStratfordToHAITrains")
    public String getStratfordToHAITrains(Model model) {
        model.addAttribute("StratfordToHAITrains", getTrains("https://api.tfl.gov.uk/StopPoint/910GSTFD/ArrivalDepartures?lineIds=london-overground",
                "Highbury & Islington Rail Station"));
        return "index";
    }

    @GetMapping("/getHAItoStratfordTrains")
    public String getHighburyAndIslingtionToStratfordTrains(Model model) {
        model.addAttribute("HAItoStratfordTrains", getTrains("https://api.tfl.gov.uk/StopPoint/910GHGHI/Arrivals",
                "Stratford (London) Rail Station"));
        return "index";
    }



}
