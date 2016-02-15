package org.teamresistance.auto;

public class Positioning {
	Boolean correctPos;
	int threshold;
	
	Positioning(int x, int y, int theta, int newThreshold) {
		threshold = newThreshold;
	}
	
	public Boolean posCorrect() {
		return correctPos;
	}
	
	
	
}
