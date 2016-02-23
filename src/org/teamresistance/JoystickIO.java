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
	public static Button btnAngleHold;
	
	public static Button btnScore;
	public static Button btnAntler;
	public static Button btnSnorfler;
	public static Button btnCancel;
	public static Button btnSnorflerReverse;
	
	public static Button btnToggleFoot;
	public static Button btnToggleLifter;
	public static Button btnPortcullis;
	public static Button btnDrawbridge;
	public static Button btnToggleLifterPosition;
	
	public static void init() {
		leftJoystick = new Joystick(0);
		rightJoystick = new Joystick(1);
		codriverStick = new Joystick(2);
		
		btnDriveMode = newButton(leftJoystick, 3);
		btnDriveReverse = newButton(leftJoystick, 1);
		btnAngleHold = newButton(rightJoystick, 1);
		
		btnScore = newButton(codriverStick, 1);
		btnAntler = newButton(codriverStick, 4);
		btnSnorfler = newButton(codriverStick, 3);
		btnCancel = newButton(codriverStick, 2);
		btnSnorflerReverse = newButton(codriverStick, 10);
		
		btnToggleFoot = newButton(codriverStick, 5);
		btnToggleLifter = newButton(codriverStick, 6);
		btnPortcullis = newButton(codriverStick, 7);
		btnDrawbridge = newButton(codriverStick, 8);
		
		btnToggleLifterPosition = newButton(codriverStick, 9);
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
