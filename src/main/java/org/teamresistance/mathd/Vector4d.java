package org.teamresistance.mathd;

public class Vector4d {
	private double x;
	private double y;
	private double z;
	private double w;

	public Vector4d(double x, double y, double z, double w) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector4d(Vector3d xyz, double w) {
		this.x = xyz.getX();
		this.y = xyz.getY();
		this.z = xyz.getZ();
		this.w = w;
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

	public Vector2d getXY() {
		return new Vector2d(getX(), getY());
	}

	public Vector2d getXZ() {
		return new Vector2d(getX(), getZ());
	}

	public Vector2d getXW() {
		return new Vector2d(getX(), getW());
	}

	public Vector2d getYZ() {
		return new Vector2d(getY(), getZ());
	}

	public Vector2d getYW() {
		return new Vector2d(getY(), getW());
	}

	public Vector2d getZW() {
		return new Vector2d(getZ(), getW());
	}

	public Vector2d getYX() {
		return new Vector2d(getY(), getX());
	}

	public Vector2d getZX() {
		return new Vector2d(getZ(), getX());
	}

	public Vector2d getWX() {
		return new Vector2d(getW(), getX());
	}

	public Vector2d getZY() {
		return new Vector2d(getZ(), getY());
	}

	public Vector2d getWY() {
		return new Vector2d(getW(), getY());
	}

	public Vector2d getWZ() {
		return new Vector2d(getW(), getZ());
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

	public Vector3d getXYW() {
		return new Vector3d(getX(), getY(), getW());
	}

	public Vector3d getYXW() {
		return new Vector3d(getY(), getX(), getW());
	}

	public Vector3d getYWX() {
		return new Vector3d(getY(), getW(), getX());
	}

	public Vector3d getWYX() {
		return new Vector3d(getW(), getY(), getX());
	}

	public Vector3d getXWY() {
		return new Vector3d(getX(), getW(), getY());
	}

	public Vector3d getZYW() {
		return new Vector3d(getZ(), getY(), getW());
	}

	public Vector3d getYZW() {
		return new Vector3d(getY(), getZ(), getW());
	}

	public Vector3d getYWZ() {
		return new Vector3d(getY(), getW(), getZ());
	}

	public Vector3d getWYZ() {
		return new Vector3d(getW(), getY(), getZ());
	}

	public Vector3d getZWY() {
		return new Vector3d(getZ(), getW(), getY());
	}

	public Vector3d getXZW() {
		return new Vector3d(getX(), getZ(), getW());
	}

	public Vector3d getZXW() {
		return new Vector3d(getZ(), getX(), getW());
	}

	public Vector3d getZWX() {
		return new Vector3d(getZ(), getW(), getX());
	}

	public Vector3d getWZX() {
		return new Vector3d(getW(), getZ(), getX());
	}

	public Vector3d getXWZ() {
		return new Vector3d(getX(), getW(), getZ());
	}

	public Vector4d getXYZW() {
		return new Vector4d(getX(), getY(), getZ(), getW());
	}

	public Vector4d getYXZW() {
		return new Vector4d(getY(), getX(), getZ(), getW());
	}

	public Vector4d getYZXW() {
		return new Vector4d(getY(), getZ(), getX(), getW());
	}

	public Vector4d getYZWX() {
		return new Vector4d(getY(), getZ(), getW(), getX());
	}

	public Vector4d getZYWX() {
		return new Vector4d(getZ(), getY(), getW(), getX());
	}

	public Vector4d getZWYX() {
		return new Vector4d(getZ(), getW(), getY(), getX());
	}

	public Vector4d getZWXY() {
		return new Vector4d(getZ(), getW(), getX(), getY());
	}

	public Vector4d getWZXY() {
		return new Vector4d(getW(), getZ(), getX(), getY());
	}

	public Vector4d getWXZY() {
		return new Vector4d(getW(), getX(), getZ(), getY());
	}

	public Vector4d getWXYZ() {
		return new Vector4d(getW(), getX(), getY(), getZ());
	}

	public Vector4d getXWYZ() {
		return new Vector4d(getX(), getW(), getY(), getZ());
	}

	public Vector4d getXYWZ() {
		return new Vector4d(getX(), getY(), getW(), getZ());
	}
}
