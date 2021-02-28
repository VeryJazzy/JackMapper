package Utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilTest {

    @Test
    void readFileTest() {
        String txt = FileUtil.readFile("src/test/readFileTestTxt");
        assertEquals("hi my name is jack" + System.lineSeparator() +
                "castle", txt);

    }

}