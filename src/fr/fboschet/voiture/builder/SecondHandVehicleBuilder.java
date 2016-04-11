package fr.fboschet.voiture.builder;

import org.json.JSONException;
import org.json.JSONObject;

import fr.fboschet.voiture.SecondHandVehicle;
import fr.fboschet.voiture.Vehicle;

public class SecondHandVehicleBuilder implements VehicleBuilder {

	private static SecondHandVehicleBuilder INSTANCE;
	
	private SecondHandVehicleBuilder() {}
	
	public static SecondHandVehicleBuilder getInstance(){
		if (INSTANCE == null) {
			INSTANCE = new SecondHandVehicleBuilder();
		}
		return INSTANCE;
	}
	
	@Override
	public Vehicle build(JSONObject car) {
		try {
			String make = car.getString("make");	
			String model = car.getString("model");		
			int year = car.getInt("year");
			double value = car.getDouble("value");
			int noo = car.getInt("noo");
			return new SecondHandVehicle(make, model, year, value, noo);
		}catch(JSONException e){
			e.printStackTrace();
		}
			// I may need to use a more exceptions.
			return new SecondHandVehicle("error", "error", -1, -1, -1);
	}

}
