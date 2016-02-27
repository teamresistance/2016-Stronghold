package org.teamresistance.auto;

public abstract class Defense {

	private boolean crossing;
	
	public Defense() {
		crossing = false;
	}
	
	public boolean isCrossing() {
		return crossing;
	}
	
	public void setCrossing(boolean crossing) {
		this.crossing = crossing;
	}

	public abstract boolean isReversed();

	public void beginCrossing() {
		//
	};

	public abstract void whileCrossing();

}
