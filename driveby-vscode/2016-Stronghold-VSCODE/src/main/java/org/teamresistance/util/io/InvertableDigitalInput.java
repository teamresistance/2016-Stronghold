package org.teamresistance.util.io;

import edu.wpi.first.wpilibj.DigitalInput;

public class InvertableDigitalInput extends DigitalInput {

	private boolean inverted = false;
	
	public InvertableDigitalInput(int channel) {
		this(channel, false);
	}

	public InvertableDigitalInput(int channel, boolean inverted) {
		super(channel);
		this.inverted = inverted;
	}

	@Override
	public boolean get() {
		return super.get() ^ inverted; // Conditionally inverts the output
	}

	public boolean isInverted() {
		return inverted;
	}

	public void setInverted(boolean inverted) {
		this.inverted = inverted;
	}
}
