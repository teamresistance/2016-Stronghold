package org.teamresistance.auto;

import org.teamresistance.util.state.State;
import org.teamresistance.auto.AutoMaster;
import org.teamresistance.autostates.CrossDefense;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;
import org.teamresistance.IO;
import org.teamresistance.JoystickIO;

public class DriveToTower {

	public void drive(boolean front) {

		if (front) {
			frontOffset();
		}

		switch (Autonomous.goalNum) {
		case 0:
			driveLeft();
			break;
		case 2:
			driveRight();
			break;
		default:
			driveMiddle();
			break;
		}

	}

	public void frontOffset() {
		// add 180 and wrap to -180
	}

	public void driveLeft() {
		//turn
		//drive
		//turn
	}
	
	public void driveRight() {
		//turn
		//drive
		//turn
	}

	public void driveMiddle() {
		//turn
		//drive
		//turn
	}
}
