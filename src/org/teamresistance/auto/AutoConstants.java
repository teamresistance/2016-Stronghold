package org.teamresistance.auto;

public class AutoConstants {
	
	public static final double COURTYARD_SPEED = 0.5;
	
	public static final int ANGLE_ERROR_THRESHOLD = 10;
	
	final public static int[][] START_ANGLES = {
		{160, 160, 160},
		{160, 160, 160},
		{160, 160, 160},
		{160, 160, 160}
	};
	final public static double[][] DISTANCES = {
		{1.5, 1.5, 1.5},
		{1.5, 1.5, 1.5},
		{1.5, 1.5, 1.5},
		{1.5, 1.5, 1.5}
	};
	final public static int[][] END_ANGLES = {
		{30, 30, 30}, 
		{30, 30, 30},
		{30, 30, 30},
		{30, 30, 30}
	};
	
	public static int defenseType = 0;
	public static int defensePosition = 0;
	public static int goalPosition = 0;
}
