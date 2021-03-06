package BusApp;

import com.thalesgroup.rtti._2017_10_01.ldb.types.ServiceItem;

import java.util.ArrayList;
import java.util.List;

public class ServiceItemParser {

    public static ArrayList<TflRailTrain> parseServiceItemForRomford(List<ServiceItem> serviceItemList) {
        ArrayList<TflRailTrain> tflRailTrainList = new ArrayList<>();

        for (ServiceItem si : serviceItemList) {
            if (si.getDestination().getLocation().get(0).getLocationName().equals("London Liverpool Street")) {
                boolean fast;
                if (si.getOrigin().getLocation().get(0).getCrs().equals("CET") || si.getOrigin().getLocation().get(0).getCrs().equals("SOV")) {
                    fast = true;
                } else {
                    fast = false;
                }
                TflRailTrain tflRailTrain = new TflRailTrain(si.getStd(), si.getDestination().getLocation().get(0).getLocationName(), si.getPlatform(), si.getEtd(), fast);
                tflRailTrainList.add(tflRailTrain);
            }
        }

        return tflRailTrainList;
    }

    public static ArrayList<TflRailTrain> parseServiceItemForStratford(List<ServiceItem> serviceItemList) {
        ArrayList<TflRailTrain> tflRailTrainList = new ArrayList<>();
        for (ServiceItem si : serviceItemList) {
            if (si.getDestination().getLocation().get(0).getLocationName().equals("Shenfield")) {
                TflRailTrain tflRailTrain = new TflRailTrain(si.getStd(), si.getDestination().getLocation().get(0).getLocationName(), si.getPlatform(), si.getEtd(), false);
                tflRailTrainList.add(tflRailTrain);
            }
        }
        return tflRailTrainList;
    }

    public static ArrayList<TflRailTrain> parseServiceItemForLiverpoolStreet(List<ServiceItem> serviceItemList) {
        ArrayList<TflRailTrain> tflRailTrainList = new ArrayList<>();
        for (ServiceItem si : serviceItemList) {
            if (si.getDestination().getLocation().get(0).getLocationName().equals("Shenfield")) {
                TflRailTrain tflRailTrain = new TflRailTrain(si.getStd(), si.getDestination().getLocation().get(0).getLocationName(), si.getPlatform(), si.getEtd(), false);
                tflRailTrainList.add(tflRailTrain);
            }
        }
        return tflRailTrainList;
    }
}
