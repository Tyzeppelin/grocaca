package fr.fboschet.voiture.loader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.fboschet.voiture.Vehicle;
import fr.fboschet.voiture.builder.SecondHandVehicleBuilder;
import fr.fboschet.voiture.builder.VehicleBuilder;

public class SeconHandVehicleLoader implements JSONParser {
	
	private static File toParse;
		
	private static SeconHandVehicleLoader INSTANCE;
	
	private SeconHandVehicleLoader() {}
	
	public static SeconHandVehicleLoader getInstance(String path) {
		if (INSTANCE == null) {
			INSTANCE = new SeconHandVehicleLoader();
		}
		toParse = new File(path);
		return INSTANCE;
	}
	
	// Populate the given list of Vehiclse with those from the File toParse
	@Override
	public void populate(List<Vehicle> lv) {
		StringBuilder sb = new StringBuilder();
		JSONObject json = new JSONObject();
		VehicleBuilder vb = SecondHandVehicleBuilder.getInstance();
		
		try {
			// Read a file (java-lambda style)
			Files.lines(toParse.toPath()).forEach((String s) -> sb.append(s));
		} catch (IOException e) {
			// in case the file don'exist
			sb.append("{}");
		}
		// cerate a jsonobject from our file
		json = new JSONObject(sb.toString());
		
		
		// I know this isn't the prettiest way to parse json files
		// TODO: a correct interpreter of our json file
		try {
			JSONArray vehicles = json.getJSONArray("vehicles");
			for(int i = 0; i < vehicles.length(); i++) {
				// call the vehicleBuilder for each vehicule if the json file
				lv.add(vb.build(vehicles.getJSONObject(i)));
			}
		}catch (JSONException e) {
			// if the file isn't well formated, return a blank vehicle
			lv.add(vb.getBlankVehicule());
		}
	}
}
