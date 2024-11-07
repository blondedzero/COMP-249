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
    public static void do_part1(){
        try(BufferedReader br = new BufferedReader(new FileReader("Part1_input_file_names.txt"))){
            int numberOfFiles = Integer.parseInt(br.readLine());
            
            String filename;
            while((filename = br.readLine())!= null){
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
    private static void processFile(String filename){
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            
            String line;

            //used to a while loop to read the contents of the file until it reaches the end where it will stop the loop
            while ((line = br.readLine()) != null) {
                try {
                validateRecord(line, filename); 
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
    private static void validateRecord(String line, String filename) {
        // TODO Auto-generated method stub
        
    }

    //logSyntaxError
    private static void logSyntaxError(String message, String filename, String line) {
        // TODO Auto-generated method stub
        
    }
}
