package org.teamresistance.auto;

public class AutoConstants {
	
	public static final double COURTYARD_SPEED = 0.5;
	public static final double SNORFLE_TIME = 0.75;
	public static final int ANGLE_ERROR_THRESHOLD = 10;
	
	final public static int[][] START_ANGLES = {
		{0, -40, 0},
		{18, -16, 0},
		{0, 16, -18},
		{0, 40, 0}
	};
	final public static double[][] DISTANCES = {
		{3.0, 2.5, 0},
		{3.8, 2, 0},
		{0, 2, 3.8},
		{0, 2.5, 3.0}
	};
	final public static int[][] END_ANGLES = {
		{30, 30, 30}, 
		{30, 30, 30},
		{30, 30, 30},
		{30, 30, 30}
	};
	
	
}
