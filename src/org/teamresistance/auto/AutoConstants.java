package org.teamresistance.auto;

public class AutoConstants {
	
	public static final double PORTCULLIS_LIFTER_UP_SPEED = 0.5;
	public static final double PORTCULLIS_LIFTER_DOWN_SPEED = 0.5;
	public static final double CHEVAL_CROSS_SPEED = 0.5;
	public static final double MOAT_CROSS_SPEED = 0.5;
	public static final double RAMPARTS_CROSS_SPEED = 0.5;
	public static final double ROCK_WALL_CROSS_SPEED = 0.5;
	public static final double ROUGH_TERRAIN_CROSS_SPEED = 0.5;
	
	public static final double COURTYARD_SPEED = 0.75;
	
	public static final int ANGLE_ERROR_THRESHOLD=10;
	
	final public static int[][] START_ANGLES = {
		{160, 160, 160},
		{160, 160, 160},
		{160, 160, 160},
		{160, 160, 160}
	};
	final public static double[][] DISTANCES = {
		{2, 2.0, 2.0},
		{2.0, 2.0, 2.0},
		{2.0, 2.0, 2.0},
		{2.0, 2.0, 2.0}
	};
	final public static int[][] END_ANGLES = {
		{0, 0, 0}, 
		{0, 0, 0},
		{0, 0, 0},
		{0, 0, 0}
	};
	
}
