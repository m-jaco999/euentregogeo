package com.desafio.geolocation.api;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GeoLocationController {

	private final String urlBase = "https://maps.googleapis.com/maps/api/geocode/json?address=";
	private final String keyParam = "&key=AIzaSyBkEOGhqm-CqHuYHhc5Ol29PxEt1fmWPfk";

	@SuppressWarnings("unchecked")
	@PostMapping
	public ResponseEntity<?> getLatAndLong(@RequestBody LinkedHashMap<String, Object> objeto) {
		RestTemplate restTemplate = new RestTemplate();

		LinkedHashMap<String, String> response = new LinkedHashMap<>();
		List<Double> latitude = new java.util.ArrayList<>(Collections.emptyList());
		List<Double> longitude = new java.util.ArrayList<>(Collections.emptyList());
		String[] enderecos = objeto.get("enderecos").toString().split("; ");

		for (String s : enderecos) {
			s = s.replace(" ", "+");
			String url = urlBase + s + keyParam;

			ResponseEntity<Object> response2 = restTemplate.getForEntity(url, Object.class);

			LinkedHashMap<String, Object> responseJson = (LinkedHashMap<String, Object>) response2.getBody();
			List<LinkedHashMap<String, Object>> resultArray = (List<LinkedHashMap<String, Object>>) responseJson
					.get("results");
			if (!resultArray.isEmpty()) {
				LinkedHashMap<String, Object> arrayFirstIndex = resultArray.get(0);
				LinkedHashMap<String, Object> geoLocation = (LinkedHashMap<String, Object>) arrayFirstIndex
						.get("geometry");
				LinkedHashMap<String, Object> location = (LinkedHashMap<String, Object>) geoLocation.get("location");

				latitude.add(Double.parseDouble(location.get("lat").toString()));

				longitude.add(Double.parseDouble(location.get("lng").toString()));
			}
		}

			Double resultDist;

			resultDist = distance(latitude.get(0),latitude.get(1),longitude.get(0),longitude.get(1));
			response.put("distancia:","O endereco 1 possui "+resultDist+" de distancia do endereço 2");

		return ResponseEntity.ok(response);
	}


	public static double distance(double lat1, double lat2, double lon1, double lon2) {

		Double pointA = Math.pow(lat1-lat2, 2);
		Double pointB = Math.pow(lon1-lon2, 2);
		
		return Math.sqrt(pointA + pointB);
	}
}
