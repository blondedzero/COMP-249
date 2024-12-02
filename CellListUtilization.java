// -----------------------------------------------------
// Assignment 3
// Question 3
// Written by: Nicholas Chamoun (40264135) & Kaila Quimson (40240746)
// -----------------------------------------------------

/**
* The CellListUtilization class provides a program for manipulating CellPhone records stored in
* a linked list. It reads data from the Cell_Info.txt file, allows user interaction for searching,
* modifying, and testing the linked list, and ensures no duplicate entries are present in the list.
*/ 

import java.io.*;
import java.util.*;

public class CellListUtilization {

    // potential privacy leak: constantly displaying the serial numbers of phones.
    // fix: that data could be censored.

    static Scanner scan = new Scanner(System.in); // Scanner

    public static void main(String[] args) {

        // Creation of empty lists
        CellList l1 = new CellList();
        CellList l2 = new CellList();
        
        // Welcome Message
        System.out.println("~ Welcome to Nicholas & Kaila's Cellphone Record List Manipulation Program! ~\n");

        // find() Method Changelog
        System.out.println("Changelog for the serial number find method:");

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

                    } catch (NumberFormatException nfe){ System.out.println("Invalid line at " + line);}    // potential privacy leak: the line could contain sensitive info!
                }
            }
  
        } catch (IOException e) { System.out.println("Error! could not read file" + e.getMessage());}

        // displaying l1
        System.out.println("\nContents of first list initilized by file: ");
        l1.showContents();

        System.out.println("Enter corresponding serial numbers to search in file (use a comma for seperation): ");
        String input = scan.nextLine();
        String[] serialNum = input.split(",");

        for(int i = 0; i < serialNum.length; i++){

            try{
                String sn = serialNum[i].trim();
                long serialNumber = Long.parseLong((sn)); // parses to long 

                CellList.CellNode foundNode = l1.find(serialNumber);  // find node

                if(foundNode != null){System.out.println("      - Phone found: " + foundNode.getPhone());}
                else{
                    System.out.println("    - Warning: Phone with SN " + serialNumber + " has not been found.");
                }

            } catch(NumberFormatException nfe){ System.out.println("Error: Invalid serial number " + serialNum[i]);}
        }

        CellPhone p1 = new CellPhone(1497204, "iPhone", 2024, 1200.99);
        CellPhone p2 = new CellPhone(8624729, "Nokia", 2024, 200.99);
        CellPhone p3 = new CellPhone(5929837, "Blackberry", 2024, 1200.99);

        l2.addToStart(p1);
        l2.addToStart(p2);
        l2.addToStart(p3);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n ~ Test Menu ~");
            System.out.println("(1) Display contents of the list 2");
            System.out.println("(2) Add to Start");
            System.out.println("(3) Insert at Index");
            System.out.println("(4) Replace at Index");
            System.out.println("(5) Delete from Index");
            System.out.println("(6) Delete from Start");
            System.out.println("(7) Check if a Serial Number Exists");
            System.out.println("(8) Copy the List");
            System.out.println("(9) Compare Two Lists");
            System.out.println("(0) Exit");
            
            System.out.print("Enter choice: ");
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    l2.showContents();
                    break;

                case 2: {
                    System.out.print("Enter a phone to add (Enter in this format: SN Brand Year Price): ");

                    long sn = scan.nextLong();
                    String brand = scan.next();
                    int year = scan.nextInt();
                    double price = scan.nextDouble();

                    CellPhone userPhone = new CellPhone(sn, brand, year, price);
                    l2.addToStart(userPhone);
                    System.out.println("Phone added to start of list 2.");
                    break;
                }
                case 3: {
                    System.out.print("Enter index to insert at: ");
                    int index = scan.nextInt();
                    System.out.print("Enter a phone to insert (Enter in this format: SN Brand Year Price): ");
                    long sn = scan.nextLong();
                    String brand = scan.next();
                    int year = scan.nextInt();
                    double price = scan.nextDouble();

                    CellPhone userPhone = new CellPhone(sn, brand, year, price);
                    try {
                        l2.insertAtIndex(userPhone, index);
                        System.out.println("Phone inserted at index " + index);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 4: {
                    System.out.print("Enter index to replace at: ");
                    int index = scan.nextInt();
                    System.out.print("Enter a phone to replace with (Enter in this format: SN Brand Year Price): ");
                    long sn = scan.nextLong();
                    String brand = scan.next();
                    int year = scan.nextInt();
                    double price = scan.nextDouble();

                    CellPhone userPhone = new CellPhone(sn, brand, year, price);
                    try {
                        l2.replaceAtIndex(userPhone, index);
                        System.out.println("Phone replaced at index " + index);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 5: {
                    System.out.print("Enter index to delete from: ");
                    int index = scan.nextInt();
                    try {
                        l2.deleteFromIndex(index);
                        System.out.println("Deleted from index " + index);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 6: {
                    l2.deleteFromStart();
                    System.out.println("Deleted from start.");
                    break;
                }
                case 7: {
                    System.out.print("Enter a serial number to find: ");
                    long serial = scan.nextLong();
                    if (l2.contains(serial)) {
                        System.out.println("Serial number exists in the list.");
                    } else {
                        System.out.println("Serial number does not exist.");
                    }
                    break;
                }
                //double check
                case 8: {
                    CellList copy = new CellList(l2);
                    System.out.println("Copied list contents:");
                    copy.showContents();
                    break;
                }
                case 9: {
                    System.out.println("Creating a second list for comparison...");
                    System.out.println("Are the lists equal? " + l1.equals(l2));
                    break;
                }
                case 0: {
                    exit = true;
                    System.out.println("\nSession ended. Terminating the program...");
                    break;
                }
                default: {
                    System.out.println("Invalid choice! Try again.");
                }
            }
        }
        scan.close();
        System.exit(1);
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