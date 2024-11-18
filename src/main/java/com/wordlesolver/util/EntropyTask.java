package com.wordlesolver.util;

import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;
import static com.wordlesolver.solver.EntropyCalculator.rankWordsByEntropy;

public class EntropyTask extends RecursiveTask<Map<String, Double>> {
    private final List<String> allWords;
    private final List<String> possibleWords;

    public EntropyTask(List<String> allWords, List<String> possibleWords) {
        this.allWords = allWords;
        this.possibleWords = possibleWords;
    }

    @Override
    public Map<String, Double> compute() {
        if (allWords.size() < 10) { // Small enough do it urself
            return rankWordsByEntropy(allWords, possibleWords);
        } else {
            int mid = allWords.size() / 2;
            EntropyTask left = new EntropyTask(allWords.subList(0, mid), possibleWords);
            EntropyTask right = new EntropyTask(allWords.subList(mid, allWords.size()), possibleWords);
            left.fork();
            Map<String, Double> rightResult = right.compute();
            Map<String, Double> leftResult = left.join();
            leftResult.putAll(rightResult);
            return leftResult;
        }
    }
}
