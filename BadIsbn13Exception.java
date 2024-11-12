// -----------------------------------------------------
// Assignment 2
// Question: Part 2
// Written by: Kaila Quimson (40240746) & Nicholas Chamoun (40264135)
// ----------------------------------------------------- 

// Class Function: This checked exception class helps reports BadIsbn13Exceptions

public class BadIsbn13Exception extends Exception {
    public BadIsbn13Exception(String message) {
        super(message);
    }
}