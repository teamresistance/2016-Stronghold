package org.teamresistance.mathf;

public class Vector4f {
	private float x;
	private float y;
	private float z;
	private float w;

	public Vector4f(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector4f(Vector3f xyz, float w) {
		this.x = xyz.getX();
		this.y = xyz.getY();
		this.z = xyz.getZ();
		this.w = w;
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

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}

	public Vector2f getXY() {
		return new Vector2f(getX(), getY());
	}

	public Vector2f getXZ() {
		return new Vector2f(getX(), getZ());
	}

	public Vector2f getXW() {
		return new Vector2f(getX(), getW());
	}

	public Vector2f getYZ() {
		return new Vector2f(getY(), getZ());
	}

	public Vector2f getYW() {
		return new Vector2f(getY(), getW());
	}

	public Vector2f getZW() {
		return new Vector2f(getZ(), getW());
	}

	public Vector2f getYX() {
		return new Vector2f(getY(), getX());
	}

	public Vector2f getZX() {
		return new Vector2f(getZ(), getX());
	}

	public Vector2f getWX() {
		return new Vector2f(getW(), getX());
	}

	public Vector2f getZY() {
		return new Vector2f(getZ(), getY());
	}

	public Vector2f getWY() {
		return new Vector2f(getW(), getY());
	}

	public Vector2f getWZ() {
		return new Vector2f(getW(), getZ());
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

	public Vector3f getXYW() {
		return new Vector3f(getX(), getY(), getW());
	}

	public Vector3f getYXW() {
		return new Vector3f(getY(), getX(), getW());
	}

	public Vector3f getYWX() {
		return new Vector3f(getY(), getW(), getX());
	}

	public Vector3f getWYX() {
		return new Vector3f(getW(), getY(), getX());
	}

	public Vector3f getXWY() {
		return new Vector3f(getX(), getW(), getY());
	}

	public Vector3f getZYW() {
		return new Vector3f(getZ(), getY(), getW());
	}

	public Vector3f getYZW() {
		return new Vector3f(getY(), getZ(), getW());
	}

	public Vector3f getYWZ() {
		return new Vector3f(getY(), getW(), getZ());
	}

	public Vector3f getWYZ() {
		return new Vector3f(getW(), getY(), getZ());
	}

	public Vector3f getZWY() {
		return new Vector3f(getZ(), getW(), getY());
	}

	public Vector3f getXZW() {
		return new Vector3f(getX(), getZ(), getW());
	}

	public Vector3f getZXW() {
		return new Vector3f(getZ(), getX(), getW());
	}

	public Vector3f getZWX() {
		return new Vector3f(getZ(), getW(), getX());
	}

	public Vector3f getWZX() {
		return new Vector3f(getW(), getZ(), getX());
	}

	public Vector3f getXWZ() {
		return new Vector3f(getX(), getW(), getZ());
	}

	public Vector4f getXYZW() {
		return new Vector4f(getX(), getY(), getZ(), getW());
	}

	public Vector4f getYXZW() {
		return new Vector4f(getY(), getX(), getZ(), getW());
	}

	public Vector4f getYZXW() {
		return new Vector4f(getY(), getZ(), getX(), getW());
	}

	public Vector4f getYZWX() {
		return new Vector4f(getY(), getZ(), getW(), getX());
	}

	public Vector4f getZYWX() {
		return new Vector4f(getZ(), getY(), getW(), getX());
	}

	public Vector4f getZWYX() {
		return new Vector4f(getZ(), getW(), getY(), getX());
	}

	public Vector4f getZWXY() {
		return new Vector4f(getZ(), getW(), getX(), getY());
	}

	public Vector4f getWZXY() {
		return new Vector4f(getW(), getZ(), getX(), getY());
	}

	public Vector4f getWXZY() {
		return new Vector4f(getW(), getX(), getZ(), getY());
	}

	public Vector4f getWXYZ() {
		return new Vector4f(getW(), getX(), getY(), getZ());
	}

	public Vector4f getXWYZ() {
		return new Vector4f(getX(), getW(), getY(), getZ());
	}

	public Vector4f getXYWZ() {
		return new Vector4f(getX(), getY(), getW(), getZ());
	}
}
