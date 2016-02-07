package de.googleDistance.coordinates;

public class CartasianCoordinates {

	private double m_xValue;
	private double m_yValue;

	public CartasianCoordinates(double xValue, double yValue) {
		m_xValue = xValue;
		m_yValue = yValue;
	}

	public double getXValue() {
		return m_xValue;
	}

	public void setXValue(double xValue) {
		this.m_xValue = xValue;
	}

	public double getYValue() {
		return m_yValue;
	}

	public void setYValue(double yValue) {
		this.m_yValue = yValue;
	}

	public String toString() {
		return getXValue() + ", " + getYValue();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CartasianCoordinates)) {
			return false;
		}
		return m_xValue == ((CartasianCoordinates) obj).getXValue()
				&& m_yValue == ((CartasianCoordinates) obj).getYValue();
	}
}
