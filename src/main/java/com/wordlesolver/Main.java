package com.wordlesolver;

import com.wordlesolver.model.WordleResult;
import com.wordlesolver.solver.WordleSolver;
import com.wordlesolver.util.EntropyTask;
import com.wordlesolver.util.WordListLoader;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import static com.wordlesolver.model.WordlePattern.generatePattern;
import static com.wordlesolver.solver.EntropyCalculator.calculateEntropy;
import static com.wordlesolver.solver.WordleSolver.simulateGame;
import static java.lang.System.nanoTime;

/**
 * Main
 * 	•	The entry point for the program.
 * 	•	Handles:
 * 	•	CLI input for guesses and results.
 * 	•	Calls the solver to suggest the next best guesses.
 * 	•	Displays the average expected information and the information received.
 */
public class Main {

    public static void main(String[] args) {
        WordleSolver wordleSolver = new WordleSolver();

        /*
        long start = nanoTime();
        wordleSolver.calcAvg(1000);
        long end = nanoTime();
        System.out.println("Time: " + (end - start) / 1_000_000 + "ms");
        */


        wordleSolver.playGame();
    }

}