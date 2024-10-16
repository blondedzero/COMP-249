package P5;
import P4.UAV;

public class MAV extends UAV{
    private String model;
    private double size;

    public MAV() {
    	this.model = "";
    	this.size = 0.0;
    }    
    
    public MAV(double weight, double price, String model, double size) {
    	super(weight, price);
    	
    	this.model = model;
    	this.size = size;
    	
    }
    //copy constructor
    public MAV(MAV copy) {
    	this(copy.weight, copy.price, copy.model, copy.size);
    }
    
    public String getModel() {
    	return this.model;
    }
    
    public double getSize() {
    	return this.size;
    }
    
    public String setModel() {
    	return this.model;
    }
    
    public double setSize() {
    	return this.size;
    }
}

