package de.googleDistance.scan;

import java.io.IOException;
import java.io.StringReader;

import org.xml.sax.InputSource;

import de.googleDistance.csv.WriteCsv;
import de.googleDistance.rest.Coordinates;
import de.googleDistance.rest.DistanceService;
import de.googleDistance.xml.XmlDistanceParser;

public class Scan {

	private Coordinates m_origin;
	private Coordinates m_coordLU;
	private Coordinates m_coordRU;
	private Coordinates m_coordLD;
	private Coordinates m_coordRD;

	private double m_offset;

	private WriteCsv m_data;
	private WriteCsv m_metaData;

	private Double m_minLng = Double.MAX_VALUE;
	private Double m_maxLng = Double.MIN_VALUE;
	private Double m_minLat = Double.MAX_VALUE;
	private Double m_maxLat = Double.MIN_VALUE;
	private Long m_minDuration = Long.MAX_VALUE;
	private Long m_maxDuration = Long.MIN_VALUE;
	private Long m_minDistance = Long.MAX_VALUE;
	private Long m_maxDistance = Long.MIN_VALUE;

	public Scan() {
	}

	public Scan(Coordinates origin, Coordinates coordLU, Coordinates coordRU, Coordinates coordLD, Coordinates coordRD,
			double offset) {
		m_origin = origin;
		m_coordLU = coordLU;
		m_coordRU = coordRU;
		m_coordLD = coordLD;
		m_coordRD = coordRD;

		m_offset = offset;

		m_data = new WriteCsv("C:\\Users\\Tom\\Desktop\\testTruderingBigData.csv");
		m_metaData = new WriteCsv("C:\\Users\\Tom\\Desktop\\testMetaData.csv");
	}

	public void scan() throws IOException, InterruptedException { // offset in
																	// meters
		Coordinates startPoint = m_coordLU;
		Coordinates currentPoint = startPoint;

		Double numberOfSteps = calculateSteps() * 1.0;
		Double currentStep = 0.0;

		while (currentPoint.isAboveOf(m_coordLD)) {
			while (currentPoint.isLeftOf(m_coordRU)) {

				Thread.sleep(50);

				String restDist = null;
				boolean gotResult = false;
				Long numberOfTries = 0L;
				while (!gotResult) {
					numberOfTries++;
					restDist = DistanceService.getDistance(m_origin, currentPoint);
					if (!restDist.contains("You have exceeded your rate-limit for this API")
							&& !restDist.contains("You have exceeded your daily request quota for this API")) {
						gotResult = true;
					} else {
						Thread.sleep(numberOfTries * 1000);
						System.out.println("You have to wait " + numberOfTries);
					}
				}

				// You have exceeded your rate-limit for this API
				InputSource inputSource = new InputSource(new StringReader(restDist));
				XmlDistanceParser xmlDistanceParser = new XmlDistanceParser(inputSource);

				Long duration = xmlDistanceParser.parseDuration();
				Long distance = xmlDistanceParser.parseDistance();

				writeInData(currentPoint, duration, distance);

				long percentDone = (long) ((currentStep / numberOfSteps) * 100);
				printConsole(percentDone, currentPoint, duration, distance, distance / (duration + 0.1));
				currentStep++;

				currentPoint = getShiftedCoordinates(currentPoint, 0, m_offset);
			}

			currentPoint = new Coordinates(currentPoint.getLng(), m_coordLU.getLat());
			currentPoint = getShiftedCoordinates(currentPoint, m_offset, 0);
		}

		System.out.println("done");
		m_data.closeFile();
	}

	public double distFrom(Coordinates coord1, Coordinates coord2) {
		double lat1 = coord1.getLat();
		double lng1 = coord1.getLng();
		double lat2 = coord2.getLat();
		double lng2 = coord2.getLng();

		double earthRadius = 6371000; // meters
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2) * Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double dist = earthRadius * c;

		return dist;
	}

	private Long calculateSteps() {
		double distLng = distFrom(m_coordLU, m_coordRU);
		double distLat = distFrom(m_coordLU, m_coordLD);

		Long stepsLng = (long) (distLng / m_offset);
		Long stepsLat = (long) (distLat / m_offset);

		return stepsLng * stepsLat;
	}

	public Coordinates getShiftedCoordinates(Coordinates origin, double offsetLat, double offsetLng) {
		// Position, decimal degrees
		double lon = origin.getLng();
		double lat = origin.getLat();

		// Earth’s radius, sphere
		double R = 6378137;
		double pi = Math.PI;

		// Coordinate offsets in radians
		double dLon = -offsetLat / (R * Math.cos(pi * lat / 180));
		double dLat = offsetLng / R;

		// OffsetPosition, decimal degrees
		double lonO = lon + dLon * 180 / pi;
		double latO = lat + dLat * 180 / pi;

		return new Coordinates(lonO, latO);
	}

	private void writeInData(Coordinates currentPoint, Long duration, Long distance) throws IOException {
		m_data.addLine(m_origin, currentPoint, duration, distance, distance / (duration + 0.1));

		// double lng = currentPoint.getLng();
		// double lat = currentPoint.getLat();
		//
		// if (lng < m_minLng) {
		// m_minLng = lng;
		// }
		// if (lng > m_maxLng) {
		// m_maxLng = lng;
		// }
		// if (lat < m_minLat) {
		// m_minLat = lat;
		// }
		// if (lat > m_maxLat) {
		// m_maxLat = lat;
		// }
		// if (duration < m_minDuration) {
		// m_minDuration = duration;
		// }
		// if (duration > m_maxDuration) {
		// m_maxDuration = duration;
		// }
		// if (distance < m_minDistance) {
		// m_minDistance = distance;
		// }
		// if (distance > m_maxDistance) {
		// m_maxDistance = distance;
		// }

		// TODO write in metafile

	}

	private void printConsole(Long percentDone, Coordinates currentPoint, Long duration, Long distance,
			double distProDur) {
		System.out.print(percentDone + "%");
		System.out.print("   ");
		System.out.print("Coordinates: " + currentPoint);
		System.out.print("   ");
		System.out.print("Duration: " + duration);
		System.out.print("   ");
		System.out.print("Distance: " + distance);
		System.out.print("   ");
		System.out.print("Dist/Dur: " + distProDur);
		System.out.println();
	}
}
