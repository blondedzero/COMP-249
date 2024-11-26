// -----------------------------------------------------
// Assignment 3
// Question 1
// Written by: Nicholas Chamoun (40264135) & Kaila Quimson (40240746)
// -----------------------------------------------------


import java.util.*;
public class CellPhone{

    static Scanner scan = new Scanner(System.in);
    //constant attributes
    protected long serialNum;
    protected String brand;
    protected int year;
    protected double price;

    //intializes serial number to 1000000000
    protected static long serialNumCounter = 1000000;

    //parameterized contructor
    public CellPhone(long serialNum, String brand, int year, double price){

        this.serialNum = serialNum;
        this.brand = brand;
        this.year = year;
        this.price = price;

    }

    //default constructor
    public CellPhone(String brand, int year, double price){

        //increments serial nmmber
        this.serialNum = serialNumCounter++;
        this.brand = brand;
        this.year = year;
        this.price = price;

    }

    //getters & setters
    public long getSerialNum(){
        return this.serialNum;
    }
    public String getBrand(){
        return this.brand;
    }
    public int getYear(){
        return this.year;
    }
    public double getPrice(){
        return this.price;
    }
    public long setSerialNum(){
        return this.serialNum;
    }
    public String setBrand(){
        return this.brand;
    }
    public int setYear(){
        return this.year;
    }
    public double setPrice(){
        return this.price;
    }

    //copy constructor
    public CellPhone(CellPhone copy, long newSerialNum){

        this.brand = copy.brand;
        this.year = copy.year;
        this.price = copy.price;
        this.serialNum = newSerialNum;

    }

    //clone method
    public CellPhone clone(){

        System.out.println("Enter a new serial number (must be 10 digits)");
        long newSerialNum = scan.nextLong();

        //checks if user input is less than 10 digits. If so it prompts a message
        if(newSerialNum < 1000000){
            System.out.println("Invalid serial number output");
        }

        return new CellPhone(this, newSerialNum);

    }

    public String toString(){
        return "Cellphone: \n Serial Number: " + this.serialNum + "\n Brand: '" + this.brand + " '\' \nYear: " +this.year+ "\n Price: " +this.price;
    }

    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass())
            return false;
        CellPhone c = (CellPhone) o;
        return (this.brand == c.brand && this.year == c.year && this.price == c.price);
    }
      
}
