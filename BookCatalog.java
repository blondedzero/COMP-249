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
				String[] allFields = checkFields(line);
				// ["title", "author", etc...]
                if (allFields.length < 6) {
                	throw TooFewFieldsException("Too Few Fields!");
                }
                if (allFields.length > 6) {
                	throw TooManyFieldsException("Too Many Fields!"); 
                }
                // FIELDS HAVE BEEN VERIFIED.  
                String missingField = checkMissingField(allFields);
                if (!missingField.equals("All")) {
                	throw MissingFieldException(missingField);
				}
				String isbn = allFields[3];
            
			}
		} catch(FileNotFoundException e) {
				System.out.println("This file was not found");
			}
		}


			//validateRecord function
			private static String[] checkFields(String record) {
				if(record.contains("\"")){
					// "Zimbalist, Efrem - A Life",Roy Malan,19.95,1574670913,MRB,1905
					String[] field1 = record.split("\"");
					// ["Zimbalist, Efrem - A Life", "Roy Malan,19.95,1574670913,MRB,1905"]
					field1[1].split(",");
					String[] otherfields = record.split(",");
					// ["Zimbalist, Efrem - A Life", "Roy Malan,19.95,1574670913,MRB,1905"]
					String[] allFields = new String[otherfields.length + 1];
                    //[, , , , , ]
					allFields[0] = field1[0];
 					for(int i = 0; i < otherfields.length; i++){
                    	allFields[i+1] = otherfields[i];
                    }
                    return allFields; 
  
				} else {
					// Hitchcock's London: A Reference Guide to Locations,Gary Giblin,19.95,188766467X,MTV,1905
					String[] fields = record.split(",");
					return fields; 
				}
		}


			private static String checkMissingField(String[] allFields) {
				// ["Zimbalist, Efrem - A Life", "Roy Malan", "19.95", "3617312312", "MRB", 1905]
				
				for(int i = 0; i < allFields.length; i++){
                	if(allFields[i].equals("") || allFields[i] == null){
                    	switch(i){
                          	case 0:
                            return "Title";

                        	case 1:
                            return "Author";

                            case 2:
							return "Price";
					
							case 3:
							return "ISBN";
						
                          case 4:
                            return "Genre";
                       
                            
                          case 5:
                            return "Year";
                                               
                        }
                    }
                }
                return "All";
			}


  			private static boolean checkISBN10(String ISBN){
           		//"1234565219"
              int x1 = Integer.parseInt(ISBN.substring(0,1));
              int x2 = Integer.parseInt(ISBN.substring(1,2));
              int x3 = Integer.parseInt(ISBN.substring(2,3));
              int x4 = Integer.parseInt(ISBN.substring(3,4));
              int x5 = Integer.parseInt(ISBN.substring(4,5));
              int x6 = Integer.parseInt(ISBN.substring(5,6));
              int x7 = Integer.parseInt(ISBN.substring(6,7));
              int x8 = Integer.parseInt(ISBN.substring(7,8));
              int x9 = Integer.parseInt(ISBN.substring(8,9));
              int x10 = Integer.parseInt(ISBN.substring(9,10));
              
              int sum = (10 * x1) + (9 * x2) + (8*x3) + (7*x4) + (6*x5) + (5*x6) + (4*x7) + (3*x8) + (2*x9)+ (1*x10);
              
              if(sum % 11 == 0){
              	return true;
              } else { return false; }
            }

			private static boolean checkISBN13(String ISBN){
				//"1234565219"
		   int x1 = Integer.parseInt(ISBN.substring(0,1));
		   int x2 = Integer.parseInt(ISBN.substring(1,2));
		   int x3 = Integer.parseInt(ISBN.substring(2,3));
		   int x4 = Integer.parseInt(ISBN.substring(3,4));
		   int x5 = Integer.parseInt(ISBN.substring(4,5));
		   int x6 = Integer.parseInt(ISBN.substring(5,6));
		   int x7 = Integer.parseInt(ISBN.substring(6,7));
		   int x8 = Integer.parseInt(ISBN.substring(7,8));
		   int x9 = Integer.parseInt(ISBN.substring(8,9));
		   int x10 = Integer.parseInt(ISBN.substring(9,10));
		   int x11 = Integer.parseInt(ISBN.substring(9,11));
		   int x12 = Integer.parseInt(ISBN.substring(9,12));
		   int x13 = Integer.parseInt(ISBN.substring(9,13));
		   
		   int sum = x1 + (3 * x2) + x3 + (3*x4) + x5 + (3*x6) + x7 + (3*x8) + x9 + (3*x10)+ x11 + (3*x12)+ x11;
		   
		   if(sum % 13 == 0){
			   return true;
		   } else { return false; }
		 }
	}	
	

