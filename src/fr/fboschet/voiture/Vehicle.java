package fr.fboschet.voiture;

import java.time.Year;

//Class to model a Vehicle
//Used for inheritance only so its abstract
public abstract class Vehicle{
	// Instance Variables
	protected int number;
	protected String make;	
	protected String model;		
	protected int year;
	protected double value; 

	private static int nextUniqueNumber=1;	// Next available unique Vehicle number
	// static - means nextUniqueNumber is SHARED
	// amongst all Vehicle objects, so if one
	// of them change it, it is changed for all.

	private final double TAX_A = 175.00;
	private final double TAX_B = 300.00;
	private final double TAX_C = 500.00;
	private final double TAX_D = 670.00;		
	private final double TAX_E = 850.00;			


	// Constructor 1
	public Vehicle(){  
		// Set number to nextUniqueNumber, then increment it for next Vehicle
		this.number=nextUniqueNumber++;
		this.make=this.model=new String();
		this.year=0;
		this.value=0.0;  
	}

	// Constructor 2
	public Vehicle(String make, String model, int year, double value){  
		// Set number to nextUniqueNumber, then increment it for next Vehicle
		this.number=nextUniqueNumber++;		
		this.make=make;			
		this.model=model;	
		this.year=year;
		this.value=value;
	}

	// getMake()
	public String getMake(){  
		return make; 
	}

	// getModel()
	public String getModel(){  
		return model; 
	}

	// getYear()
	public int getYear(){  
		return year; 
	}

	// getValue()
	public double getValue(){  
		return value; 
	}

	// toString()
	public String toString(){
		return("VEHICLE "+number+"==>"+make+", "+model+", "+year+", €"+value);
	}	

	// equals() method
	public boolean equals(Vehicle vehicleIn){
		if(this.number == vehicleIn.number)
			return true;
		else
			return false;
	}

	// decreaseValue() method	
	public String decreaseValue(double newValue){
		if(newValue < value){
			value = newValue;
			// return message to indicate value changed
			return ("Value changed to " + value + ".");
		}
		else
			// return message to indicate newValue is not less than value
			return (newValue + " is not less than current value of " + value + ".");		
	}

	// calculateTaxPayable() method
	public double calculateTaxPayable(double engineSize){  
		if(engineSize > 2000)
			return TAX_E;
		else if(engineSize > 1800)
			return TAX_D;
		else if(engineSize > 1500)
			return TAX_C;
		else if(engineSize > 1000)
			return TAX_B;
		else
			return TAX_A;
	}

	// calculateAge() - calculates the age
	public int calculateAge(){
		int currentYear = Year.now().getValue();
		return currentYear - year;
	}

	// qualifyForScrappage() method
	public boolean qualifyForScrappage(){  
		if(calculateAge() >= 10)
			return true;
		else
			return false;
	}
}