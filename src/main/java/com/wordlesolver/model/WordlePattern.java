package com.wordlesolver.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WordlePattern {

    /**
     * Static  method to generate a pattern from a guess and an answer.
     *
     * @param   guess
     *          The guessed word.
     *
     * @param   answer
     *          The actual answer.
     *
     * @return A new WordlePattern instance.
     */
    public static String generatePattern(String guess, String answer) {
        if (guess.length() != 5 || answer.length() != 5) {
            throw new IllegalArgumentException("Guess and answer must both be 5 letters.");
        }
        char[] pattern = new char[5];
        boolean[] used = new boolean[5];

        // First pass: exact matches
        for (int i = 0; i < 5; i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                pattern[i] = 'G';
                used[i] = true;
            } else {
                pattern[i] = 'X';
            }
        }

        // Second pass: partial matches
        for (int i = 0; i < 5; i++) {
            if (pattern[i] == 'G') continue;
            for (int j = 0; j < 5; j++) {
                if (guess.charAt(i) == answer.charAt(j) && !used[j]) {
                    pattern[i] = 'Y';
                    used[j] = true;
                    break;
                }
            }
        }

        return new String(pattern);
    }


    /**
     * Generates a map of patterns and their counts based on a guess and possible answers.
     *
     * @param   guess
     *          The guess word.
     *
     * @param   possibleAnswers
     *          The list of possible answers.
     *
     * @return A map where keys are patterns and values are their occurrence counts.
     */
    public static Map<String, Integer> generatePatternCounts(String guess, List<String> possibleAnswers) {
        Map<String, Integer> patternCounts = new HashMap<>();

        for (String answer : possibleAnswers) {
            patternCounts.put(WordlePattern.generatePattern(guess, answer), patternCounts.getOrDefault(WordlePattern.generatePattern(guess, answer), 0) + 1);
        }

        return patternCounts;
    }

}
