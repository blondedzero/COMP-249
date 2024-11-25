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
    protected static long serialNumCounter = 1000000000;

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
        if(newSerialNum < 1000000000){
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


/* Instructions:

I) The CellPhone class has the following attributes: a serialNum (long type), a brand (String type), a year (int type, which indicates manufacturing year) and a price (double type). It is assumed that brand name is always recorded as a single word (i.e. Motorola, SonyEricsson, Panasonic, etc.). It is also assumed that all cellular phones follow one system of assigning serial numbers, regardless of their different brands, so no two cell phones may have the same serial number.

You are required to write the implementation of the CellPhone class. Beside the usual mutator and accessor methods (i.e. getPrice(), setYear()) the class must have the following:
(a) Parameterized constructor that accepts four values and initializes serialNum, brand, year and price to these passed values;
(b) Copy constructor, which takes two parameters, a CellPhone object and a long value. The newly created object will be assigned all the attributes of the passed object, with the exception of the serial number. serialNum is assigned the value passed as the second parameter to the constructor. It is always assumed that this value will correspond to the unique serial number rule;
(c) clone() method. This method will prompt the user to enter a new serial number, then creates and returns a clone of the calling object with the exception of the serial number, which is assigned the value entered by the user;
(d) Additionally, the class should have a toString() and an equals() methods. Two cell phones are equal if they
have the same attributes, with the exception of the serial number, which could be different.

*/
