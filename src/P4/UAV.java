package P4;

public class UAV {
   public double weight;
   public double price;
   
   public UAV() {
	   this.weight = 0.0;
	   this.price = 0.0;
   }
   
   public UAV(double weight, double price) {
	   
	   this.weight = weight;
	   this.price = price;
   }

//copy constructor
   public UAV(UAV copy) {
	   this(copy.weight, copy.price);
   }  
   
   public double getUAVWeight() {
	   return this.weight;
   }
   
   public double getUAVPrice() {
	   return this.price;
   }
   
   public double setUAVWeight() {
	   return this.weight;
   }
   
   public double setUAVPrice() {
	   return this.price;
   }

//toString
   public String toString() {
		return "This UAV has a weight of " + this.weight + " and a costs $" + this.price;
   }
}
