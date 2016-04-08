package fr.fboschet.voiture.loader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.json.JSONArray;
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

	@Override
	public void populate(List<Vehicle> lv) {
		StringBuilder sb = new StringBuilder();
		JSONObject kk = new JSONObject();
		VehicleBuilder vb = SecondHandVehicleBuilder.getInstance();
		
		try {
			// Read a file (java-lambda style)
			Files.lines(toParse.toPath()).forEach((String s) -> sb.append(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
		kk = new JSONObject(sb.toString());
		
		JSONArray vehicles = kk.getJSONArray("vehicles");
		
		for(int i = 0; i < vehicles.length(); i++) {
			lv.add(vb.build(vehicles.getJSONObject(i)));
		}
	}
}
