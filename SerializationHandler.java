// -----------------------------------------------------
// Assignment 2
// Question: Part 2
// Written by: Kaila Quimson (40240746) & Nicholas Chamoun (40264135)
// ----------------------------------------------------- 


// This class handles the serialization of Book objects from CSV files into binary files.
//It reads data from specified CSV files, converts it into Book objects, and serializes them into binary files.

import java.io.*;

public class SerializationHandler {

    public void do_part2(String[] inputFiles) throws IOException {
        String[] csvFiles = FileNames.CSV_FILES;
        
        for (String csvFile : csvFiles) {
            BufferedReader reader = new BufferedReader(new FileReader("txtFiles/" + inputFiles));
            BufferedWriter errorWriter = new BufferedWriter(new FileWriter("semantic_error_file.txt", true));
            
            Book[] validBooks = new Book[100];  // Example size
            int validCount = 0;
            
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] fields = line.split(",");
                    String title = fields[0];
                    String author = fields[1];
                    double price = Double.parseDouble(fields[2]);
                    String isbn = fields[3];
                    String genre = fields[4];
                    int year = Integer.parseInt(fields[5]);
                    
                    // ISBN-10 Validation
                    switch (isbn.length()) {
                        case 10:
                            validateIsbn10(isbn);
                            break;
                        case 13:
                            validateIsbn13(isbn);
                            break;
                        default:
                            throw new BadIsbn10Exception("Invalid ISBN-10 or ISBN-13 length");
                    }
                    
                    // Price Validation
                    if (price <= 0) {
                        throw new BadPriceException("Invalid price: " + price);
                    }
                    
                    // Year Validation
                    if (year < 1900 || year > 2023) {
                        throw new BadYearException("Invalid year: " + year);
                    }
                    
                    validBooks[validCount++] = new Book(title, author, price, isbn, genre, year);
                } catch (BadIsbn10Exception | BadIsbn13Exception | BadPriceException | BadYearException ex) {
                    errorWriter.write("semantic error in file: " + csvFile + "\n====================\nError: " + ex.getMessage() + "\nRecord: " + line + "\n\n");
                } catch (NumberFormatException ex) {
                    errorWriter.write("semantic error in file: " + csvFile + "\n====================\nError: Invalid number format\nRecord: " + line + "\n\n");
                }
            }
            reader.close();
            errorWriter.close();
            
            // Serialize valid books array
            FileOutputStream fileOut = new FileOutputStream(csvFile + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(validBooks);
            out.close();
            fileOut.close();
        }
    }

    private void validateIsbn10(String isbn) throws BadIsbn10Exception {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                throw new BadIsbn10Exception("Invalid ISBN-10 character");
            }
            sum += (10 - i) * Character.getNumericValue(isbn.charAt(i));
        }
        char lastChar = isbn.charAt(9);
        sum += (lastChar == 'X') ? 10 : Character.getNumericValue(lastChar);
        
        if (sum % 11 != 0) {
            throw new BadIsbn10Exception("Invalid ISBN-10 checksum");
        }
    }

    private void validateIsbn13(String isbn) throws BadIsbn13Exception {
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                throw new BadIsbn13Exception("Invalid ISBN-13 character");
            }
            int digit = Character.getNumericValue(isbn.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        int lastDigit = Character.getNumericValue(isbn.charAt(12));
        if ((sum + lastDigit) % 10 != 0) {
            throw new BadIsbn13Exception("Invalid ISBN-13 checksum");
        }
    }
}
