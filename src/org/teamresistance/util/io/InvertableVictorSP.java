package org.teamresistance.util.io;

import edu.wpi.first.wpilibj.VictorSP;

public class InvertableVictorSP extends VictorSP {

	private boolean inverted = false;
	
	public InvertableVictorSP(int channel, boolean inverted) {
		super(channel);
		this.inverted = inverted;
	}
	
	public InvertableVictorSP(int channel) {
		this(channel, false);
	}

	@Override
	public void set(double speed) {
		super.set(speed * (inverted ? -1.0 : 1.0));
	}

	@Override
	public void pidWrite(double output) {
		super.pidWrite(output * (inverted ? -1.0 : 1.0));
	}

	public boolean isInverted() {
		return inverted;
	}

	public void setInverted(boolean inverted) {
		this.inverted = inverted;
	}	
}
