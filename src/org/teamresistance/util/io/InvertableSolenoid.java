package org.teamresistance.util.io;

import edu.wpi.first.wpilibj.Solenoid;

public class InvertableSolenoid extends Solenoid {

	private boolean inverted;
	
	public InvertableSolenoid(int moduleNumber, int channel) {
		super(moduleNumber, channel);
		this.inverted = false;
	}
	
	public InvertableSolenoid(int channel) {
		super(channel);
		this.inverted = false;
	}
	
	public InvertableSolenoid(int moduleNumber, int channel, boolean inverted) {
		super(moduleNumber, channel);
		this.inverted = inverted;
	}
	
	public InvertableSolenoid(int channel, boolean inverted) {
		super(channel);
		this.inverted = inverted;
	}

	@Override
	public void set(boolean on) {
		super.set(on ^ inverted);
	}
	
	public void setInverted(boolean inverted) {
		this.inverted = inverted;
	}
	
	public boolean isInverted() {
		return inverted;
	}
	
}
