package org.teamresistance.robostates;

import org.teamresistance.IO;

public class GateGrabber {
	
	public static void out() {
		IO.gateGrabberSolenoid.set(true);
	}
	
	public static void in() {
		IO.gateGrabberSolenoid.set(false);
	}
}
