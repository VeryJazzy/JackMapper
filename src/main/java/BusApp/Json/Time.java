package BusApp.Json;

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

    public static Long getDifference(LocalTime expected, LocalTime current) {
        return Math.abs(Duration.between(expected, current).toMinutes());
    }

    public static int howLong(String expectedArrival) {
        LocalTime expected = parseTime(expectedArrival);

        int difference = Math.toIntExact(getDifference(expected, LocalTime.now()));
        return difference;
    }



}
