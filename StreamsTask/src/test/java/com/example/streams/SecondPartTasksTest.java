package com.example.streams;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        var paths = List.of("testFindQuotesFile1.txt", "testFindQuotesFile2.txt", "testFindQuotesFile3.txt");
        var expected = List.of(
                "@JeremyList ideone.com/weYI1e it prints true. – Martin Vseticka May 11 '16 at 10:43",
                "boolean  b = string.startsWith(\"Mad\");  // true",
                "b = string.endsWith(\"dam\");             // true",
                "b = string.indexOf(\"I am\") >= 0;        // true",
                "true kekekek",
                "dftrueasdasd",
                "sdf  true",
                "true",
                "true",
                "true"
        );
        assertEquals(expected, SecondPartTasks.findQuotes(paths, "true"));
    }

    @Test
    public void testPiDividedBy4() {
        fail();
    }

    @Test
    public void testFindPrinter() {
        fail();
    }

    @Test
    public void testCalculateGlobalOrder() {
        fail();
    }
}