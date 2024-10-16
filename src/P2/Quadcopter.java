package P2;
public class Quadcopter extends Helicopter{

private int maxFlyingSpeed;

public Quadcopter() {
	this.maxFlyingSpeed = 0;
}

public Quadcopter(String brand, double price, int horsePower, int numberOfCylinders, int creationYear,
		int passengerCapacity, int maxFlyingSpeed) {
	super(brand, price, horsePower, numberOfCylinders, creationYear, passengerCapacity);
	
	this.maxFlyingSpeed = maxFlyingSpeed;
}

//copy constructor
 public Quadcopter(Quadcopter copy) {
    this(copy.brand, copy.price, copy.horsePower, copy.numberOfCylinders, copy.creationYear, copy.passengerCapacity, copy.maxFlyingSpeed);

}

public int getMaxFlyingSpeed() {
return this.maxFlyingSpeed;
}

public int gsetMaxFlyingSpeed() {
return this.maxFlyingSpeed;
}


}
