package snakeandladder.model;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Dice {

	private int minValue;
	private int maxValue;
	
	public int getMinValue() {
		return minValue;
	}
	
	public int getMaxValue() {
		return maxValue;
	}

	public Dice(int minValue, int maxValue) {
		super();
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	public int roll() throws NoSuchAlgorithmException {
		return SecureRandom.getInstanceStrong().nextInt(minValue, maxValue + 1);
	}
	
	
	
}
