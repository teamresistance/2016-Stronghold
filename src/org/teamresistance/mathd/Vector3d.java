package org.teamresistance.mathd;

import org.teamresistance.mathi.Vector2i;
import org.teamresistance.mathi.Vector3i;

public class Vector3d {
	private double x;
	private double y;
	private double z;

	public Vector3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3d(Vector2d xy, double z) {
		this.x = xy.getX();
		this.y = xy.getY();
		this.z = z;
	}
	
	public Vector3d(Vector2i xy, double z) {
		this.x = xy.getX();
		this.y = xy.getY();
		this.z = z;
	}

	public Vector3d(double x, Vector2d yz) {
		this.x = x;
		this.y = yz.getX();
		this.z = yz.getY();
	}
	
	public Vector3d(double x, Vector2i yz) {
		this.x = x;
		this.y = yz.getX();
		this.z = yz.getY();
	}

	public double length() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	public double lengthSqrd() {
		return x * x + y * y + z * z;
	}

	public double dot(Vector3d r) {
		return x * r.getX() + y * r.getY() + z * r.getZ();
	}

	public Vector3d cross(Vector3d r) {
		double x_ = y * r.getZ() - z * r.getY();
		double y_ = z * r.getX() - x * r.getZ();
		double z_ = x * r.getY() - y * r.getX();

		return new Vector3d(x_, y_, z_);
	}

	public Vector3d normalized() {
		double length = length();

		return new Vector3d(x / length, y / length, z / length);
	}

	public Vector3d rotate(double angle, Vector3d axis) {
		double sinHalfAngle = Math.sin(Math.toRadians(angle / 2));
		double cosHalfAngle = Math.cos(Math.toRadians(angle / 2));

		double rX = axis.getX() * sinHalfAngle;
		double rY = axis.getY() * sinHalfAngle;
		double rZ = axis.getZ() * sinHalfAngle;
		double rW = cosHalfAngle;

		Quaterniond rotation = new Quaterniond(rX, rY, rZ, rW);
		Quaterniond conjugate = rotation.conjugate();

		Quaterniond w = rotation.mul(this).mul(conjugate);

		return new Vector3d(w.getX(), w.getY(), w.getZ());
	}

	public Vector3d add(Vector3d r) {
		return new Vector3d(x + r.getX(), y + r.getY(), z + r.getZ());
	}

	public Vector3d add(double x, double y, double z) {
		return new Vector3d(this.x + x, this.y + y, this.z + z);
	}
	
	public Vector3d add(double r) {
		return new Vector3d(x + r, y + r, z + r);
	}

	public Vector3d sub(Vector3d r) {
		return new Vector3d(x - r.getX(), y - r.getY(), z - r.getZ());
	}
	
	public Vector3d sub(double x, double y, double z) {
		return new Vector3d(this.x - x, this.y - y, this.z - z);
	}

	public Vector3d sub(double r) {
		return new Vector3d(x - r, y - r, z - r);
	}

	public Vector3d mul(Vector3d r) {
		return new Vector3d(x * r.getX(), y * r.getY(), z * r.getZ());
	}
	
	public Vector3d mul(double x, double y, double z) {
		return new Vector3d(this.x * x, this.y * y, this.z * z);
	}

	public Vector3d mul(Vector3i r) {
		return new Vector3d(x * r.getX(), y * r.getY(), z * r.getZ());
	}

	public Vector3d mul(double r) {
		return new Vector3d(x * r, y * r, z * r);
	}

	public Vector3d div(Vector3d r) {
		return new Vector3d(x / r.getX(), y / r.getY(), z / r.getZ());
	}
	
	public Vector3d div(double x, double y, double z) {
		return new Vector3d(this.x / x, this.y / y, this.z / z);
	}

	public Vector3d div(double r) {
		return new Vector3d(x / r, y / r, z / r);
	}

	public Vector3d abs() {
		return new Vector3d(Math.abs(x), Math.abs(y), Math.abs(z));
	}

	public String toString() {
		return x + "," + y + "," + z;
	}

	public void fromString(String sVec) {
		String[] coords = sVec.split(",");
		if (coords.length != 3) {
			System.err.println("Invalid vector in form of string!");
			new Exception().printStackTrace();
			System.exit(1);
		}
		try {
			setX(Double.parseDouble(coords[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			setY(Double.parseDouble(coords[1]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			setZ(Double.parseDouble(coords[2]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public Vector2d getXY() {
		return new Vector2d(getX(), getY());
	}

	public Vector2d getXZ() {
		return new Vector2d(getX(), getZ());
	}

	public Vector2d getYZ() {
		return new Vector2d(getY(), getZ());
	}

	public Vector2d getYX() {
		return new Vector2d(getY(), getX());
	}

	public Vector2d getZX() {
		return new Vector2d(getZ(), getX());
	}

	public Vector2d getZY() {
		return new Vector2d(getZ(), getY());
	}

	public Vector3d getXYZ() {
		return new Vector3d(getX(), getY(), getZ());
	}

	public Vector3d getYXZ() {
		return new Vector3d(getY(), getX(), getZ());
	}

	public Vector3d getYZX() {
		return new Vector3d(getY(), getZ(), getX());
	}

	public Vector3d getZYX() {
		return new Vector3d(getZ(), getY(), getX());
	}

	public Vector3d getZXY() {
		return new Vector3d(getZ(), getX(), getY());
	}

	public Vector3d getXZY() {
		return new Vector3d(getX(), getZ(), getY());
	}

	public Vector3i getXYZi() {
		return new Vector3i((int)Math.round(getX()), (int)Math.round(getY()),
				(int)Math.round(getZ()));
	}

}
