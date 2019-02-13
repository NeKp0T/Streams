package com.example.streams;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        fail();
    }

    @Test
    public void testPiDividedBy4() {
        fail();
    }

    @Test
    public void testFindPrinter() {
        Map<String, List<String>> compositions = new HashMap<>();
        List<String> sashaList = new ArrayList<>();
        sashaList.add("1");
        sashaList.add("2");
        sashaList.add("3");
        sashaList.add("4");
        List<String> vladList = new ArrayList<>();
        vladList.add("1");
        vladList.add("2");
        vladList.add("3");
        vladList.add("4");
        vladList.add("5");
        compositions.put("Sasha", new ArrayList<>(sashaList));
        compositions.put("Vlad", vladList);
        assertEquals("Vlad", SecondPartTasks.findPrinter(compositions));
        sashaList.add("5");
        sashaList.add("6");
        compositions.put("Sasha2", sashaList);
        assertEquals("Sasha2", SecondPartTasks.findPrinter(compositions));
    }

    @Test
    public void testCalculateGlobalOrder() {
        fail();
    }
}