package org.teamresistance.util;

public class Util {
	
    public static double span( double input, double inLo, double inHi, double outLo, double outHi) {
		if ( input < inLo ){
			return outLo;
		} else if ( input > inHi ) {
			return outHi;
		} else {
			return (outLo + (((input - inLo) / (inHi - inLo)) * (outHi - outLo)));
		}
    }
    
	public static double scaleJoytick(double input) {
	        if (Math.abs(input) < 0.15) {
	        	return 0;
	        } else if(input > 0) {
	        	return input <= 0.8 && input >= 0.05 ? ((input * 0.55) + 0.0725) :  ((input * 2.4375) - 1.4375);
	        } else {
	        	return input >= -0.8 && input <= -0.05 ? ((input * 0.55) - 0.0725) : ((input * 2.4375) + 1.4375);
	        }  
	}
}
