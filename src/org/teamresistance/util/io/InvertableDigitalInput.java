package org.teamresistance.util.io;

public class InvertableDigitalInput extends edu.wpi.first.wpilibj.DigitalInput {

	private boolean inverted = true;
	
	public InvertableDigitalInput(int channel) {
		super(channel);
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
