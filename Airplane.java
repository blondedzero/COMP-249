
public class Airplane {
	
	protected String brand;
	protected double price;
	protected int horsePower;
	
	public Airplane() {
		this.brand = "";
		this.price = 0.0;
		this.horsePower = 0;
	}

    public Airplane(String brand, double price, int horsePower){
	this.brand = brand;
	this.price = price;
	this.horsePower = horsePower;
    }

    public String getBrand(){
	return this.brand;
    }
    public double getPrice(){
	return this.price;
    }
    public int getHorsePower(){
	return this.horsePower;
    }

    public String setBrand(){
	return this.brand;
    }
    public double setPrice(){
	return this.price;
    }
    public int setHorsePower(){
	return this.horsePower;
    }

}
