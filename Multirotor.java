public class Multirotor extends Helicopter{
	
	private int numberOfRotors;
	
	public Multirotor(String brand, double price, int horsePower, int numberOfCylinders, int creationYear,
			int passengerCapacity, int numberOfRotors) {
		super(brand, price, horsePower, numberOfCylinders, creationYear, passengerCapacity);
		
		this.numberOfRotors = numberOfRotors;
	}
	
		//getter
	   public int getNumberOfRotors() {
	    	return this.numberOfRotors;
	    }
		
	   //setter
	   public int setNumberOfRotors() {
	    	return this.numberOfRotors;
	    }    
}
