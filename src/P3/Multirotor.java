package P3;
import P2.Helicopter;

public class Multirotor extends Helicopter{
	
	private int numberOfRotors;
	
		public Multirotor() {
		this.numberOfRotors = 0;
	}	
	
	public Multirotor(String brand, double price, int horsePower, int numberOfCylinders, int creationYear,
			int passengerCapacity, int numberOfRotors) {
		super(brand, price, horsePower, numberOfCylinders, creationYear, passengerCapacity);
		
		this.numberOfRotors = numberOfRotors;
	}

	//copy constructor
	public Multirotor(Multirotor copy) {
		this(copy.brand, copy.price, copy.horsePower, copy.numberOfCylinders, copy.creationYear, copy.passengerCapacity, copy.numberOfRotors);
	}
	
	//getter
	public int getNumberOfRotors() {
	return this.numberOfRotors;
	}
	
	//setter
	public int setNumberOfRotors() {
	return this.numberOfRotors;
	}
	    
    @Override
    public String toString() {
		return "This is a " + this.brand + " muiltirotor aircraft, at the price of $" + this.price + " .It possess " 
				+ this.horsePower + " horae power and has ."
				+ this.numberOfCylinders+ " cylinders and " +this.numberOfRotors+" rotors. It was made in the year " + this.creationYear 
				+ " and can hold up to " + this.passengerCapacity +" passengers.";
    	
    }
    
}
