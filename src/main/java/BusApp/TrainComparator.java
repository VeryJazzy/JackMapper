package BusApp;

import java.util.Comparator;

public class TrainComparator implements Comparator<TflTrain> {

    @Override
    public int compare(TflTrain train1, TflTrain train2) {
        if (train1.getHowLongs().get(0) > train2.getHowLongs().get(0)) {
            return 1;
        } else return -1;

    }
}
