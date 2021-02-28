package BusApp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {

    @Test
    void TestCorrectEta() {
        String timeNow = "11:01:50";
        assertEquals(Time.Eta("2021-02-28T11:15:04Z", timeNow), "14 Minutes");

    }
}