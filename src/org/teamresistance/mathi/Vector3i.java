package org.teamresistance.mathi;

import org.teamresistance.mathf.Quaternionf;
import org.teamresistance.mathf.Vector3f;

public class Vector3i {
	private int x;
	private int y;
	private int z;

	public Vector3i(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3i(Vector2i xy, int z) {
		this.x = xy.getX();
		this.y = xy.getY();
		this.z = z;
	}

	public Vector3i(int x, Vector2i yz) {
		this.x = x;
		this.y = yz.getX();
		this.z = yz.getY();
	}

	public int length() {
		return (int) Math.sqrt(x * x + y * y + z * z);
	}

	public int lengthSqrd() {
		return x * x + y * y + z * z;
	}

	public int dot(Vector3i r) {
		return x * r.getX() + y * r.getY() + z * r.getZ();
	}

	public Vector3i cross(Vector3i r) {
		int x_ = y * r.getZ() - z * r.getY();
		int y_ = z * r.getX() - x * r.getZ();
		int z_ = x * r.getY() - y * r.getX();

		return new Vector3i(x_, y_, z_);
	}

	public Vector3i normalized() {
		int length = length();

		return new Vector3i(x / length, y / length, z / length);
	}

	public Vector3i rotate(int angle, Vector3i axis) {
		int sinHalfAngle = (int) Math.sin(Math.toRadians(angle / 2));
		int cosHalfAngle = (int) Math.cos(Math.toRadians(angle / 2));

		int rX = axis.getX() * sinHalfAngle;
		int rY = axis.getY() * sinHalfAngle;
		int rZ = axis.getZ() * sinHalfAngle;
		int rW = cosHalfAngle;

		Quaternionf rotation = new Quaternionf(rX, rY, rZ, rW);
		Quaternionf conjugate = rotation.conjugate();

		Quaternionf w = rotation.mul(this).mul(conjugate);

		return new Vector3i((int) w.getX(), (int) w.getY(), (int) w.getZ());
	}

	public Vector3i add(Vector3i r) {
		return new Vector3i(x + r.getX(), y + r.getY(), z + r.getZ());
	}

	public Vector3i add(int r) {
		return new Vector3i(x + r, y + r, z + r);
	}

	public Vector3i sub(Vector3i r) {
		return new Vector3i(x - r.getX(), y - r.getY(), z - r.getZ());
	}

	public Vector3i sub(Vector3f r) {
		return new Vector3i(x - (int) r.getX(), y - (int) r.getY(), z
				- (int) r.getZ());
	}

	public Vector3i sub(int r) {
		return new Vector3i(x - r, y - r, z - r);
	}

	public Vector3i mul(Vector3i r) {
		return new Vector3i(x * r.getX(), y * r.getY(), z * r.getZ());
	}

	public Vector3i mul(Vector3f r) {
		return new Vector3i(x * (int) r.getX(), y * (int) r.getY(), z
				* (int) r.getZ());
	}

	public Vector3i mul(int r) {
		return new Vector3i(x * r, y * r, z * r);
	}

	public Vector3i div(Vector3i r) {
		return new Vector3i(x / r.getX(), y / r.getY(), z / r.getZ());
	}

	public Vector3i div(int r) {
		return new Vector3i(x / r, y / r, z / r);
	}

	public Vector3i mod(int r) {
		return new Vector3i(getX() % r, getY() % r, getZ() % r);
	}

	public Vector3i mod(Vector3i r) {
		return new Vector3i(getX() % r.getX(), getY() % r.getY(), getZ()
				% r.getZ());
	}

	public Vector3i abs() {
		return new Vector3i(Math.abs(x), Math.abs(y), Math.abs(z));
	}

	public String toString() {
		return x + "," + y + "," + z;
	}

	public Vector3i fromString(String sVec) {
		String[] coords = sVec.split(",");
		if (coords.length != 3) {
			System.err.println("Invalid vector in form of string!");
			new Exception().printStackTrace();
			System.exit(1);
		}
		try {
			setX(Integer.parseInt(coords[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			setY(Integer.parseInt(coords[1]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			setZ(Integer.parseInt(coords[2]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		long result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return (int) (result % 16908799);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector3i other = (Vector3i) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public Vector2i getXY() {
		return new Vector2i(getX(), getY());
	}

	public Vector2i getXZ() {
		return new Vector2i(getX(), getZ());
	}

	public Vector2i getYZ() {
		return new Vector2i(getY(), getZ());
	}

	public Vector2i getYX() {
		return new Vector2i(getY(), getX());
	}

	public Vector2i getZX() {
		return new Vector2i(getZ(), getX());
	}

	public Vector2i getZY() {
		return new Vector2i(getZ(), getY());
	}

	public Vector3i getXYZ() {
		return new Vector3i(getX(), getY(), getZ());
	}

	public Vector3i getYXZ() {
		return new Vector3i(getY(), getX(), getZ());
	}

	public Vector3i getYZX() {
		return new Vector3i(getY(), getZ(), getX());
	}

	public Vector3i getZYX() {
		return new Vector3i(getZ(), getY(), getX());
	}

	public Vector3i getZXY() {
		return new Vector3i(getZ(), getX(), getY());
	}

	public Vector3i getXZY() {
		return new Vector3i(getX(), getZ(), getY());
	}

}
