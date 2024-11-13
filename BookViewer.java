// -----------------------------------------------------
// Assignment 2
// Question: Part 3
// Written by: Kaila Quimson (40240746) & Nicholas Chamoun (40264135)
// ----------------------------------------------------- 


// This class defines the program's UI
//It displays an interative menu that allows usees to view/select files and view their records. 


import java.io.*;
import java.util.*;

public class BookViewer {

    private static final String[] files = FileNames.BINARY_FILES;
    private static Book[] currentBooks = null;
    private static int currentIndex = 0;
    private static String currentFileName = "";

    public static void main(String[] args) {
        do_part3();
    }

    public static void do_part3() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Kaila & Nicholas' Book Viewer program!\n");

        while (true) {
            // main menu
            System.out.println("-----------------------------");
            System.out.println("          Main Menu");
            System.out.println("-----------------------------");
            System.out.printf(" v View the selected file: %s (%d records)\n", currentFileName, 
                    (currentBooks == null ? 0 : currentBooks.length));
            System.out.println(" s Select a file to view");
            System.out.println(" x Exit");
            System.out.println("-----------------------------");
            System.out.print(" Enter Your Choice: ");
            String choice = sc.nextLine().toLowerCase();

            switch (choice) {
                case "v":
                    viewSelectedFile();
                    break;
                case "s":
                    selectFile(sc);
                    break;
                case "x":
                    System.out.println("Now exiting Kaila & Nicholas' Book Viewer program...");
                    System.exit(0);
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // method to view selected file
    public static void viewSelectedFile() {
        if (currentBooks == null) {
            System.out.println("No file selected yet.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your Choice: v");
        System.out.printf("viewing: %s (%d records)\n", currentFileName, currentBooks.length);
        
        while (true) {
            System.out.print("Enter the number of records to view (+/-n): ");
            int n = sc.nextInt();
            sc.nextLine();

            if (n == 0) {
                return; // return to the main menu
            }

            displayBooks(n);
        }
    }

    // Function to display books based on the input n
    public static void displayBooks(int n) {
        int displayed = 0;
        if (n > 0) {
            // Display current record and n-1 records below it
            for (int i = currentIndex; i < currentBooks.length && displayed < n; i++) {
                System.out.println(currentBooks[i]);
                currentIndex = i; // update currentIndex to the last displayed record
                displayed++;
            }
            if (currentIndex == currentBooks.length - 1) {
                System.out.println("EOF has been reached");
            }
        } else {
            // displays current record and |n|-1 records above it
            int range = Math.abs(n) - 1;
            int start = Math.max(currentIndex - range, 0);
            for (int i = start; i <= currentIndex; i++) {
                System.out.println(currentBooks[i]);
                currentIndex = i; // update currentIndex to the first displayed record
                displayed++;
            }
            if (currentIndex == 0) {
                System.out.println("BOF has been reached");
            }
        }
    }

    // method to select a file from sub-menu
    public static void selectFile(Scanner sc) {
        System.out.println("------------------------------");
        System.out.println("          File Sub-Menu");
        System.out.println("------------------------------");
        for (int i = 0; i < files.length; i++) {
            File file = new File(files[i]);
            int records = 0;
            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    Book[] books = (Book[]) ois.readObject();
                    records = books.length;
                } catch (Exception e) {
                    System.out.println("Error reading file " + files[i]);
                }
            }
            System.out.printf(" %d %s (%d records)\n", i + 1, files[i], records);
        }
        System.out.println(" 9 Exit");
        System.out.println("------------------------------");
        System.out.print(" Enter Your Choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 9) {
            return; // exits sub-menu
        }

        if (choice >= 1 && choice <= 8) {
            loadBooksFromFile(files[choice - 1]);
        } else {
            System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    // Function to load books from the selected file
    public static void loadBooksFromFile(String fileName) {
        File file = new File(fileName);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            currentBooks = (Book[]) ois.readObject();
            currentFileName = fileName;
            currentIndex = 0;
            System.out.println("File loaded successfully: " + fileName);
        } catch (Exception e) {
            System.out.println("Error loading file " + fileName);
        }
    }
}
