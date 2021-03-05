package BusApp;

import java.time.Duration;
import java.time.LocalTime;

public class Time {

    static LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public static LocalTime parseTime(String expectedArrival) {
        int hour = Integer.parseInt(expectedArrival.substring(11,13));
        int minute = Integer.parseInt(expectedArrival.substring(14,16));
        int seconds = Integer.parseInt(expectedArrival.substring(17,19));
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



}
