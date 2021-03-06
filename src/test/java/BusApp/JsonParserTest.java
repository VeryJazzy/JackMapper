package BusApp;

import BusApp.Json.JsonParser;
import Utility.FileUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {

    private String testJson = FileUtil.readFile("src/test/2TestBuses.txt");

    @Test
    void parseJsonReturnsRightAmountOfBuses() {
        assertEquals(JsonParser.parseJsonBus(testJson).size(), 2);
    }

    @Test
    void parseJsonReturnsCorrectBuses() {
        List<Bus> busList = JsonParser.parseJsonBus(testJson);
        assertEquals(busList.get(0).getName(), "103");
        assertEquals(busList.get(1).getName(), "499");
    }

}