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
	
	// TODO: finish a beautiful builder with custom classloaders
	//       and extra infos to put in the json and display
	@Override
	public Vehicle build(JSONObject car) {
		try {
			String make = car.getString("make");	
			String model = car.getString("model");		
			int year = car.getInt("year");
			double value = car.getDouble("value");
			int noo = car.getInt("noo");
			int hash = car.getInt("pwd");
			return new SecondHandVehicle(make, model, year, value, noo, hash);
		}catch(JSONException e){
			e.printStackTrace();
		}
			return new SecondHandVehicle("error", "error", -1, -1, -1);
	}

	@Override
	public Vehicle getBlankVehicule() {
		return new SecondHandVehicle();
	}

}
