public class UAV {
   private double weight;
   private double price;
   
   public UAV() {
	   this.weight = 0.0;
	   this.price = 0.0;
   }
   
   public UAV(double weight, double price) {
	   
	   this.weight = weight;
	   this.price = price;
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
}
