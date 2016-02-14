package org.teamresistance.robostates;

import edu.wpi.first.wpilibj.DoubleSolenoid;


public class ShooterDoubleSolenoid {
	DoubleSolenoid sol;
	
	ShooterDoubleSolenoid(int moduleNumber, int channel) {
		sol = new DoubleSolenoid(moduleNumber, channel);
		
	}
	
	public void launch() {
		sol.set(DoubleSolenoid.Value.kForward);
		
	}
	
	public void reset() {
		sol.set(DoubleSolenoid.Value.kReverse);
	}
	
}
