package de.googleDistance.rest;

public class DistanceService {

	public static String getDistance(Coordinates origin, Coordinates destination) {
		boolean withKey = false;
		String urlString = new String();
		if (withKey) {
			urlString = "https://maps.googleapis.com/maps/api/directions/xml?";
		} else {
			urlString = "http://maps.googleapis.com/maps/api/directions/xml?";
		}

		String originString = "origin=" + origin.toString();
		String destinationString = "destination=" + destination.toString();
		String key = "key=" + "AIzaSyDX4agjvpCURiiT9JtdjVG1RSEG-d8TGF4";

		String request = urlString + originString + "&" + destinationString;
		if (withKey) {
			request = request + "&" + key;
		}

		return RestService.restOutput(request);
	}
}
