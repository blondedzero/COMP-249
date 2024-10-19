//-----------------------------------------------------
// Part: 1
// Written by: Kaila Quimson 40240746 &
//-----------------------------------------------------

package P2;
public class Quadcopter extends Helicopter {

	//quadcopter attribute
	protected int maxFlyingSpeed;
	
	//default constructor
	public Quadcopter() {
		this.maxFlyingSpeed = 40;	//mph
	}
	
	//parameterized constructor
	public Quadcopter(String brand, double price, int horsePower, int numberOfCylinders, int creationYear, int passengerCapacity, int maxFlyingSpeed) {
		super(brand, price, horsePower, numberOfCylinders, creationYear, passengerCapacity);
		this.maxFlyingSpeed = maxFlyingSpeed;
	}
	
	//copy constructor
	 public Quadcopter(Quadcopter copy) {
	    this(copy.brand, copy.price, copy.horsePower, copy.numberOfCylinders, copy.creationYear, copy.passengerCapacity, copy.maxFlyingSpeed);
	}
	
	//getter
	public int getMaxFlyingSpeed() {
	return this.maxFlyingSpeed;
	}
	
	//setter
	public int setMaxFlyingSpeed() {
	return this.maxFlyingSpeed;
	}

	//toString
    @Override
    public String toString() {
		return "This is a " + this.brand + " quadcopter. It can fly at a max speed of " + this.maxFlyingSpeed + " kts and it is priced at $" + this.price + 
				". Its engine can produce " + this.horsePower + " horse power and has " + this.numberOfCylinders + " cylinders. The model was manufactured in "
				+ this.creationYear + " and it can hold up to " + this.passengerCapacity +" passengers.";
    }
    
  //equals
  	@Override
  	public boolean equals(Object x)
  	{
  		if (x == null || this.getClass() != x.getClass())
  			return false;
  			// cast the passed object to a Helicopter object
  			Quadcopter q = (Quadcopter)x;
  			return (this.brand == q.brand && this.price == q.price && this.horsePower == q.horsePower 
  					&& this.numberOfCylinders == q.numberOfCylinders && this.creationYear == q.creationYear
  					&& this.passengerCapacity == q.passengerCapacity && this.maxFlyingSpeed == q.maxFlyingSpeed);
  		}

}
