package fr.fboschet.voiture.test;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fr.fboschet.voiture.SecondHandVehicle;
import fr.fboschet.voiture.Vehicle;
import fr.fboschet.voiture.loader.JSONParser;
import fr.fboschet.voiture.loader.SeconHandVehicleLoader;
import fr.fboschet.voiture.ui.SecondHandVehicleFrame;

public class SecondHandVehicleTester{
	public static void main(String args[]){
// OMG
// 80's called, they want their arrays back.....
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
		jp.populate(vehicles);

		// showInputDialog() passing in the vehicles array
		// returnedValue will get the selected vehicle
		SecondHandVehicle returnedValue = (SecondHandVehicle)JOptionPane.showInputDialog(null, "Choose a SecondHandVehicle", 
				"VEHICLES AVAILABLE", JOptionPane.INFORMATION_MESSAGE, null, vehicles.toArray(), vehicles.get(0));


		// CONSTRUCT a SecondHandVehicleFrame object called frame, passing the selected SecondHandVehicle into the constrctor
		SecondHandVehicleFrame frame = new SecondHandVehicleFrame(returnedValue);

		// Do whatever to frame object
		frame.setTitle("SECOND HAND VEHICLE OPTIONS");
		frame.pack();	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);					
		frame.setVisible(true);
	}
}