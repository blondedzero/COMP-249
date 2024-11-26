// -----------------------------------------------------
// Assignment 3
// Question 3
// Written by: Nicholas Chamoun (40264135) & Kaila Quimson (40240746)
// -----------------------------------------------------
import java.io.*;
import java.util.*;

public class CellListUtilization {
    public static void main(String[] args) {

        // Creation of empty lists
        CellList l1 = new CellList();
        CellList l2 = new CellList();
        
        try {
            BufferedReader read = new BufferedReader(new FileReader("Cell_info"));
        } catch (FileNotFoundException e) {
         
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