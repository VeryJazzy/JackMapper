package BusApp.AcquireTransport;

import BusApp.Transport.ParseServiceItem;
import BusApp.Transport.Train;
import com.thalesgroup.rtti._2017_10_01.ldb.types.ServiceItem;

import java.util.ArrayList;
import java.util.List;

public class NationalRailTransport {

    public static List<Train> getNationalRailTrains(String crs, String direction) {
        List<Train> trainList = null;
        try {
            List<ServiceItem> fullServiceItemList = SoapClient.ExecuteRequest(crs);
            assert fullServiceItemList != null;
            trainList = filterTrains(fullServiceItemList, direction);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return trainList;
    }

    public static List<Train> filterTrains(List<ServiceItem> serviceItemList, String direction) {
        List<Train> trainList;

        if (direction.equals("Shenfield")) { //homebound
            ArrayList<String> fastServiceItemIDs = getFastServiceItems("RMF", List.of("Southend Victoria", "Colchester Town"));
            trainList = populateTrainList(serviceItemList, direction, true);
        } else {
            trainList = populateTrainList(serviceItemList, direction, false);
        }
        return trainList;
    }

    public static ArrayList<String> getFastServiceItems(String crs, List<String> destination) {
        ArrayList<String> fastIDs = new ArrayList<>();
        try {
            List<ServiceItem> serviceItemList = SoapClient.ExecuteRequest(crs);
            assert serviceItemList != null;

            for (ServiceItem si : serviceItemList) {
                for (String des : destination) {
                    if (si.getDestination().getLocation().get(0).getLocationName().equals(des)) {
                        fastIDs.add(si.getRsid());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fastIDs;
    }

    public static List<Train> populateTrainList(List<ServiceItem> serviceItemList, String destination, boolean homeBound) {

        //refactor needed

        List<Train> trainList = new ArrayList<>();
        List<String> fastServiceItemIDs = homeBound ?
                getFastServiceItems("RMF", List.of("Southend Victoria", "Colchester Town")) :
                new ArrayList<>();

        for (ServiceItem si : serviceItemList) {
            if (destination.equals("London Liverpool Street")) {
                if (fastServiceItemIDs.contains(si.getRsid()) || si.getDestination().getLocation().get(0).getLocationName().equals(destination)
                        || si.getDestination().getLocation().get(0).getLocationName().equals("Stratford (London)")) {
                    Train train = ParseServiceItem.toTrain(si);
                    trainList.add(train);
                }
            } else if (fastServiceItemIDs.contains(si.getRsid()) || si.getDestination().getLocation().get(0).getLocationName().equals(destination)) {
                Train train = ParseServiceItem.toTrain(si);
                trainList.add(train);
            }
            if (trainList.size() == 10) {
                return trainList;
            }
        }

        //refactor needed

        return trainList;
    }

}
