package P5;
import P4.UAV;

public class AgriculturalDrone extends UAV{
    private String brand;
    private int carryCapacity;

        public AgriculturalDrone() {
    	this.brand = "";
    	this.carryCapacity = 0;
    }
    
    public AgriculturalDrone(double weight, double price, String brand, int carryCapacity) {
    	super(weight, price);
    	
    	this.brand = brand;
    	this.carryCapacity = carryCapacity;
    }
    
    public String getADBrand() {
    	return this.brand;
    }
    
    public int getCarryCapacity() {
    	return this.carryCapacity;
    }

    public String setADBrand() {
    	return this.brand;
    }
    
    public int setCarryCapacity() {
    	return this.carryCapacity;
    }
}
