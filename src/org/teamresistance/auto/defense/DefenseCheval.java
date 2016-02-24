package org.teamresistance.auto.defense;

import org.teamresistance.IO;
import org.teamresistance.auto.AutoConstants;
import org.teamresistance.auto.Autonomous;
import org.teamresistance.auto.Defense;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.StateTransition;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static edu.wpi.first.wpilibj.networktables.NetworkTablesJNI.putString;
	 /*
     * Four states: initial positioning, defense crossing, tower positioning, targeting/shooting
	 */

public class DefenseCheval extends Defense {

    //private double time = 0.0;
    String didSelectorWork = "Yes";

    @Override
    public void beginCrossing() {
        SmartDashboard.putString("Selector",didSelectorWork);
    }

    @Override
    public void whileCrossing() {
//        time += Time.getDelta();
//
//		if(Time.getDelta()>0.5) {
//			AntlerMachine.getState("AntlersUp");
//
//		}
//
//		if(!DefenseMaster.imu.isLevel(0, 0, AutoConstants.ANGLE_ERROR_THRESHOLD) && time<2.0) {
//			don't know if I can do it like this - check to make sure it doesn't freeze up
//
//			IO.robotDrive.arcadeDrive(AutoConstants.CHEVAL_CROSS_SPEED, 0.0);
//		}
//		else {
//
//			turn
//			drive
//			turn
//			AutoMaster.driver.drive(false);

    }

}


//private class AntlerMachine {
//}
//}