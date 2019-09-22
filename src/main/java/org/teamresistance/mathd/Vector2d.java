package org.teamresistance.mathd;

public class Vector2d {
	private double x;
	private double y;

	public Vector2d(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double length() {
		return (double) Math.sqrt(x * x + y * y);
	}

	public double dot(Vector2d r) {
		return x * r.getX() + y * r.getY();
	}

	public Vector2d normalized() {
		double length = length();

		return new Vector2d(x / length, y / length);
	}

	public Vector2d rotate(double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);

		return new Vector2d((double) (x * cos - y * sin), (double) (x * sin + y * cos));
	}

	public Vector2d add(Vector2d r) {
		return new Vector2d(x + r.getX(), y + r.getY());
	}

	public Vector2d add(double x, double y) {
		return new Vector2d(this.x + x, this.y + y);
	}
	
	public Vector2d add(double r) {
		return new Vector2d(x + r, y + r);
	}

	public Vector2d sub(Vector2d r) {
		return new Vector2d(x - r.getX(), y - r.getY());
	}

	public Vector2d sub(double x, double y) {
		return new Vector2d(this.x - x, this.y - y);
	}
	
	public Vector2d sub(double r) {
		return new Vector2d(x - r, y - r);
	}

	public Vector2d mul(Vector2d r) {
		return new Vector2d(x * r.getX(), y * r.getY());
	}

	public Vector2d mul(double x, double y) {
		return new Vector2d(this.x * x, this.y * y);
	}
	
	public Vector2d mul(double r) {
		return new Vector2d(x * r, y * r);
	}

	public Vector2d div(Vector2d r) {
		return new Vector2d(x / r.getX(), y / r.getY());
	}
	
	public Vector2d div(double x, double y) {
		return new Vector2d(this.x / x, this.y / y);
	}

	public Vector2d div(double r) {
		return new Vector2d(x / r, y / r);
	}

	public Vector2d abs() {
		return new Vector2d(Math.abs(x), Math.abs(y));
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
			setX(Double.parseDouble(coords[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			setY(Double.parseDouble(coords[1]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean equals(Vector2d v) {
		return (v.getX() == this.x && v.getY() == this.y);
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

	public Vector2d getXY() {
		return new Vector2d(getX(), getY());
	}

	public Vector2d getYX() {
		return new Vector2d(getY(), getX());
	}

}
