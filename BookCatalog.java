import java.io.*;
import java.util.*;

public class BookCatalog {

	//givenc code
	public static void main(String[] args) {
      		{
        			do_part1();
        			do_part2();
        			do_part3();
      		
      		}

      	}
      
	//do_part1
	public static void do_part1() {

	try {
		FileReader myFileReader = new FileReader("Part1_input_file_names.txt");
        BufferedReader br = new BufferedReader(myFileReader);
        
		int numberOfFiles = Integer.parseInt(br.readLine());

		String filename;
	//reads lines until it reaches an empty line, loop will stop
    		while((filename = br.readLine())!= null) {
    			processFile(filename);
    		}
    	}
    	catch(IOException e){
    		System.out.println("Error reading input file names, " + e.getMessage());
    		}
    	}
    
	//do_part2
	public static void do_part2(){

	}

	//do_part3
	public static void do_part3(){

	}

	//functions
	//function that reads the files within the main file
	private static void processFile(String filename) {
      try {
          FileReader myFileReader = new FileReader(filename); 
          BufferedReader br = new BufferedReader(myFileReader);
          
          String line; 
          while ((line = br.readLine()) != null) {
          	// line is a record 
            
          }
          
      } catch(FileNotFoundException e) {
          System.out.println("This file was not found"); 
      }
	
	  String line;

	//used a while loop to read the contents of the file until it reaches the end where it will stop the loop
	while ((line = br.readLine()) != null) {
	try {
	checkFields(line);
	//catches all the exceptions created in one go
	} catch ( TooManyFieldsException | TooFewFieldsException | MissingFieldException | UnknownGenreException e){
	logSyntaxError(e.getMessage(), filename, line);
			}
		}
	}
	catch(IOException e){
	System.out.println("Error processing file name, " + e.getMessage());
		}
	}

	//validateRecord function
	private static boolean checkFields(String record) {
		
		if(record.contains("\"")){
			// "Zimbalist, Efrem - A Life",Roy Malan,19.95,1574670913,MRB,1905
            String[] field1 = record.split("\"");
            field1[1].split(",");
            String[] otherfields = record.split(",");
            // ["Zimbalist, Efrem - A Life", "Roy Malan,19.95,1574670913,MRB,1905"]
		} else {
        	// Hitchcock's London: A Reference Guide to Locations,Gary Giblin,19.95,188766467X,MTV,1905
            String[] fields = record.split(","); 
            if(fields.length == 6) {
            	return true;
            } else { 
            return false;
           }
        }
	}

	//logSyntaxError
	private static void logSyntaxError(String message, String filename, String line) {
	// TODO Auto-generated method stub

	}
