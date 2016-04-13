package fr.fboschet.voiture.builder;

import org.json.JSONObject;

import fr.fboschet.voiture.Vehicle;

/**
 * 
 * @author Francois Boschet
 *
 */
public interface VehicleBuilder {
	
	/**
	 * Build a car given a JSONObject
	 * @param car
	 * @return
	 */
	public Vehicle build(JSONObject car);
	
	/**
	 * 
	 * @return
	 */
	public Vehicle getBlankVehicule();	
}
