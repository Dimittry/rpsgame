package ru.begjavaweb.helpers;

import ru.begjavaweb.models.Result;
import java.util.*;


public class WinnerFinder {

	private final HashMap<String, Integer> signMap = new HashMap<>();
	
	private final HashMap<Integer, HashMap<String,String>> winnerSignMap = new HashMap<>();

	private final String paperName = "paper";

	private final String rockName = "rock";

	private final String scissorsName = "scissors";

	private final String playerName = "player";

	private final String compName = "computer";

	private final String playerSign;

	private final String compSign;


	public WinnerFinder(final String playerSign, final String compSign) {
		initSignMap();
		initWinnerSignMap();

		this.playerSign = playerSign;
		this.compSign = compSign;
	}

	public Result getWinner() throws Exception {
		if(playerSign.equals(compSign)) {
			return new Result("tied", playerSign, compSign);
		}
		HashMap<Integer, String> playersSignMap = getPlayersSignMap(playerSign, compSign);
		int signCode = calculateSignCode(playersSignMap.keySet());

		if(winnerSignMap.get(signCode) == null) {
			throw new Exception("Can't find a winner");
		}
		HashMap<String, String> choices = winnerSignMap.get(signCode);
		String winnerName = getWinnerName(choices, playersSignMap, signCode);
		
		return new Result(winnerName, choices.get("winnerChoice"), choices.get("loserChoice"));
	}

	private int getSignCode(final String sign) {
		return signMap.get(sign);
	}

	private int calculateSignCode(final Set<Integer> s) {
		int i = 0;
		int result = -1;

		for(Integer elem : s) {
			if(i == 0) {
				result = elem;
			} else {
				result &= elem;
			}
			i++;
		}
		return result;
	}

	private String getWinnerName(final HashMap<String, String> choices,
								 final HashMap<Integer, String> playersSignMap, 
								 final int signCode) throws Exception {

		
		String winnerName = playersSignMap.get(signMap.get(choices.get("winnerChoice")));
		if (winnerName == null) {
			throw new Exception("Can't find a winner name");
		}
		return winnerName;
	}

	private HashMap<Integer, String> getPlayersSignMap(final String playerSign, 
												 	   final String compSign) {
		HashMap<Integer, String> playersSignMap = new HashMap<>();
		playersSignMap.put(getSignCode(playerSign), playerName);
		playersSignMap.put(getSignCode(compSign), compName);
		return playersSignMap;
	}

	private void initSignMap() {
		signMap.put(rockName,1);
		signMap.put(paperName,2);
		signMap.put(scissorsName,3);
	}

	private void initWinnerSignMap() {
		winnerSignMap.put(0, createPlayersChoices(paperName, rockName));
		winnerSignMap.put(1, createPlayersChoices(rockName, scissorsName));
		winnerSignMap.put(2, createPlayersChoices(scissorsName, paperName));
	}

	private HashMap<String,String> createPlayersChoices(final String winnerChoice, 
														final String loserChoice) {
		HashMap<String,String> playerChoices = new HashMap<>();
		playerChoices.put("winnerChoice", winnerChoice);
		playerChoices.put("loserChoice", loserChoice);
		return playerChoices;
	}
}