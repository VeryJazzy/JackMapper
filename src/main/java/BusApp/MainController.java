package BusApp;

import BusApp.Json.Json;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class MainController {

    @GetMapping("/getBuses")
    public String getBuses(Model model) {

        ArrayList<Bus> morayWayList = getBuses("https://api.tfl.gov.uk/StopPoint/490010016S/Arrivals");
        model.addAttribute("morayWay",morayWayList);
        ArrayList<Bus> belleVueList = getBuses("https://api.tfl.gov.uk/StopPoint/490003843W/Arrivals");
        model.addAttribute("belleVue",belleVueList);
        ArrayList<Bus> collierRowList = getBuses("https://api.tfl.gov.uk/StopPoint/490005438J/Arrivals");
        model.addAttribute("collierRow",collierRowList);

        ArrayList<Bus> romfordStationList = getBuses("https://api.tfl.gov.uk/StopPoint/490001243V/Arrivals");
        model.addAttribute("romfordStation",romfordStationList);
        ArrayList<Bus> westernRoadList = getBuses("https://api.tfl.gov.uk/StopPoint/490014452P/Arrivals");
        model.addAttribute("westernRoad",westernRoadList);
        ArrayList<Bus> romfordMarketList = getBuses("https://api.tfl.gov.uk/StopPoint/490011659H/Arrivals");
        model.addAttribute("romfordMarket",romfordMarketList);
        ArrayList<Bus> romfordPoliceStationList = getBuses("https://api.tfl.gov.uk/StopPoint/490011660NA/Arrivals");
        model.addAttribute("romfordPoliceStation",romfordPoliceStationList);

        return "index";
    }


    public ArrayList<Bus> getBuses(String uri) {
        String response = Client.ExecuteGetRequest(uri);
        return Json.parseJson(response);
    }
}
