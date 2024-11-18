package com.wordlesolver.solver;

import com.wordlesolver.model.WordlePattern;
import com.wordlesolver.util.QuickSort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class EntropyCalculator {


    public static double calculateEntropy(String word, List<String> possibleWords) {
        int possibilities = possibleWords.size();
        double information = 0;
        Map<String, Integer> patternCounts = WordlePattern.generatePatternCounts(word, possibleWords);

        for (Map.Entry<String, Integer> entry : patternCounts.entrySet()) {
            double probability = (double) entry.getValue() / possibilities;
//            information += probability * -Math.log(probability) / Math.log(2);
            information += probability;
        }

        return information;
    }

    /**
     * Ranks words by their entropy.
     *
     * @param allWords      The list of all possible words.
     * @param possibleWords The list of possible words.
     * @return A map of words to their entropy.
     */
    public static Map<String, Double> rankWordsByEntropy(List<String> allWords, List<String> possibleWords) {
        Map<String, Double> wordEntropies = new HashMap<>(allWords.size());
        for (String word : allWords) {
            wordEntropies.put(word, calculateEntropy(word, possibleWords));
        }
        return QuickSort.sortByValueDescending(wordEntropies);
    }

}