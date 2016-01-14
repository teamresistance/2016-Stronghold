package org.teamresistance.mathf;

import org.teamresistance.mathi.Vector2i;
import org.teamresistance.mathi.Vector3i;

public class Vector3f {
	private float x;
	private float y;
	private float z;

	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3f(Vector2f xy, float z) {
		this.x = xy.getX();
		this.y = xy.getY();
		this.z = z;
	}
	
	public Vector3f(Vector2i xy, float z) {
		this.x = xy.getX();
		this.y = xy.getY();
		this.z = z;
	}

	public Vector3f(float x, Vector2f yz) {
		this.x = x;
		this.y = yz.getX();
		this.z = yz.getY();
	}
	
	public Vector3f(float x, Vector2i yz) {
		this.x = x;
		this.y = yz.getX();
		this.z = yz.getY();
	}

	public float length() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	public float lengthSqrd() {
		return x * x + y * y + z * z;
	}

	public float dot(Vector3f r) {
		return x * r.getX() + y * r.getY() + z * r.getZ();
	}

	public Vector3f cross(Vector3f r) {
		float x_ = y * r.getZ() - z * r.getY();
		float y_ = z * r.getX() - x * r.getZ();
		float z_ = x * r.getY() - y * r.getX();

		return new Vector3f(x_, y_, z_);
	}

	public Vector3f normalized() {
		float length = length();

		return new Vector3f(x / length, y / length, z / length);
	}

	public Vector3f rotate(float angle, Vector3f axis) {
		float sinHalfAngle = (float) Math.sin(Math.toRadians(angle / 2));
		float cosHalfAngle = (float) Math.cos(Math.toRadians(angle / 2));

		float rX = axis.getX() * sinHalfAngle;
		float rY = axis.getY() * sinHalfAngle;
		float rZ = axis.getZ() * sinHalfAngle;
		float rW = cosHalfAngle;

		Quaternionf rotation = new Quaternionf(rX, rY, rZ, rW);
		Quaternionf conjugate = rotation.conjugate();

		Quaternionf w = rotation.mul(this).mul(conjugate);

		return new Vector3f(w.getX(), w.getY(), w.getZ());
	}

	public Vector3f add(Vector3f r) {
		return new Vector3f(x + r.getX(), y + r.getY(), z + r.getZ());
	}
	
	public Vector3f add(float x, float y, float z) {
		return new Vector3f(this.x + x, this.y + y, this.z + z);
	}

	public Vector3f add(float r) {
		return new Vector3f(x + r, y + r, z + r);
	}

	public Vector3f sub(Vector3f r) {
		return new Vector3f(x - r.getX(), y - r.getY(), z - r.getZ());
	}
	
	public Vector3f sub(float x, float y, float z) {
		return new Vector3f(this.x - x, this.y - y, this.z - z);
	}

	public Vector3f sub(float r) {
		return new Vector3f(x - r, y - r, z - r);
	}

	public Vector3f mul(Vector3f r) {
		return new Vector3f(x * r.getX(), y * r.getY(), z * r.getZ());
	}
	
	public Vector3f mul(float x, float y, float z) {
		return new Vector3f(this.x * x, this.y * y, this.z * z);
	}

	public Vector3f mul(Vector3i r) {
		return new Vector3f(x * r.getX(), y * r.getY(), z * r.getZ());
	}

	public Vector3f mul(float r) {
		return new Vector3f(x * r, y * r, z * r);
	}

	public Vector3f div(Vector3f r) {
		return new Vector3f(x / r.getX(), y / r.getY(), z / r.getZ());
	}
	
	public Vector3f div(float x, float y, float z) {
		return new Vector3f(this.x / x, this.y / y, this.z / z);
	}

	public Vector3f div(float r) {
		return new Vector3f(x / r, y / r, z / r);
	}

	public Vector3f abs() {
		return new Vector3f(Math.abs(x), Math.abs(y), Math.abs(z));
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
			setX(Float.parseFloat(coords[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			setY(Float.parseFloat(coords[1]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			setZ(Float.parseFloat(coords[2]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public Vector2f getXY() {
		return new Vector2f(getX(), getY());
	}

	public Vector2f getXZ() {
		return new Vector2f(getX(), getZ());
	}

	public Vector2f getYZ() {
		return new Vector2f(getY(), getZ());
	}

	public Vector2f getYX() {
		return new Vector2f(getY(), getX());
	}

	public Vector2f getZX() {
		return new Vector2f(getZ(), getX());
	}

	public Vector2f getZY() {
		return new Vector2f(getZ(), getY());
	}

	public Vector3f getXYZ() {
		return new Vector3f(getX(), getY(), getZ());
	}

	public Vector3f getYXZ() {
		return new Vector3f(getY(), getX(), getZ());
	}

	public Vector3f getYZX() {
		return new Vector3f(getY(), getZ(), getX());
	}

	public Vector3f getZYX() {
		return new Vector3f(getZ(), getY(), getX());
	}

	public Vector3f getZXY() {
		return new Vector3f(getZ(), getX(), getY());
	}

	public Vector3f getXZY() {
		return new Vector3f(getX(), getZ(), getY());
	}

	public Vector3i getXYZi() {
		return new Vector3i(Math.round(getX()), Math.round(getY()),
				Math.round(getZ()));
	}

}
