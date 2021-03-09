package BusApp;

import com.thalesgroup.rtti._2017_10_01.ldb.types.ServiceItem;

import java.util.ArrayList;
import java.util.List;

public class ServiceItemParser {

    public static ArrayList<Train> parseServiceItem(List<ServiceItem> serviceItemList, String destination) {
        ArrayList<Train> trainList = new ArrayList<>();

        for (ServiceItem si : serviceItemList) {
            if (si.getDestination().getLocation().get(0).getLocationName().equals(destination)) {

                boolean fast = checkIfFast(si); // check if: southend / colchester -> liverpoolStreet
                String platform = si.getPlatform() == null ? "Platform Unknown" : si.getPlatform();

                Train train = new Train.Builder()
                        .withDestination(si.getDestination().getLocation().get(0).getLocationName())
                        .onPlatform(platform)
                        .isOnTime(si.getEtd())
                        .timeDeparting(si.getStd())
                        .isFast(fast)
                        .build();
                trainList.add(train);
            }
        }
        return trainList;
    }

    public static boolean checkIfFast(ServiceItem si) {
        if (si.getOrigin().getLocation().get(0).getCrs().equals("CET") || si.getOrigin().getLocation().get(0).getCrs().equals("SOV")) {
            return true;
        } else {
            return false;
        }
    }
}
