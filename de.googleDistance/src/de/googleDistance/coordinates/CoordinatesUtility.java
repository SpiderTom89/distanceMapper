package de.googleDistance.coordinates;

public final class CoordinatesUtility {

	private CoordinatesUtility() {
	}

	public static SphereCoordinates calcSphereCoord(CartasianCoordinates cartasianCoord) {
		double lat = 2 * Math.atan(Math.exp(cartasianCoord.getXValue())) - 0.5 * Math.PI;
		double lng = cartasianCoord.getYValue();

		return new SphereCoordinates(lng, lat);
	}

	public static CartasianCoordinates calcCartasianCoord(SphereCoordinates sphereCoord) {
		double lat = sphereCoord.getLat();
		double x = 0.5 * Math.log((1 + Math.sin(lat)) / (1 - Math.sin(lat)));
		double y = sphereCoord.getLng();

		return new CartasianCoordinates(x, y);
	}
}
