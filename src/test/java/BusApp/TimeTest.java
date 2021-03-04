package BusApp;

import BusApp.Json.Time;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {

    @Test
    void testParseTime() {
        String expectedArrival = "2021-02-28T11:15:54Z";
        assertEquals(Time.parseTime(expectedArrival),
                LocalTime.of(11,15,54));
    }

    @Test
    void testGetDifference() {
        LocalTime now = LocalTime.of(11, 2,30);
        LocalTime expected = LocalTime.of(11,14,50);
        assertEquals(Time.getDifference(expected, now), 12);
    }

    @Test
    void testGetDifferenceAgain() {
        LocalTime now = LocalTime.of(10, 50,30);
        LocalTime expected = LocalTime.of(11,10,50);
        assertEquals(Time.getDifference(expected,now),20);
    }




}