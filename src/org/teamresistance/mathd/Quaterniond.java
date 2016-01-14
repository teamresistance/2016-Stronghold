package org.teamresistance.mathd;

import org.teamresistance.mathi.Vector3i;

public class Quaterniond {
	private double x;
	private double y;
	private double z;
	private double w;

	public Quaterniond(double x, double y, double z, double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public double length() {
		return Math.sqrt(x * x + y * y + z * z + w * w);
	}

	public Quaterniond normalize() {
		double length = length();

		return new Quaterniond(x / length, y / length, z / length, w / length);
	}

	public Quaterniond conjugate() {
		return new Quaterniond(-x, -y, -z, w);
	}

	public Quaterniond mul(Quaterniond r) {
		double w_ = w * r.getW() - x * r.getX() - y * r.getY() - z * r.getZ();
		double x_ = x * r.getW() + w * r.getX() + y * r.getZ() - z * r.getY();
		double y_ = y * r.getW() + w * r.getY() + z * r.getX() - x * r.getZ();
		double z_ = z * r.getW() + w * r.getZ() + x * r.getY() - y * r.getX();

		return new Quaterniond(x_, y_, z_, w_);
	}

	public Quaterniond mul(Vector3d r) {
		double w_ = -x * r.getX() - y * r.getY() - z * r.getZ();
		double x_ = w * r.getX() + y * r.getZ() - z * r.getY();
		double y_ = w * r.getY() + z * r.getX() - x * r.getZ();
		double z_ = w * r.getZ() + x * r.getY() - y * r.getX();

		return new Quaterniond(x_, y_, z_, w_);
	}

	public Quaterniond mul(Vector3i r) {
		double w_ = -x * r.getX() - y * r.getY() - z * r.getZ();
		double x_ = w * r.getX() + y * r.getZ() - z * r.getY();
		double y_ = w * r.getY() + z * r.getX() - x * r.getZ();
		double z_ = w * r.getZ() + x * r.getY() - y * r.getX();

		return new Quaterniond(x_, y_, z_, w_);
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

	public double getW() {
		return w;
	}

	public void setW(double w) {
		this.w = w;
	}
}
