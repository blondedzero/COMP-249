// -----------------------------------------------------
// Assignment 2
// Question: PART 1, PART 2 & PART 3
// Written by: Kaila Quimson (40240746) & Nicholas Chamoun (40264135)
// ----------------------------------------------------- 


import java.io.*;
import java.util.*;



public class BookCatalog {

		private static PrintWriter errorWriter;

		// Arrays for genre codes and corresponding output file names
		private static final String[] genreCodes = {"CCB", "HCB", "MTV", "MRB", "NEB", "OTR", "SSM", "TPA"};
		private static final String[] genreFileNames = {
			"Cartoons_Comics_Books.csv",
			"Hobbies_Collectibles_Books.csv",
			"Movies_TV.csv",
			"Music_Radio_Books.csv",
			"Nostalgia_Eclectic_Books.csv",
			"Old_Time_Radio.csv",
			"Sports_Sports_Memorabilia.csv",
			"Trains_Planes_Automobiles.csv"
		};

		public static void main(String[] args) throws TooFewFieldsException, TooManyFieldsException, MissingFieldException, UnknownGenreException, IOException {
		{
			try {
				//errorWriter = new PrintWriter(new FileWriter("syntax_error_file.txt"));
				do_part1();
				do_part2();
				do_part3();
			} finally{
				if (errorWriter != null){
					errorWriter.close();
				}
			}

		}

	}

