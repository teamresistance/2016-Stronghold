package org.teamresistance.robostates;

import org.teamresistance.IO;

public class Flipper {
	//the thingy that goes under the portcullis to lift it
	
	public void raise() {
		IO.flipperSolenoid.set(true);
	}
	
	public void lower() {
		IO.flipperSolenoid.set(false);
	}
	
}
