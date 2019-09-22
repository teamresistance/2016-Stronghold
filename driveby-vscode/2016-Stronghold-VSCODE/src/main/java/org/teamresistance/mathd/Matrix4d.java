package org.teamresistance.mathd;

public class Matrix4d {
	private double[][] m;
	
	public Matrix4d() {
		m = new double[4][4];
	}

	public Matrix4d initIdentity() {
		m[0][0] = 1;	m[0][1] = 0;	m[0][2] = 0;	m[0][3] = 0;
		m[1][0] = 0;	m[1][1] = 1;	m[1][2] = 0;	m[1][3] = 0;
		m[2][0] = 0;	m[2][1] = 0;	m[2][2] = 1;	m[2][3] = 0;
		m[3][0] = 0;	m[3][1] = 0;	m[3][2] = 0;	m[3][3] = 1;
		
		return this;
	}
	
	public Matrix4d initTranslation(double x, double y, double z) {
		m[0][0] = 1;	m[0][1] = 0;	m[0][2] = 0;	m[0][3] = x;
		m[1][0] = 0;	m[1][1] = 1;	m[1][2] = 0;	m[1][3] = y;
		m[2][0] = 0;	m[2][1] = 0;	m[2][2] = 1;	m[2][3] = z;
		m[3][0] = 0;	m[3][1] = 0;	m[3][2] = 0;	m[3][3] = 1;
		
		return this;
	}
	
	public Matrix4d initRotation(double x, double y, double z) {
		Matrix4d rx = new Matrix4d();
		Matrix4d ry = new Matrix4d();
		Matrix4d rz = new Matrix4d();
		
		x = Math.toRadians(x);
		y = Math.toRadians(y);
		z = Math.toRadians(z);
		
		rz.m[0][0] = Math.cos(z);		rz.m[0][1] = -Math.sin(z);		rz.m[0][2] = 0;					rz.m[0][3] = 0;
		rz.m[1][0] = Math.sin(z);		rz.m[1][1] = Math.cos(z);		rz.m[1][2] = 0;					rz.m[1][3] = 0;
		rz.m[2][0] = 0;					rz.m[2][1] = 0;					rz.m[2][2] = 1;					rz.m[2][3] = 0;
		rz.m[3][0] = 0;					rz.m[3][1] = 0;					rz.m[3][2] = 0;					rz.m[3][3] = 1;
		
		rx.m[0][0] = 1;					rx.m[0][1] = 0;					rx.m[0][2] = 0;					rx.m[0][3] = 0;
		rx.m[1][0] = 0;					rx.m[1][1] = Math.cos(x);		rx.m[1][2] = -Math.sin(x);		rx.m[1][3] = 0;
		rx.m[2][0] = 0;					rx.m[2][1] = Math.sin(x);		rx.m[2][2] = Math.cos(x);		rx.m[2][3] = 0;
		rx.m[3][0] = 0;					rx.m[3][1] = 0;					rx.m[3][2] = 0;					rx.m[3][3] = 1;
		
		ry.m[0][0] = Math.cos(y);		ry.m[0][1] = 0;					ry.m[0][2] = -Math.sin(y);		ry.m[0][3] = 0;
		ry.m[1][0] = 0;					ry.m[1][1] = 1;					ry.m[1][2] = 0;					ry.m[1][3] = 0;
		ry.m[2][0] = Math.sin(y);		ry.m[2][1] = 0;					ry.m[2][2] = Math.cos(y);		ry.m[2][3] = 0;
		ry.m[3][0] = 0;					ry.m[3][1] = 0;					ry.m[3][2] = 0;					ry.m[3][3] = 1;
		
		m = rz.mul(ry.mul(rx)).getM();
		
		return this;
	}
	
	public Matrix4d initScale(double x, double y, double z) {
		m[0][0] = x;	m[0][1] = 0;	m[0][2] = 0;	m[0][3] = 0;
		m[1][0] = 0;	m[1][1] = y;	m[1][2] = 0;	m[1][3] = 0;
		m[2][0] = 0;	m[2][1] = 0;	m[2][2] = z;	m[2][3] = 0;
		m[3][0] = 0;	m[3][1] = 0;	m[3][2] = 0;	m[3][3] = 1;
		
		return this;
	}
	
	public Matrix4d initProjection(double fov, double width, double height, double zNear, double zFar) {
		double ar = width/height;
		double tanHalfFOV = Math.tan(Math.toRadians(fov / 2));
		double zRange = zNear - zFar;
		
		m[0][0] = 1.0f / (tanHalfFOV * ar);	m[0][1] = 0;					m[0][2] = 0;	m[0][3] = 0;
		m[1][0] = 0;						m[1][1] = 1.0f / tanHalfFOV;	m[1][2] = 0;	m[1][3] = 0;
		m[2][0] = 0;						m[2][1] = 0;					m[2][2] = (-zNear -zFar)/zRange;	m[2][3] = 2 * zFar * zNear / zRange;
		m[3][0] = 0;						m[3][1] = 0;					m[3][2] = 1;	m[3][3] = 0;
		
		
		return this;
	}
	
	public Matrix4d initOrthographic(double left, double right, double bottom, double top, double near, double far) {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				m[i][j] = 0;
			}
		}
		m[0][0] = 2.0f / (left - right);
		m[1][1] = 2.0f / (top - bottom);
		m[2][2] = -2.0f / (far - near);
		
		m[0][3] = -(right + left) / (right - left);
		m[1][3] = -(top + bottom) / (top - bottom);
		m[2][3] = -(far + near) / (far - near);
		m[3][3] = 1.0f;
		return this;
	}
	
	public Matrix4d initCamera(Vector3d forward, Vector3d up) {
		Vector3d f = forward.normalized();
		
		Vector3d r = up.normalized();
		r = r.cross(f);
		
		Vector3d u = f.cross(r);
		
		m[0][0] = r.getX();	m[0][1] = r.getY();	m[0][2] = r.getZ();	m[0][3] = 0;
		m[1][0] = u.getX();	m[1][1] = u.getY();	m[1][2] = u.getZ();	m[1][3] = 0;
		m[2][0] = f.getX();	m[2][1] = f.getY();	m[2][2] = f.getZ();	m[2][3] = 0;
		m[3][0] = 0;		m[3][1] = 0;		m[3][2] = 0;		m[3][3] = 1;
		
		return this;
	}
	
	public Matrix4d mul(Matrix4d r) {
		Matrix4d res = new Matrix4d();
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				res.set(i, j, m[i][0] * r.get(0, j) +
							  m[i][1] * r.get(1, j) +
							  m[i][2] * r.get(2, j) +
							  m[i][3] * r.get(3, j));
			}
		}
		
		return res;
	}
	
	public double[][] getM() {
		double[][] res = new double[4][4];
		
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				res[i][j] = m[i][j];
		
		return res;
	}
	
	public double get(int x, int y) {
		return m[x][y];
	}

	public void setM(double[][] m) {
		this.m = m;
	}
	
	public void set(int x, int y, double value) {
		m[x][y] = value;
	}
}
