package com.wordlesolver.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles loading words from the files.
 * 	Methods:
 * 	•	List<String> loadGuessWords(): Loads the guess words.
 * 	•	List<String> loadAnswerWords(): Loads the answer words.
 */
public class WordListLoader {
    private static final String GUESS_WORDS_FILE = "src/main/resources/guess_words.txt";
    private static final String ANSWER_WORDS_FILE = "src/main/resources/answer_words.txt";

    /**
     * Loads the guess words.
     * @return List of guess words.
     */
    public static List<String> loadGuessWords() throws FileNotFoundException {
        File file = new File(GUESS_WORDS_FILE);
        return loadWords(file);
    }

    public static List<String> loadAnswerWords() throws FileNotFoundException {
        File file = new File(ANSWER_WORDS_FILE);
        return loadWords(file);
    }

    /**
     * Loads the words from a specified txt file path to a list.
     *
     * @param   file
     *          The file to load words from.
     *
     * @return {@code List} of words.
     *
     * @throws  FileNotFoundException
     *          If the file is not found.
     */
    private static List<String> loadWords(File file) throws FileNotFoundException {
        List<String> words = new ArrayList<>();
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            words.add(scanner.nextLine());
        }
        scanner.close();
        return words;
    }
}
