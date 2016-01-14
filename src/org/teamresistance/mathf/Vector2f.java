package org.teamresistance.mathf;

public class Vector2f {
	private float x;
	private float y;

	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float length() {
		return (float) Math.sqrt(x * x + y * y);
	}

	public float dot(Vector2f r) {
		return x * r.getX() + y * r.getY();
	}
	
	public float dot(float x, float y) {
		return this.x * x + this.y * y;
	}

	public Vector2f normalized() {
		float length = length();

		return new Vector2f(x / length, y / length);
	}

	public Vector2f rotate(float angle) {
		double rad = Math.toRadians(angle);
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);

		return new Vector2f((float) (x * cos - y * sin), (float) (x * sin + y
				* cos));
	}

	public Vector2f add(Vector2f r) {
		return new Vector2f(x + r.getX(), y + r.getY());
	}
	
	public Vector2f add(float x, float y) {
		return new Vector2f(this.x + x, this.y + y);
	}

	public Vector2f add(float r) {
		return new Vector2f(x + r, y + r);
	}

	public Vector2f sub(Vector2f r) {
		return new Vector2f(x - r.getX(), y - r.getY());
	}
	
	public Vector2f sub(float x, float y) {
		return new Vector2f(this.x - x, this.y - y);
	}

	public Vector2f sub(float r) {
		return new Vector2f(x - r, y - r);
	}

	public Vector2f mul(Vector2f r) {
		return new Vector2f(x * r.getX(), y * r.getY());
	}
	
	public Vector2f mul(float x, float y) {
		return new Vector2f(this.x * x, this.y * y);
	}

	public Vector2f mul(float r) {
		return new Vector2f(x * r, y * r);
	}

	public Vector2f div(Vector2f r) {
		return new Vector2f(x / r.getX(), y / r.getY());
	}
	
	public Vector2f div(float x, float y) {
		return new Vector2f(this.x / x, this.y / y);
	}

	public Vector2f div(float r) {
		return new Vector2f(x / r, y / r);
	}

	public Vector2f abs() {
		return new Vector2f(Math.abs(x), Math.abs(y));
	}

	public String toString() {
		return x + "," + y;
	}

	public void fromString(String sVec) {
		String[] coords = sVec.split(",");
		if (coords.length != 2) {
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
	}

	public boolean equals(Vector2f v) {
		return (v.getX() == this.x && v.getY() == this.y);
	}
	
	public boolean equals(float x, float y) {
		return (x == this.x && y == this.y);
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

	public Vector2f getXY() {
		return new Vector2f(getX(), getY());
	}

	public Vector2f getYX() {
		return new Vector2f(getY(), getX());
	}

}
