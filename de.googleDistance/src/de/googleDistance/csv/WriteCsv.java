package de.googleDistance.csv;

import java.io.FileWriter;
import java.io.IOException;

import de.googleDistance.coordinates.SphereCoordinates;

public class WriteCsv {

	private FileWriter m_fileWriter;

	// Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	// CSV file header
	private static final String FILE_HEADER = "" + "originLng" + COMMA_DELIMITER + "origingLat" + COMMA_DELIMITER
			+ "destinationLng" + COMMA_DELIMITER + "destinationLat" + COMMA_DELIMITER + "duration" + COMMA_DELIMITER
			+ "distance" + COMMA_DELIMITER + "dis/dur";

	public WriteCsv(String fileName) {
		try {
			initializeFile(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initializeFile(String fileName) throws IOException {
		m_fileWriter = new FileWriter(fileName);
		// Write the CSV file header
		m_fileWriter.append(FILE_HEADER.toString());

		// Add a new line separator after the header
		m_fileWriter.append(NEW_LINE_SEPARATOR);
	}

	public void addLine(SphereCoordinates origin, SphereCoordinates destination, Long duration, Long distance, double distPerDur)
			throws IOException {
		m_fileWriter.append(String.valueOf(origin.getLng()));
		m_fileWriter.append(COMMA_DELIMITER);
		m_fileWriter.append(String.valueOf(origin.getLat()));
		m_fileWriter.append(COMMA_DELIMITER);
		m_fileWriter.append(String.valueOf(destination.getLng()));
		m_fileWriter.append(COMMA_DELIMITER);
		m_fileWriter.append(String.valueOf(destination.getLat()));
		m_fileWriter.append(COMMA_DELIMITER);
		m_fileWriter.append(String.valueOf(duration));
		m_fileWriter.append(COMMA_DELIMITER);
		m_fileWriter.append(String.valueOf(distance));
		m_fileWriter.append(COMMA_DELIMITER);
		m_fileWriter.append(String.valueOf(distPerDur));
		m_fileWriter.append(NEW_LINE_SEPARATOR);
	}

	public void closeFile() throws IOException {
		m_fileWriter.flush();
		m_fileWriter.close();
	}
}
