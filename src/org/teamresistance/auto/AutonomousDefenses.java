package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.io.NavXGyro;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousDefenses {
	//NavXGyro gyro = new NavXGyro();
	int defensePos;
	int defenseType; 
	Boolean isDone;

	public void AutonomousDefenses() {
		SmartDashboard.putNumber("position", 3);
		SmartDashboard.putNumber("defense type", 5);
		setDefenseType();
		setDefensePos();
	}
	
	public Boolean isDone() {
		return isDone;
	}
		
		void setDefenseType() {
			defenseType = (int) SmartDashboard.getNumber("defense type");
		}
		
		void setDefensePos() {
			defensePos = (int) SmartDashboard.getNumber("position");
		}
		
		public int getDefensePos() {
			return defensePos;
		}
		
		public int getDefenseType(){
			return defenseType;
		}
	
	
}