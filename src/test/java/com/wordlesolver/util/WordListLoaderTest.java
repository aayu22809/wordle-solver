package com.wordlesolver.util;

import com.wordlesolver.model.WordlePattern;
import com.wordlesolver.model.WordleResult;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import static com.wordlesolver.solver.EntropyCalculator.calculateEntropy;
import static com.wordlesolver.solver.EntropyCalculator.rankWordsByEntropy;

/**
 * WordListLoaderTest
 * 	•	Ensures word lists load correctly.
 */
public class WordListLoaderTest {
    public static void main(String[] args) {
        List<String> words;
        try {
            words = WordListLoader.loadAnswerWords();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Loaded " + words.size() + " words.");
    }
}
