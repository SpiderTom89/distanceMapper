package de.googleDistance.db;

import de.googleDistance.coordinates.CartasianCoordinates;

public class DistanceMatrixBean {

	private CartasianCoordinates m_start;
	private CartasianCoordinates m_destination;
	private long m_duration;

	public DistanceMatrixBean(CartasianCoordinates start, CartasianCoordinates destination, long duration) {
		m_start = start;
		m_destination = destination;
		m_duration = duration;
	}

	public CartasianCoordinates getStart() {
		return m_start;
	}

	public CartasianCoordinates getDestination() {
		return m_destination;
	}

	public long getDuration() {
		return m_duration;
	}
}
