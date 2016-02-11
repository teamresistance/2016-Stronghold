package org.teamresistance;

import java.util.ArrayList;

import org.teamresistance.util.joystick.Button;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;

public class JoystickIO {
	
	public static Joystick leftJoystick;
	public static Joystick rightJoystick;
	public static Joystick codriverStick;
	
	private static ArrayList<Button> buttons = new ArrayList<>();
	
	public static Button btnDriveMode;
	public static Button btnDriveReverse;
	
	public static Button btnScore;
	public static Button btnAntler;
	public static Button btnSnorfler;
	public static Button btnCancelSnorfle;
	
	public static void init() {
		leftJoystick = new Joystick(0);
		rightJoystick = new Joystick(1);
		codriverStick = new Joystick(2);
		
		btnDriveMode = newButton(leftJoystick, 3);
		btnDriveReverse = newButton(leftJoystick, 1);
		
		btnScore = newButton(codriverStick, 1);
		btnAntler = newButton(codriverStick, 4);
		btnSnorfler = newButton(codriverStick, 3);
		btnCancelSnorfle = newButton(codriverStick, 2);
		
	}
	
	public static void update() {
		for(Button button : buttons) {
			button.update();
		}
	}
	
	private static Button newButton(GenericHID joystick, int buttonID) {
		Button button = new Button(joystick, buttonID);
		buttons.add(button);
		return button;
	}
}
