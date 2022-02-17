package pl.mateusz.rodak.zakladybukm.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    @Test
    void testIsNotNumeric() {
        assertTrue(Utility.isNotNumeric("str"));
        assertFalse(Utility.isNotNumeric("123"));
    }

    @Test
    void testPinOrNameIsWrong() {
        assertFalse(Utility.pinOrNameIsWrong("name", "1111"));
        assertTrue(Utility.pinOrNameIsWrong("name", "pin"));
        assertTrue(Utility.pinOrNameIsWrong("", "pin"));
        assertTrue(Utility.pinOrNameIsWrong("name", ""));
        assertTrue(Utility.pinOrNameIsWrong(null , "pin"));
        assertTrue(Utility.pinOrNameIsWrong("name", null));
        assertTrue(Utility.pinOrNameIsWrong(null, null));
        assertTrue(Utility.pinOrNameIsWrong("name", "11111"));
        assertTrue(Utility.pinOrNameIsWrong("name", "111"));
        assertTrue(Utility.pinOrNameIsWrong("namenamenamenamenamenamename", "1111"));

    }


    @Test
    void testComparePins() {
        assertTrue(Utility.comparePins("1111", "1111"));
        assertFalse(Utility.comparePins("1111", "2222"));
    }

    @Test
    void testCleanInt() {
        assertEquals("1", Utility.cleanInt("01"));
        assertEquals("1", Utility.cleanInt("1"));
        assertNull(Utility.cleanInt("a"));
    }
}
