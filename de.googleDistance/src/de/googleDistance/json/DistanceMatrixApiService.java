package de.googleDistance.json;

import com.google.gson.Gson;

import de.googleDistance.coordinates.CartasianCoordinates;
import de.googleDistance.coordinates.CoordinatesUtility;
import de.googleDistance.coordinates.SphereCoordinates;
import de.googleDistance.rest.RestService;

public class DistanceMatrixApiService {

	private CartasianCoordinates m_cartOrigin;
	private CartasianCoordinates m_cartDestination;
	private SphereCoordinates m_sphereOrigin;
	private SphereCoordinates m_sphereDestination;
	private long m_distance;
	private long m_duration;

	public DistanceMatrixApiService(CartasianCoordinates origin, CartasianCoordinates destination) {
		m_cartOrigin = origin;
		m_cartDestination = destination;
		m_sphereOrigin = CoordinatesUtility.calcSphereCoord(origin);
		m_sphereDestination = CoordinatesUtility.calcSphereCoord(destination);
		requestDistanceDuration();
	}

	public DistanceMatrixApiService(SphereCoordinates origin, SphereCoordinates destination) {

	}

	public static String getDistance2(SphereCoordinates origin, SphereCoordinates destination) {
		String request = "http://maps.googleapis.com/maps/api/distancematrix/json?origins=Hippelstra%C3%9Fe+24,+81827+M%C3%BCnchen&destinations=Altheimer+Eck+2,+80331+M%C3%BCnchen&";
		String output = RestService.restOutput(request);
		Gson gson = new Gson();
		Example fromJson = gson.fromJson(output, Example.class);

		System.out.println(fromJson.toString());

		return null;
	}

	private void requestDistanceDuration() {
		boolean withKey = false;
		String urlString = new String();
		urlString = "https://maps.googleapis.com/maps/api/distancematrix/json?";

		String originString = "origin=" + m_sphereOrigin.getLat() + "," + m_sphereOrigin.getLng();
		String destinationString = "destination=" + m_sphereDestination.getLat() + "," + m_sphereDestination.getLng();

		String request = urlString + originString + "&" + destinationString;
		if (withKey) {
			String key = "key=" + "AIzaSyBDaKTPOJ9DRw-c2AjSdxiMBSHWtpvjK0o";
			request = request + "&" + key;
		}

		String output = RestService.restOutput(request);
		Gson gson = new Gson();
		Example fromJson = gson.fromJson(output, Example.class);
		String status = fromJson.getStatus();
		if ("OK".equals(status)) {
			Element element = fromJson.getRows().get(0).getElements().get(0);
			m_distance = element.getDistance().getValue();
			m_duration = element.getDistance().getValue();
		}
	}
}
