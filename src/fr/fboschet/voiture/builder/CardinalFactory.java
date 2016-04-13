package fr.fboschet.voiture.builder;

public class CardinalFactory {

	// My factory is a lazy singleton.
	// It means there will only be one instance of it
	// @runtime, and it will be instantiate only when it
	// will be needed
	private static CardinalFactory INSTANCE;

	private CardinalFactory() {}

	public static CardinalFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CardinalFactory();
		}
		return INSTANCE;
	}

	// Method that return the cardinal of a given (positive obviously)ordinal
	public String getCardinal(int n) {
		String res = ""+n;
		if (n > 0) {
			int lastDigit = n%10;
			int last2Digits = n%100;
			if (lastDigit == 1 && last2Digits != 11) 
				res += "st";
			else if (lastDigit == 2 && last2Digits != 12) 
				res += "nd";
			else if (lastDigit == 3 && last2Digits != 13) 
				res += "rd";
			else 
				res +="th";
		}
		return res;
	}
}
