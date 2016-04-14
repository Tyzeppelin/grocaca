package fr.fboschet.voiture.test;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import fr.fboschet.voiture.SecondHandVehicle;
import fr.fboschet.voiture.Vehicle;
import fr.fboschet.voiture.loader.JSONParser;
import fr.fboschet.voiture.loader.SeconHandVehicleLoader;
import fr.fboschet.voiture.ui.DialogFactory;
import fr.fboschet.voiture.ui.SecondHandVehicleFrame;

public class SecondHandVehicleTester{
	public static void main(String args[]){

		//		// Declare an Array of 5 SecondHandVehicles called vehicles
		//		final int NUMBER_OF_VEHICLES = 5;
		//		SecondHandVehicle[] vehicles = new SecondHandVehicle[NUMBER_OF_VEHICLES];
		//
		//		// Create 5 new SecondHandVehicle objects with initial values...
		//	   SecondHandVehicle v1 = new SecondHandVehicle("Opel", "Astra", 1999, 800.00, 2);
		//	   SecondHandVehicle v2 = new SecondHandVehicle("Toyota", "RAV4", 2006, 15000.00, 2);
		//	   SecondHandVehicle v3 = new SecondHandVehicle("Mazda", "323F", 1998, 1000.00, 1);
		//	   SecondHandVehicle v4 = new SecondHandVehicle("Ford", "Fiesta", 2009, 8000.00, 3);
		//	   SecondHandVehicle v5 = new SecondHandVehicle("Alfa", "Romeo", 2005, 7500.00, 1);
		//
		//		//...and add them to the array called vehicles
		//		vehicles[0]=v1;
		//		vehicles[1]=v2;
		//		vehicles[2]=v3;
		//		vehicles[3]=v4;
		//		vehicles[4]=v5;


		// It's very boring to add vehicles in the main mathod. So I made a lil' JSON parser and a VehicleBuilder
		JSONParser jp = SeconHandVehicleLoader.getInstance("res/car1.json");

		List<Vehicle> vehicles = new ArrayList<>();
		// popultate our list with vehicles from the json file
		jp.populate(vehicles);

		SecondHandVehicleFrame frameFactory = SecondHandVehicleFrame.getInstance();
		DialogFactory dialogFactory = DialogFactory.getInstance();

		// showInputDialog() passing in the vehicles array
		// returnedValue will get the selected vehicle
		SecondHandVehicle currVehicle = dialogFactory.choiceDialog(vehicles);

		// I.E. if the user selected one vehicle
		if (currVehicle != null) {
			int hash = 0;
			int attempt = 0;
			// 3 attempt to input the correct password
			while(hash != currVehicle.getPwd() && attempt < 3 && hash != -1) {
				hash = dialogFactory.passwordDialog();
				attempt++;
			}
			if (hash == currVehicle.getPwd() && attempt < 4) {
				frameFactory.run(currVehicle);
			}
			else if (hash != -1) {JOptionPane.showMessageDialog(null, "Too many failed attempts", "Failure", JOptionPane.ERROR_MESSAGE);}
		}
	}
}