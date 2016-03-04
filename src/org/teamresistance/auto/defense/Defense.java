package org.teamresistance.auto.defense;

public abstract class Defense {

	private boolean crossing = false;

	protected void setCrossed() {
		this.crossing = false;
	}

	public boolean isCrossing() {
		return crossing;
	}

	public final void beginCrossing() {
		this.crossing = true;
		beforeCrossing();
	}

    public void beforeCrossing() {
        //
    }

	public abstract boolean isReversed();

	public abstract void whileCrossing();

}
