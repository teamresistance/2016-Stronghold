package org.teamresistance.robostates;

import org.teamresistance.util.io.InvertableSolenoid;

public class ShooterInvertableSolenoid {
	int channel;
	int moduleNumber;
	
	ShooterInvertableSolenoid(int newChannel, int newModuleNumber) {
		channel = newChannel;
		moduleNumber = newModuleNumber;
	}
	
	public void launch() {
		
		InvertableSolenoid shooter = new InvertableSolenoid(moduleNumber, channel, true);
		
	}
	
	public void reset() {
	
		InvertableSolenoid shooter = new InvertableSolenoid(moduleNumber, channel, false);
		
	}
	
}
