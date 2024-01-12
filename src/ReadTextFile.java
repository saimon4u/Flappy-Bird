import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;

public class ReadTextFile {
    private Scanner input;
    public String ReadTextFile(String username,String password,String fileName,AuthenticationPage page){
        openFile(fileName);
        String data = readData(username,password,fileName,page);
        closeFile();
        return data;
    }
    private void  openFile(String fileName){
        try {
            input = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException fileNotFoundException){
            System.err.println("Error opening file");
            System.exit(1);
        }
    }
    private String readData(String username,String password,String fileName,AuthenticationPage page){
        String user,pass;
        int highScore;
        try{
            user = input.nextLine();
            pass = input.nextLine();
            highScore = input.nextInt();
            if(user.equals(username) && pass.equals(password)){
                closeFile();
                page.dispose();
                FlappyBird flappyBird = new FlappyBird(username,password,fileName,highScore);
            }
            else{
                return pass;
            }
        }
        catch (NoSuchElementException noSuchElementException){
            System.err.println("File improperly formed");
            input.close();
            System.exit(1);
        }
        catch (IllegalStateException illegalStateException){
            System.err.println("Error reading from file");
            System.exit(1);
        }
        return "";
    }
    private void closeFile(){
        if(input != null) input.close();
    }
}
