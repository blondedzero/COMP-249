package P2;
import P1.Airplane;

public class Helicopter extends Airplane {
	
	//Helicopter attributes
	private int numberOfCylinders;
	private int creationYear;
	private int passengerCapacity;

	public Helicopter() {
	    this.numberOfCylinders = 0;
	    this.creationYear = 0;
	    this.passengerCapacity = 0;
	    }

	    
	    //parameterized constructor
	    public Helicopter(String brand, double price, int horsePower, int numberOfCylinders, int creationYear, int passengerCapacity){
	    	super(brand, price, horsePower);
	        this.numberOfCylinders = numberOfCylinders;
	        this.creationYear = creationYear;
	        this.passengerCapacity = passengerCapacity;

	    }
	    
	    //setters
	    public int getNumberOfCylinders() {
	    	return this.numberOfCylinders;
	    }
	    
	    public int getCreationYear() {
	    	return this.creationYear;
	    }
	    
	    public int getPassengerCapacity() {
	    	return this.passengerCapacity;
	    }
	    
	    public int setNumberOfCylinders() {
	    	return this.numberOfCylinders;
	    }
	    
	    public int setCreationYear() {
	    	return this.creationYear;
	    }
	    
	    public int setPassengerCapacity() {
	    	return this.passengerCapacity;
	    }
}
