package ru.job4j.io;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


public class BracketsTest {
    public static final Brackets BRACKETS = new Brackets();
    public static final String VALID = "{{}}[]";
    public static final String EXPECTED = "[Pair{opening='{', openingPosition=1, closing='}', closingPosition=2}, Pair{opening='{', openingPosition=0, closing='}', closingPosition=3}, Pair{opening='[', openingPosition=4, closing=']', closingPosition=5}]";
    public static final String NOT_VALID = "{[}]";

    @Test
    public void isValid() {
        assertNotNull(BRACKETS.stats(VALID));
        assertEquals(BRACKETS.stats(VALID).toString(), EXPECTED);
    }

    @Test
    public void notValid() {
        assertNull(BRACKETS.stats(NOT_VALID));
    }
}