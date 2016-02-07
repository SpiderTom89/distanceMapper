package de.googleDistance.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RestService {

	public static String restOutput(String urlString) {
		String output = null;
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			StringBuilder sb = new StringBuilder();
			String line;

			while ((line = br.readLine()) != null)
				sb.append(line);

			// do {
			// sb.append(br.readLine());
			// sb.append("\n");
			// } while ((br.readLine()) != null);

			// while ((br.readLine()) != null) {
			// sb.append(br.readLine());
			// sb.append("\n");
			// }
			output = sb.toString();

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
		return output;
	}
}
