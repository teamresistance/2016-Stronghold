package org.teamresistance.util;

public class Time {

	public static final long SECOND = 1000000000L;
	
	private static double delta;
	
	private static double previousTime = getTime();
	
	public static void update() {
		double currentTime = getTime();
		delta = currentTime - previousTime;
		previousTime = currentTime;
	}
	
	public static double getDelta() {
		return delta;
	}
	
	public static double getTime() {
		return (double)System.nanoTime() / SECOND;
	}
	
}
