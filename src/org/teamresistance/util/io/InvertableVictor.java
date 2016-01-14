package org.teamresistance.util.io;

import edu.wpi.first.wpilibj.Victor;

public class InvertableVictor extends Victor {
	
	private boolean inverted = false;
	
	public InvertableVictor(int channel) {
		super(channel);
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
