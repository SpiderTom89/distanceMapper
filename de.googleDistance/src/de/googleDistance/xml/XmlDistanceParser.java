package de.googleDistance.xml;

import java.util.List;

import org.w3c.dom.Element;
import org.xml.sax.InputSource;

public class XmlDistanceParser {

	private XmlParser m_xmlParser;

	public XmlDistanceParser(InputSource inputSource) {
		m_xmlParser = new XmlParser(inputSource);
	}

	public Long parseDuration() {
		List<Element> durations = m_xmlParser.parseDocumentFor("duration");
		if (durations.size() == 0) {
			return 0L;
		}
		Element duration = durations.get(durations.size() - 1);
		String durationValue = m_xmlParser.getTextValue(duration, "value");
		return Long.valueOf(durationValue);
	}

	public Long parseDistance() {
		List<Element> distances = m_xmlParser.parseDocumentFor("distance");
		if (distances.size() == 0) {
			return 0L;
		}
		Element distance = distances.get(distances.size() - 1);
		String distanceValue = m_xmlParser.getTextValue(distance, "value");
		return Long.valueOf(distanceValue);
	}
}
