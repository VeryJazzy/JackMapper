package BusApp;

import com.thalesgroup.rtti._2017_10_01.ldb.types.ServiceItem;

import java.util.ArrayList;
import java.util.List;

public class ServiceItemParser {

    public static ArrayList<TflRail> parseServiceItem(List<ServiceItem> serviceItemList) {
        ArrayList<TflRail> tflRailList = new ArrayList<>();

        for (ServiceItem si : serviceItemList) {
            if (si.getDestination().getLocation().get(0).getLocationName().equals("London Liverpool Street")) {
                boolean fast;
                if (si.getOrigin().getLocation().get(0).getCrs().equals("CET") || si.getOrigin().getLocation().get(0).getCrs().equals("SOV")) {
                    fast = true;
                } else {
                    fast = false;
                }
                TflRail tflRail = new TflRail(si.getStd(), si.getDestination().getLocation().get(0).getLocationName(), si.getPlatform(), si.getEtd(), fast);
                tflRailList.add(tflRail);
            }
        }

        return tflRailList;
    }
}
