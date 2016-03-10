package org.teamresistance.util.state;

public class StubState extends State {

	private final OnEntry onEntry;

	public StubState() {
		// Empty implementation
		this.onEntry = () -> {};
	}

	public StubState(String name, OnEntry onEntry) {
		this.onEntry = onEntry;
		this.name = name;
	}

	@Override
	public void onEntry(StateTransition e) {
		onEntry.onEntry();
	}

	@FunctionalInterface
	public interface OnEntry {

		void onEntry();

	}

}
