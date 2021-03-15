package BusApp;

import BusApp.AcquireTransport.NationalRailTransport;
import BusApp.AcquireTransport.SoapClient;
import BusApp.AcquireTransport.TflTransport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;



@Controller
public class MainController {

    @GetMapping("/getBusesToRomford")
    public String getBusesToRomford(Model model) {
        model.addAttribute("t1", "Moray Way");
        model.addAttribute("morayWay", TflTransport.getBusStop("490010016S"));
        model.addAttribute("t2", "Belle Vue Road");
        model.addAttribute("belleVue", TflTransport.getBusStop("490003843W"));
        model.addAttribute("t3", "Collier Row Roundabout");
        model.addAttribute("collierRow", TflTransport.getBusStop("490005438J",
                List.of("375")));
        return "results";
    }

    @GetMapping("/getBusesHome")
    public String getBusesToHome(Model model) {
        model.addAttribute("t4", "Romford Station");
        model.addAttribute("romfordStation", TflTransport.getBusStop("490001243V",
                List.of("174", "193", "370", "496", "498")));

        model.addAttribute("t5", "Western Road");
        model.addAttribute("westernRoad", TflTransport.getBusStop("490014452P",
                List.of("296", "498", "66", "486", "375", "365", "86")));

        model.addAttribute("t6", "Romford Market");
        model.addAttribute("romfordMarket", TflTransport.getBusStop("490011659H",
                List.of("296", "66", "375")));

        model.addAttribute("t7", "Romford Police Station");
        model.addAttribute("romfordPoliceStation", TflTransport.getBusStop("490011660NA",
                List.of("498", "174")));
        return "results";
    }

    @GetMapping("/getTrainsToLondon")
    public String getTrainsToLondon(Model model) {
        model.addAttribute("t8", "Romford --> Stratford");
        model.addAttribute("romfordTrains", NationalRailTransport.getNationalRailTrains("RMF", "London Liverpool Street"));
        model.addAttribute("t9", "Stratford --> Highbury & Islington");
        model.addAttribute("StratfordToHAITrains", TflTransport.getTrains("https://api.tfl.gov.uk/StopPoint/910GSTFD/ArrivalDepartures?lineIds=london-overground",
                "Highbury & Islington Rail Station"));
        return "results";
    }

    @GetMapping("/getTrainsHome")
    public String getTrainsHome(Model model) {
        model.addAttribute("t10", "Stratford --> Romford");
        model.addAttribute("stratfordTrains", NationalRailTransport.getNationalRailTrains("SRA", "Shenfield"));
        model.addAttribute("t11", "Liverpool Street --> Romford");
        model.addAttribute("liverpoolStreetTrains", NationalRailTransport.getNationalRailTrains("LST", "Shenfield"));
        model.addAttribute("t12", "Highbury & Islington --> Stratford");
        model.addAttribute("HAItoStratfordTrains", TflTransport.getTrains("https://api.tfl.gov.uk/StopPoint/910GHGHI/Arrivals",
                "Stratford (London) Rail Station"));
        return "results";
    }









//    @GetMapping("/getRomfordTrains")
//    public String getRomfordTrains(Model model) {
//        model.addAttribute("romfordTrains", SoapClient.getNationalRailTrains("RMF", "London Liverpool Street"));
//        return "trainsToLondon";
//    }
//
//    @GetMapping("/getStratfordToHAITrains")
//    public String getStratfordToHAITrains(Model model) {
//        model.addAttribute("StratfordToHAITrains", Client.getTrains("https://api.tfl.gov.uk/StopPoint/910GSTFD/ArrivalDepartures?lineIds=london-overground",
//                "Highbury & Islington Rail Station"));
//        return "trainsToLondon";
//    }




//    @GetMapping("/getStratfordTrains")
//    public String getStratfordTrains(Model model) {
//        model.addAttribute("stratfordTrains", SoapClient.getNationalRailTrains("SRA", "Shenfield"));
//        return "trainsHome";
//    }
//
//    @GetMapping("/getLiverpoolStreetTrains")
//    public String getLiverpoolStreetTrains(Model model) {
//        model.addAttribute("liverpoolStreetTrains", SoapClient.getNationalRailTrains("LST", "Shenfield"));
//        return "trainsHome";
//    }
//
//    @GetMapping("/getHAItoStratfordTrains")
//    public String getHighburyAndIslingtionToStratfordTrains(Model model) {
//        model.addAttribute("HAItoStratfordTrains", Client.getTrains("https://api.tfl.gov.uk/StopPoint/910GHGHI/Arrivals",
//                "Stratford (London) Rail Station"));
//        return "trainsHome";
//    }

}
