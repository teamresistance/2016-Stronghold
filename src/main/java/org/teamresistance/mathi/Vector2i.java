package org.teamresistance.mathi;

public class Vector2i {
	private int x;
	private int y;

	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int length() {
		return (int) Math.sqrt(x * x + y * y);
	}

	public int dot(Vector2i r) {
		return x * r.getX() + y * r.getY();
	}

	public Vector2i normalized() {
		int length = length();

		return new Vector2i(x / length, y / length);
	}

	public Vector2i rotate(int angle) {
		double rad = Math.toRadians(angle);
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);

		return new Vector2i((int) (x * cos - y * sin),
				(int) (x * sin + y * cos));
	}

	public Vector2i add(Vector2i r) {
		return new Vector2i(x + r.getX(), y + r.getY());
	}

	public Vector2i add(int r) {
		return new Vector2i(x + r, y + r);
	}

	public Vector2i sub(Vector2i r) {
		return new Vector2i(x - r.getX(), y - r.getY());
	}

	public Vector2i sub(int r) {
		return new Vector2i(x - r, y - r);
	}

	public Vector2i mul(Vector2i r) {
		return new Vector2i(x * r.getX(), y * r.getY());
	}

	public Vector2i mul(int r) {
		return new Vector2i(x * r, y * r);
	}

	public Vector2i div(Vector2i r) {
		return new Vector2i(x / r.getX(), y / r.getY());
	}

	public Vector2i div(int r) {
		return new Vector2i(x / r, y / r);
	}

	public Vector2i abs() {
		return new Vector2i(Math.abs(x), Math.abs(y));
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
			setX(Integer.parseInt(coords[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			setY(Integer.parseInt(coords[1]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		long result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Vector2i other = (Vector2i) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
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

	public Vector2i getXY() {
		return new Vector2i(getX(), getY());
	}

	public Vector2i getYX() {
		return new Vector2i(getY(), getX());
	}

}
