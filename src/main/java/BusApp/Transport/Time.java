package BusApp.Transport;

import java.time.Duration;
import java.time.LocalTime;

public class Time {

    static LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public static LocalTime parseTime(String expectedArrival) {
        int hour = Integer.parseInt(expectedArrival.substring(11, 13));
        int minute = Integer.parseInt(expectedArrival.substring(14, 16));
        int seconds = Integer.parseInt(expectedArrival.substring(17, 19));
        return LocalTime.of(hour, minute, seconds);
    }

    public static double getDifference(LocalTime expected, LocalTime current) {
        double seconds = Duration.between(expected, current).toSeconds();
        return Math.abs(seconds / 60);
    }

    public static int howLong(String expectedArrival) {
        LocalTime expected = parseTime(expectedArrival);
        double difference = getDifference(expected, LocalTime.now());
        return (int) Math.round(difference);
    }

    public static int howLongForSiStd(String expectedArrival) {
        LocalTime conv = LocalTime.of(Integer.parseInt(expectedArrival.substring(0, 2)), Integer.parseInt(expectedArrival.substring(3, 5)));
        return (int) Math.round(getDifference(conv, LocalTime.now()));

    }


}
