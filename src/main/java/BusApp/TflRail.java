package BusApp;

public class TflRail {
    private final String timeArriving;
    private final String destination;
    private final String platform;
    private final String onTime;
    private boolean fast;

    public TflRail(String timeArriving, String destination, String platform, String onTime, boolean fast) {
        this.timeArriving = timeArriving;
        this.destination = destination;
        this.onTime = onTime;
        this.platform = platform;
        this.fast = fast;
    }

    public String getTimeArriving() {
        return timeArriving;
    }

    public String getDestination() {
        return destination;
    }

    public String getOnTime() {
        return onTime;
    }

    public boolean isFast() {
        return fast;
    }

    public String getPlatform() {
        return platform;
    }

    public String getInfo() {
        if (!onTime.equals("On time")) {
            if (fast) {
                return "FAST " + timeArriving + " platform " + platform + "<" + onTime + ">";
            }
            return timeArriving + " platform " + platform + "<" + onTime + ">";
        }


        if (fast) {
            return "FAST " + timeArriving + " platform " + platform;
        }
        return timeArriving + " platform " + platform;
    }
}
