package fr.fboschet.voiture.builder;

import org.json.JSONObject;

import fr.fboschet.voiture.Vehicle;

public interface VehicleBuilder {
	
	public Vehicle build(JSONObject car);	
}
