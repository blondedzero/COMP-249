// -----------------------------------------------------
// Assignment 3
// Question 3
// Written by: Nicholas Chamoun (40264135) & Kaila Quimson (40240746)
// -----------------------------------------------------
import java.io.*;
import java.util.*;

public class CellListUtilization {

    static Scanner scan = new Scanner(System.in); // Scanner
    public static void main(String[] args) {

        // Creation of empty lists
        CellList l1 = new CellList();
        CellList l2 = new CellList();
        
        try (BufferedReader read = new BufferedReader(new FileReader("Cell_info.txt"))){

            String line;

            // loops through file, assuming it isn't null it splits contents based on one or more spaces 
            while ((line = read.readLine()) != null){

                String[] fields = line.split("\\s+");

                if(fields.length >= 4){

                    try{

                        // associating each field seperated to it's own attribute and parsing it to it's corresponding type
                        //Using .trim to remove an excess spaces
                        long serialNum = Long.parseLong(fields[0].trim());
                        String brand = fields[1].trim();
                        double price = Double.parseDouble(fields[2].trim());
                        int year = Integer.parseInt(fields[3].trim());

                        // Create new CellPhone obj
                        CellPhone phone = new CellPhone(serialNum, brand, year, price);

                        // duplicate verification
                        if(!l1.contains(serialNum)){
                            l1.addToStart(phone); // adds to start if not a duplicate
                        }

                    } catch (NumberFormatException nfe){ System.out.println("Invalid line at " + line);}
                }
            }
  
        } catch (IOException e) { System.out.println("Error! could not read file" + e.getMessage());}

        // displaying l1
        System.out.println("Contents of first list initilized by file: ");
        l1.showContents();

        System.out.println("Enter corresponding serial numbers to search in file (use a comma for seperation)");
        String input = scan.nextLine();
        String[] serialNum = input.split(",");

        for(int i = 0; i < serialNum.length; i++){

            try{
                String sn = serialNum[i].trim();
                long serialNumber = Long.parseLong((sn)); // parses to long 

                CellList.CellNode foundNode = l1.find(serialNumber);  // find node

                if(foundNode != null){System.out.println("Phone found: " + foundNode.getPhone());}
                else{System.out.println("Phone with SN " + serialNumber + " has not been found.");}

            } catch(NumberFormatException nfe){ System.out.println("Error: Invalid serial number " + serialNum[i]);}
        }

    }
}
   
    



    



/* Instructions:

IV) Now, you are required to write a public class called CellListUtilization. In the main() method, you must do the following:

(a) Create at least two empty lists from the CellList class;
(b) Open the Cell_Info.txt file, and read its contents line by line. Use these records to initialize one of the CellList objects you created above. You can simply use the addToStart() method to insert the read objects
into the list. However, the list should not have any duplicate records, so if the input file has duplicate entries,
which is the case in the file provided with the assignment for instance, your code must handle this case so that
each record is inserted in the list only once;
(c) Show the contents of the list you just initialized;
(d) Prompt the user to enter a few serial numbers and search the list that you created from the input file for these
values. Make sure to display the number of iterations performed;


(e) Following that, you must create enough objects to test each of the constructors/methods of your classes. The details of this part are left as open to you. You can do whatever you wish as long as your methods are being tested including some of the special cases.
*/