package de.googleDistance.db;

import de.googleDistance.coordinates.CartasianCoordinates;

public class TaskBean {

	private long m_taskNr;
	private CartasianCoordinates m_origin;
	private double m_west;
	private double m_north;
	private double m_east;
	private double m_south;
	private long m_accuracy;
	private long m_lastAccuracy;

	public TaskBean(long taskNr, CartasianCoordinates origin, double west, double north, double east, double south,
			long accuracy, long lastAccuracy) {
		m_taskNr = taskNr;
		m_origin = origin;
		m_west = west;
		m_north = north;
		m_east = east;
		m_south = south;
		m_accuracy = accuracy;
		m_lastAccuracy = lastAccuracy;
	}

	public long getTaskNr() {
		return m_taskNr;
	}

	public CartasianCoordinates getOrigin() {
		return m_origin;
	}

	public double getWest() {
		return m_west;
	}

	public double getNorth() {
		return m_north;
	}

	public double getEast() {
		return m_east;
	}

	public double getSouth() {
		return m_south;
	}

	public long getAccuracy() {
		return m_accuracy;
	}

	public long getLastAccuracy() {
		return m_lastAccuracy;
	}
}
