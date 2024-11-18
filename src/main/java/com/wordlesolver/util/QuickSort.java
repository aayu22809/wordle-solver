package com.wordlesolver.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Full Disclosure: This code is from https://www.geeksforgeeks.org/quick-sort/
public class QuickSort {
    // Method to sort a Map by its values in descending order
    public static Map<String, Double> sortByValueDescending(Map<String, Double> map) {
        // Convert the map into a list of entries
        List<Map.Entry<String, Double>> entryList = new ArrayList<>(map.entrySet());

        // Sort the list based on the values in descending order
        entryList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        // Maintain the order in a LinkedHashMap
        Map<String, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}