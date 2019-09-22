package org.teamresistance.util.joystick;

import edu.wpi.first.wpilibj.GenericHID;

public class Button {
	
	private GenericHID joystick;
	private int buttonID;
	
	private boolean previousState = false;
	private boolean currentState = false;
	
	public Button(GenericHID joystick, int buttonID) {
		this.joystick = joystick;
		this.buttonID = buttonID;
		this.currentState = joystick.getRawButton(buttonID);
	}
	
	public void update() {
		previousState = currentState;
		currentState = joystick.getRawButton(buttonID);
	}
	
	public boolean isDown() {
		return currentState;
	}	
	
	public boolean onButtonPressed() {
		return currentState && !previousState;
	}
	
	public boolean onButtonReleased() {
		return previousState && !currentState;
	}
}
