package com.wordlesolver.util;

public interface WordleSolver {

	/**
	 * Given a guess and color result, this method returns the next word to guess.
	 * <br><br>
	 * The first time you call this method, you should pass in (null, null)
	 * to indicate a first guess.  In this case, your getNextGuess method
	 * can return any first guess you would like.  This can be the first word in the
	 * Wordle list of possible words, it can be a random word from the Wordle list,
	 * or it can be a special starting word you selected for whatever reason.
	 * <br><br>
	 * Subsequent calls to getNextGuess should use the result of obj.getResponse(guess)
	 * as your color where obj is a WordleTester object.
	 * @param guess
	 * @param guessResult
	 * @return The next word to guess
	 */
	String getNextGuess(String guess, String guessResult);
}
