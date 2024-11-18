package com.wordlesolver.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class QuickSort {
    // Method to sort a Map by its values in descending order
    public static Map<String, Double> sortByValueDescending(Map<String, Double> map) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Double>> list = new ArrayList<>(map.entrySet());

        // Sort the list
        list.sort((o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));

        // put data from sorted list to hashmap
        Map<String, Double> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}