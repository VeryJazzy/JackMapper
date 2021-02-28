package BusApp;

import Utility.FileUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

    private String testJson = FileUtil.readFile("src/test/2TestBuses.txt");

    @Test
    void parseJsonReturnsRightAmountOfBuses() {
        assertEquals(Json.parseJson(testJson).size(), 2);
    }

    @Test
    void parseJsonReturnsCorrectBusesAndInfo() {
        List<Bus> busList = Json.parseJson(testJson);
        assertEquals(busList.get(0).getBusNumber(), "103");
        assertEquals(busList.get(0).getTowards(), "Romford");
        assertEquals(busList.get(0).getExpectedArrival(), "2021-02-28T11:15:04Z");
        assertEquals(busList.get(1).getBusNumber(), "499");
        assertEquals(busList.get(1).getTowards(), "Romford");
        assertEquals(busList.get(1).getExpectedArrival(), "2021-02-28T11:05:09Z");
    }

}