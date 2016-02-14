package org.teamresistance.robostates;

import org.teamresistance.IO;


public class Shooter {
	
	public void launch() {
		
		IO.shooterSolenoid.set(true);
		
	}
	
	public void reset() {
	
		IO.shooterSolenoid.set(false);
		
	}
	
}
