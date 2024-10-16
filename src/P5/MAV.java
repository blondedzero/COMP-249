package P5;
import P4.UAV;

public class MAV extends UAV{
    private String model;
    private double size;
    
    public MAV(double weight, double price, String model, double size) {
    	super(weight, price);
    	
    	this.model = model;
    	this.size = size;
    	
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

