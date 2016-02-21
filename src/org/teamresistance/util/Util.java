package org.teamresistance.util;

public class Util {
	
	public static double span(double input, double inLo, double inHi,
			double outLo, double outHi) {
		if (input < inLo) {
			return outLo;
		} else if (input > inHi) {
			return outHi;
		} else {
			return (outLo + (((input - inLo) / (inHi - inLo)) * (outHi - outLo)));
		}
	}

	public static double scaleJoytick(double input) {
		if (Math.abs(input) < 0.05) {
			return 0;
		} else if (input > 0) {
			return input <= 0.65 ? (0.02225+0.735 * input) : (-0.428571+1.42857 * input);
		} else {
			return input >= -0.65 ? (-0.02225+0.735 * input) : (0.428571+1.42857 * input);
		}

		// if (Math.abs(input) < 0.05) {
		// return 0;
		// } else if(input > 0) {
		// return input <= 0.85 ? (0.0314375+0.55125*input) :
		// (-2.33333+3.33333*input);
		// } else {
		// return input >= -0.85 ? (-0.0314375+0.55125*input) :
		// (2.33333+3.33333*input);
		// }

		// if (Math.abs(input) < 0.05) {
		// return 0;
		// } else if(input > 0) {
		// return input <= 0.8 && input >= 0.05 ? ((input * 0.55) + 0.0725) :
		// ((input * 2.4375) - 1.4375);
		// } else {
		// return input >= -0.8 && input <= -0.05 ? ((input * 0.55) - 0.0725) :
		// ((input * 2.4375) + 1.4375);
		// }
	}

	public static double clip(double input, double min, double max) {
		if (input <= min) {
			return min;
		} else if (input >= max) {
			return max;
		} else {
			return input;
		}
	}
}
