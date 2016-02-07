package de.googleDistance.db;

import de.googleDistance.coordinates.CartasianCoordinates;

public class ProgressBean {

	private long m_taskNr;
	private long m_accuracy;
	private CartasianCoordinates m_destination;
	private double m_stepLength;

	public ProgressBean(long taskNr, long accuracy, CartasianCoordinates destination, double stepLength) {
		m_taskNr = taskNr;
		m_accuracy = accuracy;
		m_destination = destination;
		m_stepLength = stepLength;
	}

	public long getTaskNr() {
		return m_taskNr;
	}

	public long getAccuracy() {
		return m_accuracy;
	}

	public CartasianCoordinates getDestination() {
		return m_destination;
	}

	public double getStepLength() {
		return m_stepLength;
	}
}
