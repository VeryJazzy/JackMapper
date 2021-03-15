package BusApp.Transport;

import com.thalesgroup.rtti._2017_10_01.ldb.types.ServiceItem;

public class ParseServiceItem {

    public static Train toTrain(ServiceItem si) {
        boolean fast = checkIfFast(si);
        String platform = si.getPlatform() == null ? "Platform Unknown" : si.getPlatform();

        return new Train.Builder()
                .withDestination(si.getDestination().getLocation().get(0).getLocationName())
                .onPlatform(platform)
                .isOnTime(si.getEtd())
                .timeDeparting(si.getStd())
                .addHowLong(Time.howLongForSiStd(si.getStd()))
                .isFast(fast)
                .build();
    }

    public static boolean checkIfFast(ServiceItem si) {
        return si.getDestination().getLocation().get(0).getCrs().equals("CET") ||
                si.getDestination().getLocation().get(0).getCrs().equals("SOV") ||
                si.getOrigin().getLocation().get(0).getCrs().equals("CET") ||
                si.getOrigin().getLocation().get(0).getCrs().equals("SOV");
    }

}
