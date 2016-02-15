package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.io.NavXGyro;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousDefenses {
	NavXGyro gyro = new NavXGyro();
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
	
	void crossDefense() {
		AutonomousDefenses crosser = new AutonomousDefenses();
		
		switch(defenseType) {
		//case 1: crosser.crossPortcullis();
		//	break;
		//case 2: crosser.crossChevalDeFrise();
		//	break;
		case 3: crosser.crossMoat();
			break;
		case 4: crosser.crossRamparts();
			break;
		//case 5: crosser.crossDrawbridge();
		//	break;
		//case 6: crosser.crossSallyPort();
		//	break;
		case 7: crosser.crossRockWall();
			break;
		default: crosser.crossRoughTerrain();
			break;

		}
		
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
	
	
	public void crossMoat() {
		//check starting alignment
		int yawAngle = (int) gyro.getYawAngle();
		int rollAngle = (int) gyro.getRollAngle();
		int pitchAngle = (int) gyro.getPitchAngle();
		
		
		//use gyro to continue checking alignment
		while(!gyro.isLevel(10, rollAngle, pitchAngle)) {
			//drive forward
			IO.robotDrive.arcadeDrive(0.5, 0);
		} 
		
		gyro.turnTo(10, yawAngle); //straighten back out once off
		//keep track of position to determine when off defense
		
		isDone = true;
		
	}
	public void crossRamparts() {
		//check starting alignment
				int yawAngle = (int) gyro.getYawAngle();
				int rollAngle = (int) gyro.getRollAngle();
				int pitchAngle = (int) gyro.getPitchAngle();
				
				
				//use gyro to continue checking alignment
				while(!gyro.isLevel(10, rollAngle, pitchAngle)) {
					//drive forward
					IO.robotDrive.arcadeDrive(0.5, 0);
				} 
				
				gyro.turnTo(10, yawAngle); //straighten back out once off
				//keep track of position to determine when off defense
				
		isDone = true;
	}
	public void crossRockWall() {
		//check starting alignment
				int yawAngle = (int) gyro.getYawAngle();
				int rollAngle = (int) gyro.getRollAngle();
				int pitchAngle = (int) gyro.getPitchAngle();
				
				
				//use gyro to continue checking alignment
				while(!gyro.isLevel(10, rollAngle, pitchAngle)) {
					//drive forward
					IO.robotDrive.arcadeDrive(0.5, 0);
				} 
				
				gyro.turnTo(10, yawAngle); //straighten back out once off
				//keep track of position to determine when off defense
		isDone = true;
	}
	public void crossRoughTerrain() {
		//check starting alignment
				int yawAngle = (int) gyro.getYawAngle();
				int rollAngle = (int) gyro.getRollAngle();
				int pitchAngle = (int) gyro.getPitchAngle();
				
				
				//use gyro to continue checking alignment
				while(!gyro.isLevel(10, rollAngle, pitchAngle)) {
					//drive forward
					IO.robotDrive.arcadeDrive(0.5, 0);
				} 
				
				gyro.turnTo(10, yawAngle); //straighten back out once off
				//keep track of position to determine when off defense
				
		isDone = true;
	}
}

/*public void crossDrawbridge() {
		
	}
	public void crossSallyPort() {
		
	}
	public void crossPortcullis() {
		
	}
	public void crossChevalDeFrise() {
		
	}*/
