import java.io.FileNotFoundException;
import java.lang.SecurityException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CreateTextFile {
    private Formatter output;

    public CreateTextFile(String username,String password,String fileName,int highScore){
        openFile(fileName);
        addData(username,password,highScore);
        closeFile();
    }
    private void openFile(String fileName){
        try {
            output = new Formatter(fileName);
        }
        catch(SecurityException securityException){
            System.err.println("You do not have write access to this file");
            System.exit(1);
        }
        catch(FileNotFoundException fileNotFoundException){
            System.err.println("Error creating file");
            System.exit(1);
        }
    }
    private void addData(String username,String password,int highScore){
        Scanner input = new Scanner(System.in);
        try {
            output.format("%s\n%s\n%d",username,password,highScore);
        }
        catch(FormatterClosedException formatterClosedException){
            System.err.println("Error writing to file");
            return;
        }
        catch(NoSuchElementException noSuchElementException){
            System.err.println("Invalid input please try again");
            input.nextLine();
        }
    }
    private void closeFile(){
        if(output != null) output.close();
    }
}
