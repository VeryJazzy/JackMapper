package BusApp;

public class TflRailTrain {

    private final String destination;
    private String platform;
    private final String timeArriving;
    private final String onTime;
    private boolean fast;

    public TflRailTrain(String timeArriving, String destination, String platform, String onTime, boolean fast) {
        this.timeArriving = timeArriving;
        this.destination = destination;
        this.onTime = onTime;
        this.platform = platform == null ? "NA" : platform;
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
        StringBuilder builder = new StringBuilder();
        builder.append(fast ? "FAST " : "");

        builder.append(timeArriving).append(" platform ").append(platform);

        builder.append(!onTime.equals("On time") ? "<" + onTime + ">" : "");

        return builder.toString();
    }
}
