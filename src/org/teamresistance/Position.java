package org.teamresistance;

public enum Position {

	UP, DOWN;
	
	public static Position next(Position current) {
		switch(current) {
		case UP:
			return DOWN;
		case DOWN:
			return UP;
		default:
			return UP;
		}
	}
}
