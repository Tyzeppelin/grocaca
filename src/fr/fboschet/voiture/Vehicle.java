package fr.fboschet.voiture;

import java.time.Year;


// NOTE FOR THE PERSON WHO WRITE THIS FILE
// ========================================
//
// Why are you writing code like:
// ``
// if (boolean_expression)
//  	return true
// else
//      return false
// ``
//
// Do you even know how boolean algebra works?
// Why aren't you writing :
// ``
// return boolean_expression
// ``
//
// Oh and one last thing. The equals method is faulty.
// http://www.artima.com/lejava/articles/equality.html

//Class to model a Vehicle
//Used for inheritance only so its abstract
public abstract class Vehicle{
	// Instance Variables
	protected int number;
	protected String make;	
	protected String model;		
	protected int year;
	protected double value; 
	// hash of the password
	protected int pwdHash;

	private static int nextUniqueNumber=1;	
	// Next available unique Vehicle number
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

	public Vehicle(String make, String model, int year, double value, int pwd) {
		this.number=nextUniqueNumber++;		
		this.make=make;			
		this.model=model;	
		this.year=year;
		this.value=value;
		this.pwdHash=pwd;
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
	// #boolean
	public boolean equals(Vehicle vehicleIn){
		//		if(this.number == vehicleIn.number)
		//			return true;
		//		else
		//			return false;
		return this.number == vehicleIn.number;
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
		// in this case, the use of 'this' is a good way of disambiguation
		return Year.now().getValue() - this.year;
	}

	// qualifyForScrappage() method
	// AGAIN ?!?!?!?!?!?
	public boolean qualifyForScrappage(){  
		//		if(calculateAge() >= 10)
		//			return true;
		//		else
		//			return false;
		return calculateAge() >= 10;
	}
	
	public int getPwd() {
		return this.pwdHash;
	}
}