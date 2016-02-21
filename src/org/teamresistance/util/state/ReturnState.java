package org.teamresistance.util.state;

public abstract class ReturnState extends State {

	private String returnState;

	/* Default constructor so children don't need one */
	public ReturnState() {
	}
	
	public ReturnState(String returnState) {
		this.returnState = returnState;
	}

	public void setReturnState(String returnState) {
		this.returnState = returnState;
	}
	
	public String getReturnState() {
		return returnState;
	}
	
	protected void gotoReturnState() {
		if(returnState == null) return;
		gotoState(returnState);
	}
	
}