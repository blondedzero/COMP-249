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

 //toString
    @Override
    public String toString() {
		return "This is a " + this.brand + " quadcopter, at the price of $" + this.price + " .It possesses " 
				+ this.horsePower + " horae power and has ."
				+ this.numberOfCylinders+ " cylinders. It was made in the year " + this.creationYear 
				+ " .It can hold up to " + this.passengerCapacity +" passengers and can fly at a max speed of ." + this.maxFlyingSpeed+ " kts";
    	
    }


}
