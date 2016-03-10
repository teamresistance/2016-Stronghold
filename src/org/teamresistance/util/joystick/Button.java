package org.teamresistance.util.joystick;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.GenericHID;

public class Button {
	
	private GenericHID joystick;
	private int buttonID;
	
	private boolean previousState = false;
	private boolean currentState = false;

	private List<Listener> listeners;
	
	public Button(GenericHID joystick, int buttonID) {
		this.joystick = joystick;
		this.buttonID = buttonID;
		this.currentState = joystick.getRawButton(buttonID);
		this.listeners = new ArrayList<>();
	}
	
	public void update() {
		previousState = currentState;
		currentState = joystick.getRawButton(buttonID);

		if (onButtonPressed()) {
			listeners.stream().forEach(Listener::onButtonPress);
		}
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

	public void addPressListener(Listener listener) {
		listeners.add(listener);
	}

	public void removePressListener(Listener listener) {
		listeners.remove(listener);
	}

	public interface Listener {

		void onButtonPress();

	}
}
