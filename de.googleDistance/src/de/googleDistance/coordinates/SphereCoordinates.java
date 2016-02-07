package de.googleDistance.coordinates;

// TODO lng und lat noch vertauschen
public class SphereCoordinates {

	private double m_lng;
	private double m_lat;

	public SphereCoordinates(double lng, double lat) {
		m_lng = lng;
		m_lat = lat;
	}

	public double getLng() {
		return m_lng;
	}

	public void setLng(double lng) {
		this.m_lng = lng;
	}

	public double getLat() {
		return m_lat;
	}

	public void setLat(double lat) {
		this.m_lat = lat;
	}

	@Override
	public String toString() {
		return "(" + getLng() + "," + getLat() + ")";
	}

	public boolean isLeftOf(SphereCoordinates compCoord) {
		return m_lat <= compCoord.getLat();
	}

	public boolean isBelowOf(SphereCoordinates compCoord) {
		return m_lng <= compCoord.getLng();
	}

	public boolean isAboveOf(SphereCoordinates compCoord) {
		return m_lng >= compCoord.getLng();
	}
}
