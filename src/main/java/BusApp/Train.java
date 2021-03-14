package BusApp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Train {

    public static class Builder {

        private String destination;
        private String platform;
        private List<Integer> howLongs = new ArrayList<>();
        private String timeDeparting;
        private String onTime = "On time";
        private boolean fast;

        public Builder withDestination(String destination) {
            this.destination = destinationReNamer(destination);
            return this;
        }

        private String destinationReNamer(String destination) {
            switch (destination) {
                case "Richmond (London) Rail Station":
                    return "Richmond";
                case "Willesden Junction Rail Station":
                    return "Willesden";
                case "Stratford (London) Rail Station":
                    return "Stratford";
                case "Clapham Junction Rail Station":
                    return "Clapham Junction";
            }
            return "Unknown Destination";
        }

        public Builder onPlatform(String platform) {
            this.platform = platform.equals("Platform Unknown") ? "Platform NA" : "Platform " + platform;
            return this;
        }

        public Builder timeDeparting(String timeDeparting) {
            this.timeDeparting = timeDeparting;
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

        public Train build() {
            Train train = new Train();
            train.destination = this.destination;
            train.platform = this.platform;
            train.howLongs = this.howLongs;
            train.timeArriving = this.timeDeparting;
            train.onTime = this.onTime;
            train.fast = this.fast;
            return train;
        }
    }

    private String destination;
    private String platform;
    private List<Integer> howLongs = new ArrayList<>();
    private String timeArriving;
    private String onTime;
    private boolean fast;

    private Train() {
    }

    public String getDestination() {
        return destination;
    }

    public void addTime(int time) {
        howLongs.add(time - 1);
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
        if (!(o instanceof Train)) {
            return false;
        }
        Train t = (Train) o;
        if (this.destination.equals(t.destination)) {
            return true;
        }
        return false;
    }

    public String getInfo() {
        return timeArriving + " ------ "+ platform;
    }

    public String getRailInfo() {
        StringBuilder builder = new StringBuilder(timeArriving);
        builder.append(fast ? " -FAST- " : " ------ ");
        builder.append(platform);
        builder.append(!onTime.equals("On time") ? " <Delayed: " + onTime + ">" : "");

        return builder.toString();
    }


}
