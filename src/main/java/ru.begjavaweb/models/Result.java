package ru.begjavaweb.models;

public class Result {

	final private String winner;

	final private String winnerChoice;

	final private String loserChoice;

	public Result(
		final String winner, 
		final String winnerChoice, 
		final String loserChoice
	) {
		this.winner =  winner;
		this.winnerChoice = winnerChoice;
		this.loserChoice = loserChoice;
	}

	public String getWinner() {
		return winner;
	}

	public String getWinnerChoice() {
		return winnerChoice;
	}

	public String getLoserChoice() {
		return loserChoice;
	}

	public String toString() {
		return winner + " " + winnerChoice + " " + loserChoice;
	}
}