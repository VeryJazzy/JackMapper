package BusApp;

import java.util.Comparator;

public class BusComparator implements Comparator<Bus> {

    @Override
    public int compare(Bus b1, Bus b2) {
        if (b1.getHowLongs().get(0) > b2.getHowLongs().get(0)) {
            return 1;
        }
        return -1;
    }
}
