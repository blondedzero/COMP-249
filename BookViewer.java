// -----------------------------------------------------
// Assignment 2
// Question: Part 3
// Written by: Kaila Quimson (40240746) & Nicholas Chamoun (40264135)
// ----------------------------------------------------- 


//This class defines the program's UI
//It displays an interative menu that allows usees to view/select files and view their records. 

import java.io.*;
import java.util.*;

public class BookViewer {

    private static List<Book[]> bookArrays = new ArrayList<>();
    private static int currentArrayIndex = 0;
    private static int currentRecordIndex = 0;

    public static void main(String[] args) 
   
    {
        // Step 1: 
        loadSerializedFiles();

        // Step 2: 
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            // Main Menu
            System.out.println("-----------------------------");
            System.out.println("Main Menu");
            System.out.println("-----------------------------");
            System.out.println("v View the selected file: " + getCurrentFileName() + " (" + getCurrentFileRecords() + " records)");
            System.out.println("s Select a file to view");
            System.out.println("x Exit");
            System.out.print("Enter Your Choice: ");
            choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "v":
                    // View the selected file
                    viewFile();
                    break;
                case "s":
                    // Select a file
                    selectFile();
                    break;
                case "x":
                    // Exit
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (!choice.equals("x"));
    }

    private static void loadSerializedFiles() {
        String[] fileNames = {
            "Cartoons_Comics_Books.csv.ser", 
            "Hobbies_Collectibles_Books.csv.ser", 
            "Movies_TV.csv.ser", 
            "Music_Radio_Books.csv.ser", 
            "Nostalgia_Eclectic_Books.csv.ser", 
            "Old_Time_Radio_Books.csv.ser", 
            "Sports_Sports_Memorabilia.csv.ser", 
            "Trains_Planes_Automobiles.csv.ser"
        };

        // Load each file into a Book array
        for (String fileName : fileNames) {
            File file = new File("serFiles", fileName);
            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    Book[] books = (Book[]) ois.readObject();
                    bookArrays.add(books);
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Error reading file: " + fileName);
                }
            } else {
                bookArrays.add(new Book[0]); // Empty array if file does not exist
            }
        }
    }

    private static String getCurrentFileName() {
        return new String[] {
            "Cartoons_Comics_Books.csv.ser", 
            "Hobbies_Collectibles_Books.csv.ser", 
            "Movies_TV.csv.ser", 
            "Music_Radio_Books.csv.ser", 
            "Nostalgia_Eclectic_Books.csv.ser", 
            "Old_Time_Radio_Books.csv.ser", 
            "Sports_Sports_Memorabilia.csv.ser", 
            "Trains_Planes_Automobiles.csv.ser"
        }[currentArrayIndex];
    }

    private static int getCurrentFileRecords() {
        return bookArrays.get(currentArrayIndex).length;
    }

    private static void viewFile() {
        Scanner scanner = new Scanner(System.in);
        Book[] books = bookArrays.get(currentArrayIndex);

        if (books.length == 0) {
            System.out.println("No records to display.");
            return;
        }

        // Display the file
        String choice;
        do {
            // Show records based on the current position
            System.out.println("Viewing: " + getCurrentFileName() + " (" + books.length + " records)");
            System.out.println("Current record: " + (currentRecordIndex + 1));

            // Display the current record
            System.out.println(books[currentRecordIndex]);

            // Prompt for navigation
            System.out.println("Enter n to navigate (positive/negative), or 0 to end viewing.");
            System.out.print("Enter Your Choice: ");
            choice = scanner.nextLine();

            try {
                int n = Integer.parseInt(choice);
                navigateRecords(n, books);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }

        } while (!choice.equals("0"));
    }

    private static void navigateRecords(int n, Book[] books) {
        int newIndex = currentRecordIndex + n;

        if (n > 0) {
            if (newIndex >= books.length) {
                System.out.println("EOF has been reached.");
                newIndex = books.length - 1;
            }
        } else if (n < 0) {
            if (newIndex < 0) {
                System.out.println("BOF has been reached.");
                newIndex = 0;
            }
        }

        currentRecordIndex = newIndex;
    }

    private static void selectFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------------");
        System.out.println("File Sub-Menu");
        System.out.println("------------------------------");

        for (int i = 0; i < bookArrays.size(); i++) {
            System.out.println((i + 1) + " " + getFileName(i) + " (" + bookArrays.get(i).length + " records)");
        }

        System.out.println("9 Exit");
        System.out.print("Enter Your Choice: ");
        int choice = scanner.nextInt();

        if (choice >= 1 && choice <= 8) {
            currentArrayIndex = choice - 1;
            currentRecordIndex = 0; // Reset to the first record
        }
    }

    private static String getFileName(int index) {
        String[] fileNames = {
            "Cartoons_Comics_Books.csv.ser", 
            "Hobbies_Collectibles_Books.csv.ser", 
            "Movies_TV.csv.ser", 
            "Music_Radio_Books.csv.ser", 
            "Nostalgia_Eclectic_Books.csv.ser", 
            "Old_Time_Radio_Books.csv.ser", 
            "Sports_Sports_Memorabilia.csv.ser", 
            "Trains_Planes_Automobiles.csv.ser"
        };
        return fileNames[index];
    }
}
