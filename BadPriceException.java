// -----------------------------------------------------
// Assignment 2
// Question: Part 2
// Written by: Kaila Quimson (40240746) & Nicholas Chamoun (40264135)
// ----------------------------------------------------- 

// Class Function: This checked exception class helps reports BadPriceExceptions

public class BadPriceException extends Exception {
    public BadPriceException(String message) {
        super(message);
    }
}