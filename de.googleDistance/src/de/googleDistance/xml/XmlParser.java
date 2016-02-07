package de.googleDistance.xml;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlParser {

	private Document m_dom;
	private InputSource m_inputSource;

	public XmlParser(InputSource inputSource) {
		m_inputSource = inputSource;
		parseXmlFile();
	}

	private void parseXmlFile() {
		// get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			// Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parse using builder to get DOM representation of the XML file
			m_dom = db.parse(m_inputSource);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public Attr getAttributeNode(Element element, String name) {
		return element.getAttributeNode(name);
	}

	public List<Element> parseElementFor(Element element, String tagName) {
		List<Element> result = new LinkedList<Element>();

		NodeList nl = element.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {
				// get the employee element
				Element el = (Element) nl.item(i);
				result.add(el);
			}
		}
		return result;
	}

	public List<Element> parseDocumentFor(String tagName) {
		List<Element> result = new LinkedList<Element>();

		// get the root element
		Element docEle = m_dom.getDocumentElement();

		// get a nodelist of elements
		NodeList nl = docEle.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {
				// get the employee element
				Element el = (Element) nl.item(i);
				result.add(el);
			}
		}
		return result;
	}

	/**
	 * I take a xml element and the tag name, look for the tag and get the text
	 * content i.e for <employee><name>John</name></employee> xml snippet if the
	 * Element points to employee node and tagName is 'name' I will return John
	 */
	public String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element) nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}

	/**
	 * Calls getTextValue and returns a int value
	 */
	public int getIntValue(Element ele, String tagName) {
		// in production application you would catch the exception
		return Integer.parseInt(getTextValue(ele, tagName));
	}
}