	//do_part1
		public static void do_part1() throws TooFewFieldsException, TooManyFieldsException, MissingFieldException, UnknownGenreException{

		try {
			
			FileReader myFileReader = new FileReader("txtFiles/Part1_input_file_names.txt");
			BufferedReader br = new BufferedReader(myFileReader);

			int numberOfFiles = Integer.parseInt(br.readLine());
			System.out.println("Number of Files: " + numberOfFiles);

			String filename;
			//READS LINES UNTIL IT REACHES AN EMPTY LINE WHICH WILL CASUE THE LOOP TO STOP
			while((filename = br.readLine())!= null) {
				processFile("txtFiles/" + filename);
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



		//FUNCTIONS
		//FUNCTION THAT READS LINES WITHIN THE FIRST FILE
		private static void processFile(String filename) throws TooFewFieldsException, TooManyFieldsException, MissingFieldException, IOException, UnknownGenreException {
			String line = null;

			BufferedWriter[] writers = new BufferedWriter[genreCodes.length];

			System.out.println("Inside Process file: " + filename);
				
			try {
				FileReader myFileReader = new FileReader(filename);
				BufferedReader br = new BufferedReader(myFileReader);

				// INITIALIZES WRITERS FOR EACH GENRE FILE
				for (int i = 0; i < genreCodes.length; i++) {
					writers[i] = new BufferedWriter(new FileWriter(genreFileNames[i]));
				}

	
				//WHILE LOOP THAT WILL STOP ONCE IT REACHES THE LAST LINE
				while ((line = br.readLine()) != null) {
					//LINE IS A RECORD
					//THE CHECKFIELDS IS CALLES AND STORES IN A NEW ARRAY
					String[] allFields = checkFields(line);
					
					System.out.println("All fields: " + Arrays.toString(allFields));

					//CHECKS IF THERE ARE NOT ENOUGH FIELDS AND TROWS AN EXCEPTION
					if (allFields.length < 6) {
						throw new TooFewFieldsException("Too Few Fields!");
					}

					//CHECKS IF THERE ARE TOO MANY FIELDS AND THROWS AN EXCEPTION
					if (allFields.length > 6) {
						throw new TooManyFieldsException("Too Many Fields!"); 
					}
					// FIELDS HAVE BEEN VERIFIED. 
					System.out.println("Fields have been verified");

					//CALLS THE MISSING FIELDS FUNCITON AND STORES RETURN INTO A VARIABLE 
					String missingField = checkMissingField(allFields);

					//CHECKS IF THE RETURN DOES NOT CORRESPONDS TO "ALL" MEANING THERE IS A FIELD MISSING THUS THROWS AN EXCEPTION
					if (!missingField.equals("All")) {
						throw new MissingFieldException(missingField);
					}


					//CHECKS FOR ISBN VALIDITY
					String isbn = allFields[3]; //ISBN IS FOUND IN POSITION 3 OF THE ARRAY STORING IT IN THE ISBN VARIABLE
					if(isbn.length() == 10){
						if(checkISBN10(isbn) == true) {
							System.out.println("VALILD ISBN");
						} else{
							logSyntaxError(filename, "Invalid ISBN", line);
						}
					}else{
						if(checkISBN13(isbn) == true) {
							System.out.println("VALILD ISBN");
						}else{
							logSyntaxError(filename, "Invalid ISBN", line);
						}
					}

					System.out.println("ISBN verified.");

					//CHECKS FOR GENRE VALIDITY
					String genreCode = allFields[4];
                	int genreIndex = checkGenre(genreCode);

					System.out.println(genreIndex);

                	if (genreIndex == -1) {
						logSyntaxError(filename, "Unknown Genre: " + genreCode, line);
						throw new UnknownGenreException(genreCode);
               		}

					//CHECKS FOR YEAR VALIDITY
					String year = allFields[5];
					if(checkYear(year) != true){
						System.out.println("Invalid year");
						continue;
					}

					//WRITE TO SPECIFIC GENRE FILE
					System.out.println("Line 160"+line);
					writers[genreIndex].write(line);
					writers[genreIndex].newLine();
				}
				} catch (TooFewFieldsException e) {
					System.out.println("Too few fields");
				} catch (UnknownGenreException ug){
					System.out.println("unknown genre ");
				} catch (TooManyFieldsException tmf){System.out.println("too many fields");

				}catch (MissingFieldException mf){
					System.out.println("missing fields " );
					
				}
				catch (IOException e){
					System.out.println("file not found");
				}

				finally {
					//CLOSE GENRE WRITERS
					for (BufferedWriter writer : writers) {
						if (writer != null) {
							writer.close();
						}
					}
				}
			}
			
		

			//vVALID RECORD FUNCTION
			private static String[] checkFields(String record) {
				if(record.contains("\"")){
					//CONSIDERS 2 CASE FOR POTENTIAL TITLES
					//CASE 1 IS WITH QUOTATIONS. IT WILL FIND RECORD WITH QUOTATIONS AND SPLIT IT FROM THERE TO STORE IT AN ARRAY
					String[] field1 = record.split("\"");
					//IT WILL THEN SPLIT AFTER EVERY COMMA IT FINDS AND STORE IT IN AN ARRAY STORUN
					field1[1].split(",");

					//CASE 2 WILL JUST CONSIDER COMMAS, SIMILAR TO THE LINE ABOVE
					String[] otherfields = record.split(",");
					String[] allFields = new String[otherfields.length + 1];
                    
					allFields[0] = field1[0];
 					for(int i = 0; i < otherfields.length; i++){
                    	allFields[i+1] = otherfields[i];
                    }
                    return allFields; 
  
				} else {
					
					String[] fields = record.split(",");
					return fields; 
				}
		}


			private static String checkMissingField(String[] allFields) {
			
				
				for(int i = 0; i < allFields.length; i++){
                	if(allFields[i].equals("") || allFields[i] == null){
                    	switch(i){
                          	case 0: return "Title";
							case 1: return "Author";
							case 2: return "Price";
							case 3: return "ISBN";
							case 4: return "Genre";
                       		case 5: return "Year";                       
                        }
                    }
                }
                return "All";
			}


  			private static boolean checkISBN10(String ISBN){
           	try{
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
				}catch(NumberFormatException nfe){
					return false;
				}
            }

			private static boolean checkISBN13(String ISBN){
				try {
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
					
					int sum = x1 + (3 * x2) + x3 + (3*x4) + x5 + (3*x6) + x7 + (3*x8) + x9 + (3*x10)+ x11 + (3*x12)+ x11 + (3*x13);
				
					if(sum % 13 == 0){
						return true;
					} else { return false; }
				}catch(NumberFormatException nfe){
					return false;
				}
			}


		 private static int checkGenre(String genreCode) {
			for (int i = 0; i < genreCodes.length; i++) {
				if (genreCodes[i].equals(genreCode)) {
					return i;
				}
			}
			return -1; // Return -1 if the genre code is not found
		}

		 private static boolean checkYear(String year){
			int validYear = Integer.parseInt(year);
			if(validYear >= 1900 && validYear <= 2026) return true;
			else return false;
		 }

		private static void logSyntaxError(String filename, String errorMsg, String record){
			FileWriter errorWriter;
			try {
				errorWriter = new FileWriter("syntax_error_file.txt");

				errorWriter.write("Syntax error in file: " + filename);
				errorWriter.write("-----------------------------");
				errorWriter.write("Error: " + errorMsg);
				errorWriter.write("Record: " + record);
				errorWriter.write("");
			} catch (IOException e) {
				e.printStackTrace();
			}

			

		} 
	}	

	

