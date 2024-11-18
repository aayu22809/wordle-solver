package com.wordlesolver.solver;

import com.wordlesolver.model.WordleResult;
import com.wordlesolver.util.EntropyTask;
import com.wordlesolver.util.WordListLoader;
import wordletester.WordleTester;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import static com.wordlesolver.model.WordlePattern.generatePattern;
import static com.wordlesolver.solver.EntropyCalculator.calculateEntropy;

public class WordleSolver implements com.wordlesolver.util.WordleSolver {
    private static final int MAX_GUESSES = 5;
    private static final String BEST_GUESS = "tares";
    private int guesses;
    private WordleResult result;
    private List<String> guessWords;
    private List<String> answerWords;

    public WordleSolver() {
        try {
            guessWords = WordListLoader.loadGuessWords();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        guesses = 0;
        result = new WordleResult(guessWords);
    }


    public void makeGuess(String guess, String pattern) {
        result.applyPattern(guess, pattern);
        guesses++;
    }

    public String getGuess() {
        return new EntropyTask(result.getRemainingWords(), guessWords).compute().keySet().toArray()[0].toString();
    }

    @Override
    public String getNextGuess(String guess, String guessResult) { // idk what to do with thoes
        makeGuess(guess, guessResult);
        return getGuess();
    }


    public void playGame() {

        String guess = BEST_GUESS;
        while (true) {
            System.out.println("Remaining uncertainty: " + result.getUncertainty());
            System.out.printf("Guess: %s; Information: %f bits\n", guess, calculateEntropy(guess, result.getRemainingWords()));
            String pattern = getUserInput("Pattern");
            makeGuess(guess, pattern);
            guess = getGuess();
        }
    }

    private static String getUserInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt + ": ");
        String input = scanner.nextLine().toUpperCase();
        System.out.println("...");
        return input;
    }

    public void calcAvg(int numGames) {

        try {
            answerWords = WordListLoader.loadAnswerWords();
            guessWords = WordListLoader.loadGuessWords();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        double sum = 0;
        int i = 0;
        List<String> sublist = answerWords.subList(0, numGames);
        for (String word : sublist) {
            int guesses = simulateGame(guessWords, word);
            sum += guesses;
            ++i;
            System.out.println("Average number of guesses: " + sum / (double) i);
        }

    }

    public void calcAvg(int minIndex, int maxIndex) {

        try {
            answerWords = WordListLoader.loadAnswerWords();
            guessWords = WordListLoader.loadGuessWords();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        double sum = 0;
        int i = 0;
        List<String> sublist = answerWords.subList(minIndex, maxIndex);
        for (String word : sublist) {
            int guesses = simulateGame(guessWords, word);
            System.out.println("Guesses: " + guesses);
            sum += guesses;
            System.out.println(++i);
        }
        sum /= (double) sublist.size();
        System.out.println("Average number of guesses: " + sum);

    }

    public static int simulateGame(List<String> possibleWords, String word) {
        WordleResult result = new WordleResult(possibleWords);
        String guess = "tares";
        int i = 0;
        System.out.println("Word: " + word);
        System.out.print("\t");
        while (!word.equals(guess)) {
            String pattern = generatePattern(word, guess);
            result.applyPattern(guess, pattern);
            i++;
            guess = new EntropyTask(result.getRemainingWords(), possibleWords).compute().keySet().toArray()[0].toString();
            System.out.printf("Guess: %s; ", guess);
        }

        return i;
    }

}
