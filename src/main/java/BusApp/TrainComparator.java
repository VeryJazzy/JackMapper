package BusApp;

import java.util.Comparator;

public class TrainComparator implements Comparator<Train> {

    @Override
    public int compare(Train train1, Train train2) {
        if (train1.getHowLongs().get(0) > train2.getHowLongs().get(0)) {
            return 1;
        } else return -1;

    }
}
