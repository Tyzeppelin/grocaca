package fr.fboschet.voiture;

//Class to model SecondHandVehicle objects
//SecondHandVehicle IS-A Vehicle ==> Inherit from Vehicle
public class SecondHandVehicle extends Vehicle{  
	// Instance Variables
	private int numberOfOwners;

	// Constructor 1
	public SecondHandVehicle(){  
		this.numberOfOwners=0; 
	}

	// Constructor 2
	public SecondHandVehicle(String make, String model, int year, double value, int numberOfOwners){
		super(make,model,year,value);
		this.numberOfOwners = numberOfOwners; 
	}

	// toString() - called when a SecondHandVehicle object is displayed
	public String toString(){
		return super.toString() + ", " + numberOfOwners + " owner(s).";
	}	

	// equals() method
	// AND AGAIN, AND AGAIN, ...
	public boolean equals(SecondHandVehicle secondHandVehicleIn){
		//	   if(super.equals(secondHandVehicleIn))
		//			return true;
		//		else
		//		   return false;
		return super.equals(secondHandVehicleIn);
	}
}