package org.teamresistance.util.state;

public abstract class ReturnState extends State {

	private String returnState;
	
	public ReturnState() {
		
	}
	
	public ReturnState(String returnState) {
		this.returnState = returnState;
	}
	
	@Override
	public abstract void init();

	@Override
	public abstract void onEntry(StateTransition e);

	@Override
	public abstract void update();

	@Override
	public abstract void onExit(StateTransition e);

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
