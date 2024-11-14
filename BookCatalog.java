// -----------------------------------------------------
// Assignment 2
// Question: Part 1,2
// Written by: Kaila Quimson (40240746) & Nicholas Chamoun (40264135)
// ----------------------------------------------------- 

import java.io.*;
import java.util.*;

public class BookCatalog {

	//ARRAYS FOR THE GENRECODES, CSV FILE NAMES, AND BINARY FILE NAMES FOR SERIALIZATION
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
    private static final String[] binaryFileNames = {
            "Cartoons_Comics_Books.csv.ser", 
			"Hobbies_Collectibles_Books.csv.ser", 
			"Movies_TV.csv.ser",
            "Music_Radio_Books.csv.ser", 
			"Nostalgia_Eclectic_Books.csv.ser", 
			"Old_Time_Radio_Books.csv.ser",
            "Sports_Sports_Memorabilia.csv.ser", 
			"Trains_Planes_Automobiles.csv.ser"
    };

    public static void main(String[] args) throws TooFewFieldsException, TooManyFieldsException, MissingFieldException, UnknownGenreException, IOException {
        try {
            do_part1();
            do_part2();
            do_part3();
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
	// do_part1: READ AND PROCESS INPUT FILES FOR SYNTAX ERRORS
    public static void do_part1() throws TooFewFieldsException, TooManyFieldsException, MissingFieldException, UnknownGenreException {
        try (BufferedReader br = new BufferedReader(new FileReader("txtFiles/part1_input_file_names.txt"))) {
            int numberOfFiles = Integer.parseInt(br.readLine());
            System.out.println("Number of Files: " + numberOfFiles);

            String filename;
            while ((filename = br.readLine()) != null) {
                processFile("txtFiles/" + filename);
            }
        } catch (IOException e) {
            System.out.println("Error reading input file names: " + e.getMessage());
        }
    }

	// do_part2: VALIDATES AND SERIALIZES RECORDS
    public static void do_part2() {
        for (int i = 0; i < genreFileNames.length; i++) {
            String csvFileName = genreFileNames[i];
            String binaryFileName = binaryFileNames[i];
            List<Book> books = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(csvFileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    try {
                        Book book = createBookFromRecord(line);
                        books.add(book);
                    } catch (Exception e) {
                        logSemanticError(csvFileName, e.getMessage(), line);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading file " + csvFileName + ": " + e.getMessage());
            }

            serializeBooks(books, binaryFileName);
        }
    }

    // Part 3: 
    public static void do_part3() {
    }

	//FUNCTIONS
	//FUNCTION THAT PROCESS EACH INPUT FILE FOR SYNTAX VALIDATION
	private static void processFile(String filename) {
		String line;
		System.out.println("Processing file: " + filename);

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			while ((line = br.readLine()) != null) {
				try {
					String[] allFields = checkFields(line);

					if (allFields.length < 6) {
						logSyntaxError(filename, "Too Few Fields", line);
						continue; 
					}

					if (allFields.length > 6) {
						logSyntaxError(filename, "Too Many Fields", line);
						continue;  
					}

					String missingField = checkMissingField(allFields);
					if (!missingField.equals("All")) {
						logSyntaxError(filename, "Missing Field: " + missingField, line);
						continue;  
					}

					String isbn = allFields[3];
					if (isbn.length() == 10) {
						if (!checkISBN10(isbn)) logSyntaxError(filename, "Invalid ISBN-10", line);
					} else if (!checkISBN13(isbn)) {
						logSyntaxError(filename, "Invalid ISBN-13", line);
					}

					String genreCode = allFields[4];
					int genreIndex = checkGenre(genreCode);
					if (genreIndex == -1) {
						logSyntaxError(filename, "Unknown Genre: " + genreCode, line);
						continue; 
					}

					String year = allFields[5];
					if (!checkYear(year)) {
						logSyntaxError(filename, "Invalid Year", line);
						continue;  
					}

					writeToGenreFile(genreFileNames[genreIndex], line);

				} catch (Exception e) {
					System.out.println("Error processing record in file " + filename + ": " + e.getMessage());
					logSyntaxError(filename, e.getMessage(), line);
					
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading file " + filename + ": " + e.getMessage());
		}
	}

    // Create a Book object from a CSV record and perform semantic validation
    private static Book createBookFromRecord(String record) throws BadIsbn10Exception, BadIsbn13Exception, BadPriceException, BadYearException {
        String[] fields = record.split(",");
        String title = fields[0];
        String authors = fields[1];
        double price = parsePrice(fields[2]);
        String isbn = fields[3];
        String genre = fields[4];
        int year = parseYear(fields[5]);

        validateISBN(isbn);
        return new Book(title, authors, price, isbn, genre, year);
    }

    private static double parsePrice(String priceStr) throws BadPriceException {
        try {
            double price = Double.parseDouble(priceStr);
            if (price < 0) throw new BadPriceException("Negative price");
            return price;
        } catch (NumberFormatException e) {
            throw new BadPriceException("Invalid price format");
        }
    }

    private static int parseYear(String yearStr) throws BadYearException {
        try {
            int year = Integer.parseInt(yearStr);
            if (year < 1995 || year > 2010) throw new BadYearException("Year out of valid range");
            return year;
        } catch (NumberFormatException e) {
            throw new BadYearException("Invalid year format");
        }
    }

    private static void validateISBN(String isbn) throws BadIsbn10Exception, BadIsbn13Exception {
        if (isbn.length() == 10 && !checkISBN10(isbn)) {
            throw new BadIsbn10Exception("Invalid ISBN-10");
        } else if (isbn.length() == 13 && !checkISBN13(isbn)) {
            throw new BadIsbn13Exception("Invalid ISBN-13");
        }
    }

	// Write records to the appropriate genre file in CSV format
	private static void writeToGenreFile(String fileName, String record) {
		File csvDir = new File("csvFiles");
		File outputFile = new File(csvDir, fileName);  // Create the file in the csvFiles folder

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true))) {
			writer.write(record);
			writer.newLine();
		} catch (IOException e) {
			System.out.println("Error writing to genre file: " + e.getMessage());
		}
	}

    // Check the genre code to get the corresponding index
    private static int checkGenre(String genreCode) {
        for (int i = 0; i < genreCodes.length; i++) {
            if (genreCodes[i].equals(genreCode)) return i;
        }
        return -1;
    }

    // Helper methods for checking fields and ISBN validation
    private static String[] checkFields(String record) {
        if (record.contains("\"")) {
            String[] field1 = record.split("\"");
            field1[1].split(",");

            String[] otherFields = record.split(",");
            String[] allFields = new String[otherFields.length + 1];

            allFields[0] = field1[0];
            System.arraycopy(otherFields, 0, allFields, 1, otherFields.length);
            return allFields;
        } else {
            return record.split(",");
        }
    }
	private static String checkMissingField(String[] allFields) {
		for (int i = 0; i < allFields.length; i++) {
			if (allFields[i] == null || allFields[i].isEmpty()) {
				if (i == 0) return "Title";
				else if (i == 1) return "Author";
				else if (i == 2) return "Price";
				else if (i == 3) return "ISBN";
				else if (i == 4) return "Genre";
				else if (i == 5) return "Year";
				else return "Unknown";
			}
		}
		return "All";
	}

    private static boolean checkISBN10(String ISBN) {
        try {
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += (10 - i) * Integer.parseInt(String.valueOf(ISBN.charAt(i)));
            }
            return sum % 11 == 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean checkISBN13(String ISBN) {
        try {
            int sum = 0;
            for (int i = 0; i < 13; i++) {
                int digit = Integer.parseInt(String.valueOf(ISBN.charAt(i)));
                sum += (i % 2 == 0) ? digit : 3 * digit;
            }
            return sum % 10 == 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean checkYear(String year) {
        try {
            int validYear = Integer.parseInt(year);
            return validYear >= 1900 && validYear <= 2026;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Log syntax errors with a header for each error
    private static void logSyntaxError(String filename, String errorMsg, String record) {
        try (BufferedWriter errorWriter = new BufferedWriter(new FileWriter("syntax_error_file.txt", true))) {
            errorWriter.write("Syntax error in file: " + filename);
            errorWriter.write("\n-----------------------------");
            errorWriter.write("\nError: " + errorMsg);
            errorWriter.write("\nRecord: " + record);
            errorWriter.write("\n\n");
        } catch (IOException e) {
            System.err.println("Error writing to syntax error file: " + e.getMessage());
        }
    }

    // Log semantic errors for Part 2
    private static void logSemanticError(String filename, String errorMsg, String record) {
        try (BufferedWriter errorWriter = new BufferedWriter(new FileWriter("semantic_error_file.txt", true))) {
            errorWriter.write("Semantic error in file: " + filename);
            errorWriter.write("\n-----------------------------");
            errorWriter.write("\nError: " + errorMsg);
            errorWriter.write("\nRecord: " + record);
            errorWriter.write("\n\n");
        } catch (IOException e) {
            System.err.println("Error writing to semantic error file: " + e.getMessage());
        }
    }

   // Serialize a list of Book objects to a binary file
   private static void serializeBooks(List<Book> books, String fileName) {
	File serDir = new File("serFiles");
	File outputFile = new File(serDir, fileName);  // Create the file in the serFiles folder

	try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputFile))) {
		oos.writeObject(books.toArray(new Book[0]));
	} catch (IOException e) {
		System.out.println("Error serializing books to " + fileName + ": " + e.getMessage());
	}
	}

    
    }

  