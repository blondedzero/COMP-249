package P2;
public class Quadcopter extends Helicopter{
	
	private int maxFlyingSpeed;
	
    public Quadcopter(String brand, double price, int horsePower, int numberOfCylinders, int creationYear,
			int passengerCapacity, int maxFlyingSpeed) {
		super(brand, price, horsePower, numberOfCylinders, creationYear, passengerCapacity);
		
		this.maxFlyingSpeed = maxFlyingSpeed;
    }
    
    public int getMaxFlyingSpeed() {
    	return this.maxFlyingSpeed;
    }
    
    public int gsetMaxFlyingSpeed() {
    	return this.maxFlyingSpeed;
    }

	
}
