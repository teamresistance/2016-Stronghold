package org.teamresistance.defenses;

import org.teamresistance.util.io.NavXGyro;

public class DefenseMaster {
	public static ChevalDeFrise teeter;
	public static Drawbridge drawbridge;
	public static Moat moat;
	public static Portcullis portcullis;
	public static Ramparts ramparts;
	public static RockWall rockWall;
	public static RoughTerrain roughTerrain;
	public static SallyPort sallyPort;
	public static NavXGyro gyro;
	
	public static void init() {
		gyro = new NavXGyro();
		teeter = new ChevalDeFrise();
		drawbridge = new Drawbridge();
		moat = new Moat();
		portcullis = new Portcullis();
		ramparts = new Ramparts();
		rockWall = new RockWall();
		roughTerrain = new RoughTerrain();
		sallyPort = new SallyPort();
		
	}
}
