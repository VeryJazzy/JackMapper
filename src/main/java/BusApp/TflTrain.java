package BusApp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TflTrain {

    private String destination;
    private String platform;
    private List<Integer> howLongs = new ArrayList<>();
    private String timeArriving;

    public TflTrain(String destination, String platform) {
        this.destination = destination;
        this.platform = platform == "Platform Unknown" ? "Platform NA" : platform;
    }
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setTimeArriving(String timeArriving) {
        this.timeArriving = timeArriving;
    }

    public void addTime(int time) {
        howLongs.add(time);
        Collections.sort(howLongs);
    }

    @Override
    public String toString() {
        return destination;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TflTrain)) {
            return false;
        }
        TflTrain t = (TflTrain) o;
        if (this.destination.equals(t.destination)) {
            return true;
        }
        return false;
    }

    public String getInfo() {

        return destination + " " + timeArriving + " " + platform;
    }















}
