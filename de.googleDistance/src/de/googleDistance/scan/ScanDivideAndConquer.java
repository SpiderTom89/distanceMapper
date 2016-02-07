package de.googleDistance.scan;

import de.googleDistance.coordinates.CartasianCoordinates;

public class ScanDivideAndConquer {

	private CartasianCoordinates m_origin;
	private double m_west;
	private double m_north;
	private double m_east;
	private double m_south;
	private int m_divideStep;

	public ScanDivideAndConquer(CartasianCoordinates origin, double west, double north, double east, double south,
			int divideStep) {
		m_origin = origin;
		m_west = west;
		m_north = north;
		m_east = east;
		m_south = south;
		m_divideStep = divideStep;
	}

	public void startScan() {
		double xWidth = m_east - m_west;
		double yWidth = m_north - m_south;
		double smallerWidth = Math.min(xWidth, yWidth);

		for (int i = 1; i <= m_divideStep; i++) {
			int numberOfSteps = (int) Math.pow(2, i);
			double currentStepLength = smallerWidth / numberOfSteps;
			int xSteps = (int) (xWidth / currentStepLength);
			int ySteps = (int) (yWidth / currentStepLength);
			for (int j = 0; j <= xSteps; j++) {
				for (int k = 0; k <= ySteps; k++) {
					// Beim ersten Durchlauf berechne alle Punkte, danach
					// überspringe den Punkt, falls beide Richtungen durch 2
					// teilbar ist
					if (i != 1 && j % 2 == 0 && k % 2 == 0) {
						continue;
					}
					double currentX = m_west + j * currentStepLength;
					double currentY = m_south + k * currentStepLength;
					CartasianCoordinates current = new CartasianCoordinates(currentX, currentY);
					long duration = getDuration(current);
					insertDuration(current, duration, i);
				}
			}
		}
	}

	protected long getDuration(CartasianCoordinates destination) {
		return 100;
	}

	protected void insertDuration(CartasianCoordinates destination, long duration, int accuracyStep) {

	}
}
