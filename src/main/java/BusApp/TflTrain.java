package BusApp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TflTrain {

    public static class Builder {

        private String destination;
        private String platform;
        private List<Integer> howLongs = new ArrayList<>();
        private String timeArriving;
        private String onTime;
        private boolean fast;

        public Builder withDestination(String destination) {
            this.destination = destinationReNamer(destination);
            return this;
        }

        public Builder onPlatform(String platform) {
            this.platform = platform.equals("Platform Unknown") ? "Platform NA" : platform;
            return this;
        }

        public Builder timeArriving(String timeArriving) {
            this.timeArriving = timeArriving;
            return this;
        }

        public Builder addHowLong(int time) {
            howLongs.add(time);
            return this;
        }

        public Builder isOnTime(String onTime) {
            this.onTime = onTime;
            return this;
        }

        public Builder isFast(boolean fast) {
            this.fast = fast;
            return this;
        }

        public TflTrain build() {
            TflTrain train = new TflTrain();
            train.destination = this.destination;
            train.platform = this.platform;
            train.howLongs = this.howLongs;
            train.timeArriving = this.timeArriving;
            return train;
        }



        private String destinationReNamer(String destination) {
            switch (destination) {
                case "Richmond (London) Rail Station":
                    return "Richmond";
                case "Willesden Junction Rail Station":
                    return "Willesden";
                case "Stratford (London) Rail Station":
                    return "Stratford";
            }
            return null;
        }
    }


    private String destination;
    private String platform;
    private List<Integer> howLongs = new ArrayList<>();
    private String timeArriving;
    private String onTime;
    private boolean fast;


    private TflTrain() {}

    public String getDestination() {
        return destination;
    }

    public void addTime(int time) {
        howLongs.add(time);
        Collections.sort(howLongs);
    }

    public List<Integer> getHowLongs() {
        return howLongs;
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
        return howLongs.get(0) + ":   " + timeArriving + " " + destination + ", " + platform;
    }

    public String getRailInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append(fast ? "FAST " : "");
        builder.append(timeArriving).append(" platform ").append(platform);
        builder.append(!onTime.equals("On time") ? "<" + onTime + ">" : "");
        return builder.toString();
    }
















}
