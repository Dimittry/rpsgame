package ru.begjavaweb.generators;

import java.util.Random;
import java.util.HashMap;

 public class SignGenerator {

 	private final int minRangeValue = 0;
 	
 	private final int maxRangeValue = 2;
 	
 	public String getGeneratedSign() {
 		HashMap<Integer, String> map = getSignMap();
 		int randomNumber = getRandomNumberInRange(minRangeValue,maxRangeValue);
 		return map.get(randomNumber);
 	}

 	public int getRandomNumberInRange(final int min, final int max) {
 		if(min >= max) {
 			throw new IllegalArgumentException("max must be greater than min");
 		}

 		Random r = new Random();
 		return r.nextInt((max - min) + 1) + min;
 	}

 	public HashMap<Integer, String> getSignMap() {

 		HashMap<Integer, String> map = new HashMap<Integer, String>();
 		map.put(0, "rock");
 		map.put(1, "scissors");
 		map.put(2, "paper");

 		return map;
 	}

 }