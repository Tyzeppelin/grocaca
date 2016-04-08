package fr.fboschet.voiture.loader;

import java.util.List;

import fr.fboschet.voiture.Vehicle;

public interface JSONParser {

	public void populate(List<Vehicle> lv);
}
