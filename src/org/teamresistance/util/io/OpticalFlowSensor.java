package org.teamresistance.util.io;

import org.teamresistance.mathd.Vector2d;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OpticalFlowSensor {
	
	private DualGyro gyro;
	private SPI spi;
	private byte[] dataReceived;
	private byte[] register = new byte[] {0};
	
	private Vector2d pos = new Vector2d(0, 0);
	
	private double ticksPerFootX = 250.5; //268
	private double ticksPerFootY = 274;
	
	private int clockRate = 500000;
	
	private static final byte MOTION_REGISTER = (byte)2;
	private static final byte DY_REGISTER = (byte)3;
	private static final byte DX_REGISTER = (byte)4;
	
	public OpticalFlowSensor(SPI.Port port) {
		spi = new SPI(port);
		spi.setChipSelectActiveLow();
		spi.setClockActiveHigh();
		spi.setClockRate(clockRate);
		
		dataReceived = new byte[1];
		for(int i = 0; i < dataReceived.length; i++) {
			dataReceived[i] = 0;
		}
	}
	
	public void init() {
		int motionRegister = readRegister(MOTION_REGISTER);
		SmartDashboard.putNumber("Motion Register", motionRegister);
		if((motionRegister & 0x80) != 0) {
			readRegister(DY_REGISTER);
			readRegister(DX_REGISTER);
		}
		pos.setX(0);
		pos.setY(0);
	}
	
	public void update() {
		int rawDx = 0;
		int rawDy = 0;
		int motionRegister = readRegister(MOTION_REGISTER);
		if((motionRegister & 0x80) != 0) {
			rawDy = readRegister(DY_REGISTER);
			rawDx = -readRegister(DX_REGISTER);
		}
		double rad = Math.toRadians(gyro.getAngle());
		double dx = rawDx / ticksPerFootX;
		double dy = rawDy / ticksPerFootY;
		pos = pos.add(new Vector2d(dx, dy).rotate(rad));
	}
	
	private int readRegister(byte register) {
		this.register[0] = register;
		spi.write(this.register, 1); // Writes the register to be read
		spi.read(true, dataReceived, 1); // Reads the garbage
		spi.read(false, dataReceived, 1); // Reads the real register value
		return dataReceived[0];
	}
}

